package MtM.view.swingcomponents;

import java.awt.Dimension;

/**
 *
 * @author Colin
 */
public class MissionPanel extends MPanel {
    public MissionPanel() {
        super();
    }
    
    public void addMissionBtn(String type) {
        MissionButton newBtn = new MissionButton();
        int size = getWidth();
        newBtn.setPreferredSize(new Dimension(size - 10, size / 2 - 20));
        newBtn.setBtnID(index);
        newBtn.setMissionType(type);
        btnArray[index++] = newBtn;
        add(newBtn);
        newBtn.setMPanel(this);
        newBtn.processPress();
    }                  

    public int getNumMissions() {
        return index;
    }               
}
