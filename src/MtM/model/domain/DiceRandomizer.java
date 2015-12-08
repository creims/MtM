package MtM.model.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Colin
 */
public class DiceRandomizer {

    private static final Random random = new Random();
    
    private static final int DECIMAL_PLACES = 2;
    
    public static double nextVal(double min, double max, int numDice) {
        double ret = 0;
        
        double diff = max - min;

        for (int i = 0; i < numDice; i++) {
            ret += random.nextDouble() * diff + min;
        }

        return round(ret, DECIMAL_PLACES);
    }

    public static double round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static boolean coinFlip() {
        return random.nextBoolean();
    }
}
