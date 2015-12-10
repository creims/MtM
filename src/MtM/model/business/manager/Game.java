package MtM.model.business.manager;

import MtM.model.business.service.MissionGenerator;
import MtM.model.domain.Minion;
import MtM.model.domain.Mission;
import java.util.Properties;

/**
 * Contains information about the game state
 *
 * @author Colin
 */
public class Game {

    private final static int MISSION_AVAILABLE_TARGET = 6;

    private final int maxMinions, maxMissions;
    private int tickRate, numMinions, numMissions, numActiveMissions, difficulty;
    private final Minion[] minions;
    private final Mission[] missions;

    private long catnip;

    public Game(Properties p) {
        tickRate = Integer.parseInt(p.getProperty("TickRate"));
        maxMinions = Integer.parseInt(p.getProperty("MaxMinions"));
        maxMissions = Integer.parseInt(p.getProperty("MaxMissions"));
        catnip = Long.parseLong(p.getProperty("StartingCatnip"));

        minions = new Minion[maxMinions];
        missions = new Mission[maxMissions];
        numMinions = 0;
        numMissions = 0;
        numActiveMissions = 0;
        difficulty = 1;

        catnip = 1000;
    }

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

    public boolean addMinion(Minion minion) {
        if (numMinions >= maxMinions) {
            return false;
        }

        minions[numMinions++] = minion;
        return true;
    }

    public boolean addMission(Mission mission) {
        if (numMissions >= maxMissions) {
            return false;
        }

        missions[numMissions++] = mission;
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

    public String getMissionType(int i) {
        if (i > numMissions - 1 || i < 0) {
            return "No Minion";
        }

        return missions[i].getType().toString();
    }

    public long getCatnip() {
        return catnip;
    }

    public String printMission(int i) {
        if (i > numMissions - 1 || i < 0) {
            return "No Mission";
        }
        return missions[i].toString();
    }

    public Minion[] getMinions() {
        return minions;
    }

    public Mission[] getMissions() {
        return missions;
    }

    public int getMaxMinions() {
        return maxMinions;
    }

    public int getMaxMissions() {
        return maxMissions;
    }

    void populateMissions() {
        while (numMissions - numActiveMissions < MISSION_AVAILABLE_TARGET) {
            addMission(MissionGenerator.generateMission(difficulty));
        }
        System.out.println("");
    }

    public Mission getMission(int i) {
        return missions[i];
    }

    public void swapMissions(int i1, int i2) {
        Mission temp = missions[i1];
        missions[i1] = missions[i2];
        missions[i2] = temp;
    }

    public void activateMission(int index) {
        if (missions[index].isActive()) {
            return;
        }

        minions[index].setActive(true);
        missions[index].setActive(true);
        numActiveMissions++;
    }

    public void completeMission(int index) {
        if (!missions[index].isActive()) {
            return;
        }

        minions[index].setActive(false);
        catnip += missions[index].getReward();
        missions[index] = MissionGenerator.generateMission(difficulty);
        numActiveMissions--;
    }

    public Mission[] getActiveMissions() {
        Mission[] ret = new Mission[missions.length];

        for (int i = 0; i < ret.length; i++) {
            Mission m = missions[i];
            if(m == null) ret[i] = null;
            else ret[i] = m.isActive() ? m : null;
        }

        return ret;
    }

    public boolean getMinionActive(int i) {
        return minions[i].isActive();
    }

}
