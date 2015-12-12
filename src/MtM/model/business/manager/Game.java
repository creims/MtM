package MtM.model.business.manager;

import MtM.model.business.service.MinionGenerator;
import MtM.model.business.service.MissionGenerator;
import MtM.model.domain.Minion;
import MtM.model.domain.MinionRosterEntry;
import MtM.model.domain.Mission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

/**
 * Contains information about the game state
 *
 * @author Colin
 */
public class Game {

    private final static int MISSION_AVAILABLE_TARGET = 6;
    private final static int ROSTER_TARGET = 10;

    private final int maxMinions, maxMissions;
    private int tickRate, numActiveMissions;
    private double difficulty;

    private final ArrayList<Minion> minions;
    private final ArrayList<Mission> missions;
    private final ArrayList<MinionRosterEntry> roster;

    private long catnip;

    public Game(Properties p) {
        tickRate = Integer.parseInt(p.getProperty("TickRate"));
        maxMinions = Integer.parseInt(p.getProperty("MaxMinions"));
        maxMissions = Integer.parseInt(p.getProperty("MaxMissions"));
        catnip = Long.parseLong(p.getProperty("StartingCatnip"));

        minions = new ArrayList();
        missions = new ArrayList();
        roster = new ArrayList();

        numActiveMissions = 0;
        difficulty = 1;

        catnip = 1000;
    }

    public int getNumMinions() {
        return minions.size();
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
        if (minions.size() >= maxMinions) {
            return false;
        }

        minions.add(minion);
        return true;
    }

    public boolean addMission(Mission mission) {
        if (missions.size() >= maxMissions) {
            return false;
        }

        missions.add(mission);
        return true;
    }

    public String printMinion(int i) {
        if (i > minions.size() - 1 || i < 0) {
            return "No Minion";
        }

        return minions.get(i).toString();
    }

    public String getMinionName(int i) {
        if (i > minions.size() - 1 || i < 0) {
            return "No Minion";
        }

        return minions.get(i).getName();
    }

    public String getMissionType(int i) {
        if (i > missions.size() - 1 || i < 0) {
            return "No Minion";
        }

        return missions.get(i).getType().toString();
    }

    public long getCatnip() {
        return catnip;
    }

    public String printMission(int i) {
        if (i > missions.size() - 1 || i < 0) {
            return "No Mission";
        }
        return missions.get(i).toString();
    }

    public Minion[] getMinions() {
        return (Minion[]) minions.toArray(new Minion[1]);
    }

    public Mission[] getMissions() {
        return (Mission[]) missions.toArray(new Mission[1]);
    }

    public MinionRosterEntry[] getRoster() {
        return (MinionRosterEntry[]) roster.toArray(new MinionRosterEntry[1]);
    }

    public int getMaxMinions() {
        return maxMinions;
    }

    public int getMaxMissions() {
        return maxMissions;
    }

    public void populateMissions() {
        while (missions.size() - numActiveMissions < MISSION_AVAILABLE_TARGET) {
            addMission(MissionGenerator.generateMission(difficulty));
        }
    }

    public void populateRoster() {
        while (roster.size() < ROSTER_TARGET) {
            roster.add(new MinionRosterEntry(MinionGenerator.generateMinion()));
        }
    }

    public Mission getMission(int i) {
        return missions.get(i);
    }

    public void swapMissions(int i1, int i2) {
        Collections.swap(missions, i1, i2);
    }

    public void activateMission(int i) {
        Mission m = missions.get(i);
        if (m.isActive()) {
            return;
        }

        minions.get(i).setActive(true);
        m.setActive(true);
        numActiveMissions++;
    }

    public void completeMission(int i) {
        Mission mission = missions.get(i);
        if (!mission.isActive()) {
            return;
        }

        Minion minion = minions.get(i);

        minion.setActive(false);
        minion.growStat(mission.getPrimaryStat());
        minion.growStat(mission.getSecondaryStat());
        catnip += mission.getReward();
        difficulty += .2;
        missions.set(i, MissionGenerator.generateMission(difficulty));
        numActiveMissions--;
    }

    public Mission[] getActiveMissions() {
        Mission[] ret = new Mission[missions.size()];

        for (int i = 0; i < ret.length; i++) {
            Mission m = missions.get(i);
            if (m == null || m.isDone()) {
                ret[i] = null;
            } else {
                ret[i] = m.isActive() ? m : null;
            }
        }

        return ret;
    }

    public boolean getMinionActive(int i) {
        if (i >= minions.size()) {
            return false;
        }
        return minions.get(i).isActive();
    }

    void setCatnip(long newCatnipAmt) {
        catnip = newCatnipAmt;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

}
