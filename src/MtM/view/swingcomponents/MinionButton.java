package MtM.view.swingcomponents;

import MtM.model.domain.Minion;

/**
 *
 * @author Colin
 */
public class MinionButton extends MButton {

    /**
     * Creates new form MinionButton
     * @param m the associated minion
     */
    public MinionButton(Minion m) {
        super();
        initComponents();
        nameLabel.setText(m.getName());
        setActive(m.isActive());
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

        nameLabel.setBackground(new java.awt.Color(255, 255, 255));
        nameLabel.setFont(nameLabel.getFont().deriveFont(nameLabel.getFont().getSize()+15f));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setText("NAME");
        nameLabel.setMaximumSize(new java.awt.Dimension(80, 40));
        nameLabel.setMinimumSize(new java.awt.Dimension(80, 40));
        nameLabel.setPreferredSize(new java.awt.Dimension(80, 40));

        setLayer(nameLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}
