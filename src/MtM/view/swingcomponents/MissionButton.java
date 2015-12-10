package MtM.view.swingcomponents;

import static MtM.model.domain.MtMUtil.toPercent;
import java.awt.CardLayout;

/**
 *
 * @author Colin
 */
public class MissionButton extends MButton {

    private final CardLayout statusPanelLayout;
    private int maxTime;
    private int currentTime;

    public MissionButton(int maxTime) {
        super();
        initComponents();
        this.maxTime = maxTime;
        this.currentTime = 0;
        statusPanelLayout = (CardLayout) statusPanel.getLayout();
    }

    public void setAvailable() {
        statusPanelLayout.show(statusPanel, "available");
    }

    public void setDone() {
        statusPanelLayout.show(statusPanel, "done");
    }
    
    public void completeMission(String newType, int maxTime) {
        currentTime = 0;
        this.maxTime = maxTime;
        setAvailable();
        typeLabel.setText(newType);
        setActive(false);
    }

    @Override
    public void setActive(boolean state) {
        super.setActive(state);
        progressBar.setToolTipText(currentTime + " / " + maxTime);
        
        if(state) {
            statusPanelLayout.show(statusPanel, "active");
        } else {
            statusPanelLayout.show(statusPanel, "available");
        }
    }
    
    public void setCurrentTime(int time) {
        currentTime = time;
        progressBar.setValue(toPercent(currentTime, maxTime));
        progressBar.setToolTipText(currentTime + " / " + maxTime);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusPanel = new javax.swing.JPanel();
        availableLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        doneLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(10000, 10000));
        setPreferredSize(new java.awt.Dimension(80, 70));

        statusPanel.setPreferredSize(new java.awt.Dimension(80, 40));
        statusPanel.setLayout(new java.awt.CardLayout());

        availableLabel.setBackground(new java.awt.Color(153, 204, 255));
        availableLabel.setFont(availableLabel.getFont().deriveFont((availableLabel.getFont().getStyle() | java.awt.Font.ITALIC), availableLabel.getFont().getSize()+6));
        availableLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availableLabel.setText("Available");
        availableLabel.setOpaque(true);
        availableLabel.setPreferredSize(new java.awt.Dimension(80, 40));
        statusPanel.add(availableLabel, "available");

        progressBar.setToolTipText("");
        progressBar.setMaximumSize(new java.awt.Dimension(80, 40));
        progressBar.setMinimumSize(new java.awt.Dimension(80, 20));
        progressBar.setOpaque(true);
        progressBar.setPreferredSize(new java.awt.Dimension(80, 40));
        progressBar.setStringPainted(true);
        statusPanel.add(progressBar, "active");

        doneLabel.setBackground(new java.awt.Color(102, 255, 0));
        doneLabel.setFont(doneLabel.getFont().deriveFont((doneLabel.getFont().getStyle() | java.awt.Font.ITALIC), doneLabel.getFont().getSize()+6));
        doneLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        doneLabel.setText("DONE");
        doneLabel.setMaximumSize(new java.awt.Dimension(80, 20));
        doneLabel.setMinimumSize(new java.awt.Dimension(80, 20));
        doneLabel.setOpaque(true);
        doneLabel.setPreferredSize(new java.awt.Dimension(80, 20));
        statusPanel.add(doneLabel, "done");

        typeLabel.setFont(typeLabel.getFont().deriveFont(typeLabel.getFont().getSize()+11f));
        typeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        typeLabel.setText("Type");
        typeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        typeLabel.setMaximumSize(new java.awt.Dimension(80, 40));
        typeLabel.setMinimumSize(new java.awt.Dimension(80, 40));
        typeLabel.setPreferredSize(new java.awt.Dimension(80, 40));

        setLayer(statusPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(typeLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(typeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(typeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel availableLabel;
    private javax.swing.JLabel doneLabel;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables

    void setMissionType(String type) {
        typeLabel.setText(type);
    }

}