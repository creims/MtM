package MtM.model.domain;

/**
 *
 * @author Colin
 */
class Mission {

    private int timeRequired;
    private int currentTime;
    private int completionRate;

    private boolean done;

    public Mission(int timeRequired, int completionRate) {
        this.timeRequired = timeRequired;
        this.currentTime = 0;
        this.completionRate = completionRate;
        this.done = false;
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

}
