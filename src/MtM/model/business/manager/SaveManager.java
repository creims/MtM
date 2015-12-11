package MtM.model.business.manager;

import MtM.model.domain.Minion;
import MtM.model.domain.Mission;
import MtM.model.domain.MissionType;
import MtM.model.domain.Stat;
import MtM.model.domain.StatType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Colin
 */
public class SaveManager {

    private static final String TAG_CATNIP = "[CATNIP]";
    private static final String TAG_DIFFICULTY = "[DIFFICULTY]";
    private static final String TAG_MINIONS = "[MINIONS]";
    private static final String TAG_MISSIONS = "[MISSIONS]";

    private static final String NOT_BRACKET = "^" + Pattern.quote("[");

    private static Game currentGame;

    private static String saveFile;
    private static final String PROPS_PATH = "config/config.txt";
    public static final String SAVE_PATH = "save/";
    private static Properties p;
    private static Scanner scanner, lineScanner;
    private static PrintWriter writer;

    public SaveManager() {
        loadProps();
        saveFile = p.getProperty("LastGame");
        System.out.println("Loading " + saveFile);
        loadGame(saveFile);
    }

    public void loadProps() {
        try {
            FileInputStream file = new FileInputStream(PROPS_PATH);
            p = new Properties();
            p.load(file);
            file.close();
        } catch (IOException ex) {
            System.out.println("Failed to load properties.");
        }
    }

    public void saveProps() {
        try {
            FileInputStream file = new FileInputStream("config/config.txt");
            Properties p = new Properties();
            p.load(file);
            p.put("LastGame", (saveFile == null) ? "" : saveFile);
            p.put("TickRate", "" + currentGame.getTickRate());
            p.put("MaxMinions", "" + currentGame.getMaxMinions());
            p.put("MaxMissions", "" + currentGame.getMaxMissions());

            writer = new PrintWriter(PROPS_PATH);
            p.store(writer, null);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return false if the currentGame file is null
     */
    public boolean saveGame() {
        if (saveFile == null) {
            return false;
        }
        saveGame(saveFile);
        return true;
    }

    public void saveGame(String file) {
        saveFile = file;
        try {
            writer = new PrintWriter(new FileOutputStream(SAVE_PATH + saveFile), true);

            saveUniversal();
            saveMinions();
            saveMissions();

            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("wut");
        }
    }

    private static void saveUniversal() {
        writer.println(TAG_CATNIP);
        writer.println(currentGame.getCatnip());
        writer.println(TAG_DIFFICULTY);
        writer.println(currentGame.getDifficulty());
    }

    private static void saveMinions() {
        writer.println(TAG_MINIONS);

        for (Minion m : currentGame.getMinions()) {
            if (m == null) {
                continue;
            }
            writer.println(m.saveString());
        }
    }

    private static void saveMissions() {
        writer.println(TAG_MISSIONS);

        for (Mission m : currentGame.getMissions()) {
            if (m == null) {
                continue;
            }
            writer.println(m.saveString());
        }
    }

    public static boolean loadGame(String fileName) {
        currentGame = new Game(p);

        saveFile = fileName;
        try {
            FileInputStream file = new FileInputStream(SAVE_PATH + fileName);
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to load " + fileName);
            return false;
        }

        //TODO: make this easy to expand
        loadUniversal();

        loadMinions();
        loadMissions();
        //currentGame.populateMissions();

        return true;
    }

    private static void loadUniversal() {
        scanner.useDelimiter("\\s*[,\n]\\s*");
        scanner.nextLine();
        currentGame.setCatnip(scanner.nextLong());
        scanner.nextLine();
        scanner.nextLine();
        currentGame.setDifficulty(scanner.nextDouble());
    }

    private static void loadMinions() {
        scanner.nextLine();
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            //use linescanner to read the line, break and eat lines containing ']'
            lineScanner = new Scanner(scanner.nextLine());
            lineScanner.useDelimiter("\\s*[,\n]\\s*");
            if (lineScanner.findInLine(Pattern.quote("]")) != null) {
                break;
            }

            Minion m = new Minion(lineScanner.next(), lineScanner.nextDouble(), lineScanner.nextDouble(), lineScanner.nextDouble(), lineScanner.nextDouble());
            m.setActive(lineScanner.nextBoolean());
            if (!currentGame.addMinion(m)) {
                break;
            }
        }
    }

    private static void loadMissions() {
        //scanner.nextLine();

        while (scanner.hasNextLine()) {
            //use linescanner to read the line, break and eat lines containing ']'
            lineScanner = new Scanner(scanner.nextLine());
            lineScanner.useDelimiter("\\s*[,\n]\\s*");
            if (lineScanner.findInLine(Pattern.quote("]")) != null) {
                break;
            }

            Mission m = new Mission(MissionType.stringToType(lineScanner.next()), lineScanner.nextInt(), lineScanner.nextInt());
            m.setCurrentTime(lineScanner.nextInt());
            m.setActive(lineScanner.nextBoolean());
            m.setDone(lineScanner.nextBoolean());

            m.setPrimaryStat(new Stat(StatType.stringToStat(lineScanner.next()), lineScanner.nextDouble()));
            if (lineScanner.hasNext()) {
                m.setSecondaryStat(new Stat(StatType.stringToStat(lineScanner.next()), lineScanner.nextDouble()));
            }

            if (!currentGame.addMission(m)) {
                return;
            }
        }
    }

    public String getSaveFile() {
        return saveFile;
    }

    public Game getGame() {
        return currentGame;
    }

}
