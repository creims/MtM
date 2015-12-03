package MtM.model.business.manager;

import MtM.model.domain.Minion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class Game {

    private int tickRate, numMinions, maxMinions;
    private Minion[] minions;
    String saveFile;
    private static final String SAVE_PATH = "save/";
    private static final String PROPS_PATH = "config/config.txt";

    /**
     * Get the value of numMinions
     *
     * @return the value of numMinions
     */
    public int getNumMinions() {
        return numMinions;
    }

    /**
     * Set the value of numMinions
     *
     * @param numMinions new value of numMinions
     */
    public void setNumMinions(int numMinions) {
        this.numMinions = numMinions;
    }

    public Game() {
        Properties p = loadProps();

        loadGame(p.getProperty("LastGame"));
    }

    public boolean loadGame(String fileName) {
        minions = new Minion[maxMinions];
        numMinions = 0;

        saveFile = fileName;
        Scanner input;
        try {
            FileInputStream file = new FileInputStream(SAVE_PATH + fileName);
            input = new Scanner(file);
        } catch (FileNotFoundException ex) {
            newGame();
            return false;
        }

        //add the minions
        input.useDelimiter("\\s*[,\n]\\s*");

        boolean done;
        while (input.hasNext()) {
            //if the add fails, discontinue loading the file
            // TODO: error handling
            if (!addMinion(new Minion(input.next(), input.nextDouble(), input.nextDouble(), input.nextDouble(), input.nextDouble()))) {
                return true;
            }
        }

        return true;
    }

    /**
     * Get the value of tickRate
     *
     * @return the value of tickRate
     */
    public int getTickRate() {
        return tickRate;
    }

    /**
     * Set the value of tickRate
     *
     * @param tickRate new value of tickRate
     */
    public void setTickRate(int tickRate) {
        this.tickRate = tickRate;
    }

    public void newGame() {
        saveFile = null;
        minions = new Minion[maxMinions];
    }

    /**
     *
     * @return false if the current file is null
     * @throws FileNotFoundException
     */
    public boolean saveGame() throws FileNotFoundException {
        if (saveFile == null) {
            return false;
        }
        saveGame(saveFile);
        return true;
    }

    public void saveGame(String file) throws FileNotFoundException {
        this.saveFile = file;
        PrintWriter writer = new PrintWriter(saveFile);
        for (Minion m : minions) {
            if (m == null) {
                continue;
            }
            writer.println(m.saveString());
        }
        writer.close();

    }

    public boolean addMinion(Minion minion) {
        if (numMinions >= maxMinions) {
            return false;
        }

        minions[numMinions++] = minion;
        return true;
    }

    public String printMinion(int i) {
        if (i > numMinions - 1 || i < 0) {
            return "No Minion";
        }

        return minions[i].toString();
    }

    public String getMinionName(int i) {
        if (i > numMinions - 1 || i < 0) {
            return "No Minion";
        }

        return minions[i].getName();
    }

    public Properties loadProps() {
        try {
            FileInputStream file = new FileInputStream(PROPS_PATH);
            Properties p = new Properties();
            p.load(file);
            file.close();

            tickRate = Integer.parseInt(p.getProperty("TickRate"));
            maxMinions = Integer.parseInt(p.getProperty("MaxMinions"));

            return p;
        } catch (IOException ex) {
            System.out.println(ex);
            System.out.println("Config file not found or is wrong.");
            System.exit(1);
        }

        return null;
    }

    public void saveProps() {
        try {
            FileInputStream file = new FileInputStream("config/config.txt");
            Properties p = new Properties();
            p.load(file);
            p.put("LastGame", (saveFile == null) ? "" : saveFile);
            p.put("TickRate", "" + tickRate);
            p.put("MaxMinions", "" + maxMinions);

            PrintWriter writer = new PrintWriter(PROPS_PATH);
            p.store(writer, null);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSaveFile() {
        return saveFile;
    }
}
