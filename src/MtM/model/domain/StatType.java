package MtM.model.domain;

/**
 *
 * @author Colin
 */
public enum StatType {
    PERCEPTION, TECH, GATHERING, FIGHTING;

    @Override
    public String toString() {
        switch(this) {
            case PERCEPTION: return "Perception";
            case TECH: return "Tech";
            case GATHERING: return "Gathering";
            case FIGHTING: return "Fighting";
            default: return "STAT TYPE NOT IMPLEMENTED";
        }
    }
    
    public static StatType stringToStat(String s) {
        for(StatType stat : StatType.values()) {
            if(stat.name().equals(s.toUpperCase())) {
                return stat;
            }
        }
        
        return null;
    }
    
}
