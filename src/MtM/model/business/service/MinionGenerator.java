package MtM.model.business.service;

import MtM.model.domain.DiceRandomizer;
import MtM.model.domain.Minion;

/**
 *
 * @author Colin
 */
public class MinionGenerator {

    private static final double STAT_MIN = 0, STAT_MAX = 6;
    private static final int NUM_DICE = 1;
    private static final char[] CONSONANTS = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'r', 's', 't', 'v', 'w', 'x', 'z'};
    private static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u', 'y'};
    private static final String[] PATTERNS = {"Abba", "Baab", "Ababa", "Bababa", "Aba", "Bab", "Babba"};

    public static Minion generateMinion() {
        String name = randomName();
        double p = DiceRandomizer.nextVal(STAT_MIN, STAT_MAX, NUM_DICE);
        double t = DiceRandomizer.nextVal(STAT_MIN, STAT_MAX, NUM_DICE);
        double g = DiceRandomizer.nextVal(STAT_MIN, STAT_MAX, NUM_DICE);
        double f = DiceRandomizer.nextVal(STAT_MIN, STAT_MAX, NUM_DICE);

        return new Minion(name, p, t, g, f);
    }

    private static String randomName() {
        String ret = "";
        ret += randomWord();
        if (DiceRandomizer.coinFlip()) {
            ret += " " + randomWord();
        }

        return ret;
    }

    private static String randomWord() {
        String ret = "";
        String pattern = PATTERNS[(int) (Math.random() * PATTERNS.length)];

        for (int i = 0; i < pattern.length(); i++) {
            switch (pattern.charAt(i)) {
                case 'A':
                    ret += Character.toUpperCase(VOWELS[(int) (Math.random() * VOWELS.length)]);
                    break;
                case 'B':
                    ret += Character.toUpperCase(CONSONANTS[(int) (Math.random() * CONSONANTS.length)]);
                    break;
                case 'a':
                    ret += VOWELS[(int) (Math.random() * VOWELS.length)];
                    break;
                case 'b':
                    ret += CONSONANTS[(int) (Math.random() * CONSONANTS.length)];
                    break;
            }
        }

        return ret;
    }
}
