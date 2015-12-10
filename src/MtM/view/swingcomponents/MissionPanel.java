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

    public void addMissionBtn(String type, int maxTime) {
        MissionButton newBtn = new MissionButton(maxTime);
        int size = getWidth();
        newBtn.setPreferredSize(new Dimension(size - 10, size / 2 - 20));
        newBtn.setBtnID(index);
        newBtn.setMissionType(type);
        btnArray[index++] = newBtn;
        add(newBtn);
        newBtn.setMPanel(this);
        newBtn.processPress();
    }
    
    public void completeMission(int index, String newType, int newMaxTime) {
        ((MissionButton)btnArray[index]).completeMission(newType, newMaxTime);
    }

    public void setDone(int i) {
        ((MissionButton)btnArray[i]).setDone();
    }

    public int getNumMissions() {
        return index;
    }

    public void updateProgress(int i, int currentTime) {
        ((MissionButton)btnArray[i]).setCurrentTime(currentTime);
        btnArray[i].repaint();
    }
}
