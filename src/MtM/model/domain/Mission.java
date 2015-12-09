package MtM.model.domain;

/**
 *
 * @author Colin
 */
public class Mission {

    private final MissionType type;

    private int timeRequired;
    private int currentTime;
    private int completionRate;

    private boolean done;
    private boolean active;

    private Stat primaryStat;
    private Stat secondaryStat;

    private double reward;

    public Mission(MissionType type, int timeRequired, int completionRate) {
        this.type = type;
        this.timeRequired = timeRequired;
        this.completionRate = completionRate;
        this.currentTime = 0;
        this.done = false;
        this.active = false;
    }

    /**
     *
     * @return true iff the mission is done
     */
    public boolean update() {
        if (done) {
            return true;
        }
        currentTime += completionRate;
        if (currentTime >= timeRequired) {
            currentTime = timeRequired;
            done = true;
        }

        return done;
    }

    /**
     * Get the value of done
     *
     * @return the value of done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Set the value of done
     *
     * @param done new value of done
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Get the value of completionRate
     *
     * @return the value of completionRate
     */
    public int getCompletionRate() {
        return completionRate;
    }

    /**
     * Set the value of completionRate
     *
     * @param completionRate new value of completionRate
     */
    public void setCompletionRate(int completionRate) {
        this.completionRate = completionRate;
    }

    /**
     * Get the value of currentTime
     *
     * @return the value of currentTime
     */
    public int getCurrentTime() {
        return currentTime;
    }

    /**
     * Set the value of currentTime
     *
     * @param currentTime new value of currentTime
     */
    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * Get the value of timeRequired
     *
     * @return the value of timeRequired
     */
    public int getTimeRequired() {
        return timeRequired;
    }

    /**
     * Set the value of timeRequired
     *
     * @param timeRequired new value of timeRequired
     */
    public void setTimeRequired(int timeRequired) {
        this.timeRequired = timeRequired;
    }

    /**
     * Get the value of active
     *
     * @return the value of active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set the value of active
     *
     * @param active new value of active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    public MissionType getType() {
        return type;
    }

    public Stat getPrimaryStat() {
        return primaryStat;
    }

    public void setPrimaryStat(Stat primaryStat) {
        this.primaryStat = primaryStat;
    }

    public Stat getSecondaryStat() {
        return secondaryStat;
    }

    public void setSecondaryStat(Stat secondaryStat) {
        this.secondaryStat = secondaryStat;
    }

    /**
     * Get the value of reward
     *
     * @return the value of reward
     */
    public double getReward() {
        return reward;
    }

    /**
     * Set the value of reward
     *
     * @param reward new value of reward
     */
    public void setReward(double reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "Mission Type: " + type.toString()
                + "\n\n’Twas brillig, and the slithy toves\n"
                + "      Did gyre and gimble in the wabe:\n"
                + "All mimsy were the borogoves,\n"
                + "      And the mome raths outgrabe.\n\n"
                + primaryStat.toString() + (secondaryStat != null ? "\n" + secondaryStat.toString() : "")
                + "\n\nMission Status: " + (active ? "Active" : "Available") + 
                 "\nReward: "+ reward;
    }

}
