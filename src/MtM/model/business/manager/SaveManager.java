package MtM.model.business.manager;

import MtM.model.domain.Minion;
import MtM.model.domain.Mission;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Colin
 */
public class SaveManager {

    private static Game currentGame;

    private static String saveFile;
    private static final String PROPS_PATH = "config/config.txt";
    public static final String SAVE_PATH = "save/";
    private static Properties p;

    public SaveManager() {
        loadProps();
        saveFile = p.getProperty("LastGame");
        System.out.println(saveFile);
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

            PrintWriter writer = new PrintWriter(PROPS_PATH);
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
        this.saveFile = file;
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileOutputStream(SAVE_PATH + saveFile), true);
            for (Minion m : currentGame.getMinions()) {
                if (m == null) {
                    continue;
                }
                writer.println(m.saveString());
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("wut");
        }

    }

    public static boolean loadGame(String fileName) {
        currentGame = new Game(p);

        saveFile = fileName;
        Scanner input;
        try {
            FileInputStream file = new FileInputStream(SAVE_PATH + fileName);
            input = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to load " + fileName);
            return false;
        }

        //add the minions
        input.useDelimiter("\\s*[,\n]\\s*");

        while (input.hasNext()) {
            //if the add fails, discontinue loading the file
            // TODO: error handling
            if (!currentGame.addMinion(new Minion(input.next(), input.nextDouble(), input.nextDouble(), input.nextDouble(), input.nextDouble()))) {
                return true;
            }
        }

        currentGame.populateMissions();
        return true;
    }

    public String getSaveFile() {
        return saveFile;
    }

    public Game getGame() {
        return currentGame;
    }

}
