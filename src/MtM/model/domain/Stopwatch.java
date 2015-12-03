package MtM.model.domain;

/**
 *
 * @author Colin
 */
public class Stopwatch {

    private long startTime;
    private long endTime;

    public Stopwatch(int seconds) {
        this.startTime = System.currentTimeMillis();
        this.endTime = startTime + (seconds * 1000);
    }

    public void setTimer(int seconds) {
        this.startTime = System.currentTimeMillis();
        this.endTime = startTime + (seconds * 1000);
    }

    public long getTimeLeft() {
        return endTime - System.currentTimeMillis();
    }

    public long getEndTime() {
        return this.endTime;
    }
    
    public double getPercent() {
        double pct = ((double)(System.currentTimeMillis() - startTime) / (endTime - startTime));
        pct *= 100;
        if(pct > 100) return 100;
        else return pct;
    }
    
    @Override
    public String toString(){
        if(this.isDone()) {
            return "Done!";
        }
        
        int minutes, seconds;
        seconds = (int)(this.getTimeLeft() / 1000);
        minutes = seconds / 60; 
        seconds -= minutes * 60;
        return String.format("%2d:%2d", minutes, seconds);
        
    }

    private boolean isDone() {
        return getTimeLeft() < 0;
    }
}
