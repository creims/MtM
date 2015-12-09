package MtM.model.domain;

import java.util.Random;

/**
 *
 * @author Colin
 */
public class DiceRandomizer {

    private static Random random = new Random();
    
    public static double nextVal(double min, double max, int numDice) {
        double ret = 0;
        
        double diff = max - min;

        for (int i = 0; i < numDice; i++) {
            ret += random.nextDouble() * diff + min;
        }

        return ret;
    }

    public static boolean coinFlip() {
        return random.nextBoolean();
    }
}
