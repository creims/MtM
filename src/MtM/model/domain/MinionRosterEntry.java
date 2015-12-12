package MtM.model.domain;

/**
 *
 * @author Colin
 */
public class MinionRosterEntry {
    
    private Minion minion;
    private int cost;

    public MinionRosterEntry(Minion minion) {
        this.minion = minion;
        double c = minion.getFighting() + minion.getGathering() + minion.getPerception() + minion.getTech();
        cost = (int)(c * c * c);
    }

    
    
    /**
     * Get the value of cost
     *
     * @return the value of cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Set the value of cost
     *
     * @param cost new value of cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Get the value of minion
     *
     * @return the value of minion
     */
    public Minion getMinion() {
        return minion;
    }

    /**
     * Set the value of minion
     *
     * @param minion new value of minion
     */
    public void setMinion(Minion minion) {
        this.minion = minion;
    }

}
