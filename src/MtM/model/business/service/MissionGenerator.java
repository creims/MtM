package MtM.model.business.service;

import MtM.model.domain.DiceRandomizer;
import MtM.model.domain.Mission;
import MtM.model.domain.MissionType;
import MtM.model.domain.Stat;

/**
 *
 * @author Colin
 */
public class MissionGenerator {

    public static Mission generateMission(double difficulty) {
        Mission ret;

        MissionType type = MissionType.randomMissionType();
        Stat primaryStat, secondaryStat;
        double val1, val2;

        //single-stat missions
        if (MissionType.getNumMissionStats(type) == 1) {
            val1 = DiceRandomizer.nextVal(1, Math.floor(difficulty * 1.5), 1);
            primaryStat = new Stat(MissionType.getFirstStat(type), val1);
            secondaryStat = null;
        } else { //double-stat missions
            val1 = DiceRandomizer.nextVal(1, Math.floor(difficulty * 3), 1);
            val2 = Math.floor(difficulty * 3) - val1;

            // swap to ensure primary bigger for display purposes
            if (val2 > val1) {
                double temp = val2;
                val2 = val1;
                val1 = temp;
            }
            
            //randomize which stat is primary
            if(DiceRandomizer.coinFlip()) {
               primaryStat = new Stat(MissionType.getFirstStat(type), val1);
               secondaryStat = new Stat(MissionType.getSecondStat(type), val2);
            } else {
                primaryStat = new Stat(MissionType.getSecondStat(type), val1);
               secondaryStat = new Stat(MissionType.getFirstStat(type), val2);
            }
            
        }

        ret = new Mission(type, 100, 1);
        ret.setPrimaryStat(primaryStat);
        ret.setSecondaryStat(secondaryStat);
        ret.setReward(144 * difficulty * difficulty);

        return ret;
    }
}
