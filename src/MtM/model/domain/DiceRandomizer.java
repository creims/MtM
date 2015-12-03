package MtM.model.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Colin
 */
public class DiceRandomizer {

    private double diceMin, diceMax;
    private int numDice;
    private final ThreadLocalRandom random;
    
    private static final int DECIMAL_PLACES = 2;

    public DiceRandomizer(double min, double max, double increment, int numDice) {
        this.diceMin = min;
        this.diceMax = max;
        this.numDice = numDice;
        random = ThreadLocalRandom.current();
    }

    public double nextVal() {
        double ret = 0;

        for (int i = 0; i < numDice; i++) {
            ret += random.nextDouble(diceMin, diceMax);
        }

        return round(ret, DECIMAL_PLACES);
    }

    public static double round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double getDiceMin() {
        return diceMin;
    }

    public void setDiceMin(double diceMin) {
        this.diceMin = diceMin;
    }

    public double getDiceMax() {
        return diceMax;
    }

    public void setDiceMax(double diceMax) {
        this.diceMax = diceMax;
    }

    public int getNumDice() {
        return numDice;
    }

    public void setNumDice(int numDice) {
        this.numDice = numDice;
    }

}
