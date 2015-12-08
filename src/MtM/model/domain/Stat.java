package MtM.model.domain;

/**
 *
 * @author Colin
 */
public class Stat {

    private StatType type;

    private double value;

    public Stat(StatType type, double value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Get the value of value
     *
     * @return the value of value
     */
    public double getValue() {
        return value;
    }

    /**
     * Set the value of value
     *
     * @param value new value of value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public StatType getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(StatType type) {
        this.type = type;
    }

}
