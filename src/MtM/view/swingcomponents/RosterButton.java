package MtM.view.swingcomponents;

import MtM.model.domain.Minion;
import MtM.model.domain.MinionRosterEntry;
import MtM.model.domain.MtMUtil;

/**
 *
 * @author Colin
 */
public class RosterButton extends MButton {

    private int cost;
    private Minion minion;

    /**
     * Creates new form RosterButton
     *
     * @param e
     */
    public RosterButton(MinionRosterEntry e) {
        super();
        initComponents();
        minion = e.getMinion();
        cost = e.getCost();

        statNumDisplayArea.setText("" + MtMUtil.round(minion.getGathering(), 2) 
                + '\n' + MtMUtil.round(minion.getPerception(), 2) 
                + '\n' + MtMUtil.round(minion.getFighting(), 2) + '\n' 
                + MtMUtil.round(minion.getTech(), 2));
        costLabel.setText("Cost: " + cost);
        nameLabel.setText(minion.getName());
    }

    /**
     * Get the value of cost
     *
     * @return the value of cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Set the value of cost
     *
     * @param cost new value of cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        statTextArea = new javax.swing.JTextArea();
        statNumDisplayArea = new javax.swing.JTextArea();
        portraitPanel = new javax.swing.JPanel();
        costLabel = new javax.swing.JLabel();

        setFocusable(false);
        setPreferredSize(new java.awt.Dimension(300, 140));
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);

        nameLabel.setFont(nameLabel.getFont().deriveFont(nameLabel.getFont().getStyle() | java.awt.Font.BOLD, nameLabel.getFont().getSize()+12));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setText("Name");
        nameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nameLabel.setFocusable(false);
        nameLabel.setRequestFocusEnabled(false);

        statTextArea.setEditable(false);
        statTextArea.setBackground(new java.awt.Color(240, 240, 240));
        statTextArea.setColumns(20);
        statTextArea.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        statTextArea.setRows(5);
        statTextArea.setText("Gathering:\nPerception:\nFighting:\nTech:");
        statTextArea.setFocusable(false);
        statTextArea.setOpaque(false);
        statTextArea.setRequestFocusEnabled(false);

        statNumDisplayArea.setEditable(false);
        statNumDisplayArea.setBackground(new java.awt.Color(240, 240, 240));
        statNumDisplayArea.setColumns(20);
        statNumDisplayArea.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        statNumDisplayArea.setRows(5);
        statNumDisplayArea.setText("stats\n");
        statNumDisplayArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        statNumDisplayArea.setFocusable(false);
        statNumDisplayArea.setOpaque(false);

        portraitPanel.setBackground(new java.awt.Color(153, 204, 255));
        portraitPanel.setMinimumSize(new java.awt.Dimension(120, 120));
        portraitPanel.setPreferredSize(new java.awt.Dimension(120, 120));

        javax.swing.GroupLayout portraitPanelLayout = new javax.swing.GroupLayout(portraitPanel);
        portraitPanel.setLayout(portraitPanelLayout);
        portraitPanelLayout.setHorizontalGroup(
            portraitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        portraitPanelLayout.setVerticalGroup(
            portraitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        costLabel.setFont(costLabel.getFont().deriveFont(costLabel.getFont().getSize()+5f));
        costLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        costLabel.setText("Cost: XXXX");
        costLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        setLayer(nameLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(statTextArea, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(statNumDisplayArea, javax.swing.JLayeredPane.PALETTE_LAYER);
        setLayer(portraitPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(costLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statNumDisplayArea, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(portraitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(portraitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(costLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statNumDisplayArea, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel costLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JPanel portraitPanel;
    private javax.swing.JTextArea statNumDisplayArea;
    private javax.swing.JTextArea statTextArea;
    // End of variables declaration//GEN-END:variables
}