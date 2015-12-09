package MtM.model.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Colin
 */
public class MtMUtil {

    public static double round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
