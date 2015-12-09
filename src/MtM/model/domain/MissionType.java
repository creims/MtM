package MtM.model.domain;

import java.util.Random;

public enum MissionType {
    MINING, SCOUT, HACK, DEFEND, STAR_SIPHON, SALVAGE, RAID, INVESTIGATE, BOUNTY_HUNT,
    SABOTAGE;
    
    private static final MissionType[] TYPES = values();
    private static final int SIZE = TYPES.length - 1;
    private static final Random RANDOM = new Random();
    
    public static MissionType randomMissionType() {
        return TYPES[RANDOM.nextInt(SIZE)];
    }

    public static int getNumMissionStats(MissionType type) {
        switch(type) {
            case MINING: case SCOUT: case HACK: case DEFEND:
                return 1;
            default:
                return 2;               
        }
    }

    public static StatType getFirstStat(MissionType type) {
        switch(type) {
            case MINING: case STAR_SIPHON: case SALVAGE: case RAID: 
                return StatType.GATHERING;
            case SCOUT: case INVESTIGATE: case BOUNTY_HUNT: 
                return StatType.PERCEPTION;
            case HACK: case SABOTAGE: 
                return StatType.TECH; 
            case DEFEND: 
                return StatType.FIGHTING;             
        }
        //should never get here
        return null;
    }

    public static StatType getSecondStat(MissionType type) {
        switch(type) {
            case STAR_SIPHON: case INVESTIGATE: 
                return StatType.TECH;
            case SALVAGE: 
                return StatType.PERCEPTION;
            case RAID: case BOUNTY_HUNT: case SABOTAGE:
                return StatType.FIGHTING;                    
            default: 
                return null;        
        }
    }

    @Override
    public String toString() {
        switch(this) {
            case MINING: return "Mining"; 
            case SCOUT: return "Scouting"; 
            case HACK: return "Hacking";
            case DEFEND: return "Defend";
            case STAR_SIPHON: return "Siphon Star";
            case SALVAGE: return "Salvage";
            case RAID: return "Raid";
            case INVESTIGATE: return "Investigation";
            case BOUNTY_HUNT: return "Bounty Hunt";
            case SABOTAGE: return "Sabotage";
            default: return "MISSIONTYPE NOT IMPLEMENTED";
        }
    }
}