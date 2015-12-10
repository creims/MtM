package MtM.view;

import MtM.model.business.manager.Game;
import MtM.model.business.manager.SaveManager;
import MtM.model.domain.Minion;
import MtM.model.domain.Mission;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Colin
 */
public class MtMGUI extends javax.swing.JFrame {

    private final Timer timer;
    private final SaveManager saveManager;
    private final Game game;

    /**
     * should be called after a game is loaded to update things
     */
    private void updateOnLoad() {
        this.setTitle("Manage the Minions - " + saveManager.getSaveFile());

        labelCatnipNum.setText("" + game.getCatnip());
        minionPanel.clear();
        for (Minion m : game.getMinions()) {
            if (m == null) {
                break;
            }
            minionPanel.addMinionBtn(m.getName());
        }

        for (Mission m : game.getMissions()) {
            if (m == null) {
                break;
            }
            addMission(m);
        }
        SwingUtilities.invokeLater(() -> {
            updateViewPanel();
        });
    }

    private void saveAs() {
        //Create a file chooser
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(SaveManager.SAVE_PATH));
        int returnVal = fc.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            saveManager.saveGame(file.getName());
            log("Game saved as: " + file.getName());
        }
    }

    private void log(String s) {
        log.append(s + '\n');
    }

    private void openFile() {
        //Create a file chooser
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(SaveManager.SAVE_PATH));
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            if (SaveManager.loadGame(file.getName())) {
                updateOnLoad();
                log("Game loaded: " + file.getName());
            } else {
                log("Load FAILED: " + file.getName());
            }

        }
    }

    /**
     * Registers stuff that happens when the program exits
     */
    private void initOnExit() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent winEvt) {
                timer.stop();
                saveManager.saveGame();
                saveManager.saveProps();
                System.exit(0);
            }
        });
    }

    private void scrollToBottom(JScrollPane scrollPane) {
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = scrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }

    private void updateMissionActionButton() {
        Mission m = game.getMission(missionPanel.getSelected());

        if (m.isActive()) {
            missionActionButton.setText("Complete");
            if (m.isDone()) {
                missionActionButton.setEnabled(true);
            } else {
                missionActionButton.setEnabled(false);
            }
        } else {
            missionActionButton.setText("Assign");
            if (game.getMinionActive(minionPanel.getSelected())) {
                missionActionButton.setEnabled(false);
            } else {
                missionActionButton.setEnabled(true);
            }
        }
    }

    /**
     * Handles tick updates
     */
    class Updater implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Mission[] activeMissions = game.getActiveMissions();
            for (int i = 0; i < activeMissions.length; i++) {
                Mission m = activeMissions[i];

                if (m == null) {
                    continue;
                }
                //mission is done
                if (m.update()) {
                    missionPanel.setDone(i);
                    log(game.getMinionName(i) + " has finished a mission. Go claim the reward!");
                } else {
                    missionPanel.updateProgress(i, m.getCurrentTime());
                }

            }
        }

    }

    /**
     * Creates new form MtM
     */
    public MtMGUI() {
        initComponents();
        saveManager = new SaveManager();
        game = saveManager.getGame();
        timer = new Timer(game.getTickRate(), new Updater());

        minionPanel.addActionListener((ActionEvent e) -> {
            int minionID = Integer.parseInt(e.getActionCommand());
            updateMinionDisplay(minionID);
        });

        missionPanel.addActionListener((ActionEvent e) -> {
            int missionID = Integer.parseInt(e.getActionCommand());
            updateMissionDisplay(missionID);
        });

        updateOnLoad();

        mScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        timer.start();
    }

    public void updateMinionDisplay(int minionID) {
        if (minionPanel.getSelected() != missionPanel.getSelected() && minionPanel.getActive(minionPanel.getSelected())) {
            missionPanel.processSelection(minionID);
            missionPanel.setSelection(minionID);
        }

        minionDisplay.setText(game.printMinion(minionID));

        updateMissionActionButton();
    }

    public void updateMissionDisplay(int missionID) {
        if (minionPanel.getSelected() != missionPanel.getSelected() && missionPanel.getActive(missionPanel.getSelected())) {
            minionPanel.processSelection(missionID);
            minionPanel.setSelection(missionID);
        }

        missionDisplay.setText(game.printMission(missionID));

        updateMissionActionButton();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logScrollPane = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();
        minionDisplayScrollPane = new javax.swing.JScrollPane();
        minionDisplay = new javax.swing.JTextArea();
        missionDisplayScrollPane = new javax.swing.JScrollPane();
        missionDisplay = new javax.swing.JTextArea();
        mScrollPane = new javax.swing.JScrollPane();
        mViewPanel = new javax.swing.JPanel();
        minionPanel = new MtM.view.swingcomponents.MinionPanel();
        missionPanel = new MtM.view.swingcomponents.MissionPanel();
        labelCatnip = new javax.swing.JLabel();
        labelCatnipNum = new javax.swing.JLabel();
        rosterButton = new javax.swing.JButton();
        missionActionButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        fileSave = new javax.swing.JMenuItem();
        fileSaveAs = new javax.swing.JMenuItem();
        fileOpen = new javax.swing.JMenuItem();
        fileExit = new javax.swing.JMenuItem();
        debugMenu = new javax.swing.JMenu();
        debugSetTickRate = new javax.swing.JMenuItem();
        debugAddMinion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 153, 255));
        setResizable(false);

        log.setEditable(false);
        log.setColumns(20);
        log.setRows(5);
        log.setFocusable(false);
        logScrollPane.setViewportView(log);

        minionDisplayScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        minionDisplay.setEditable(false);
        minionDisplay.setColumns(20);
        minionDisplay.setRows(5);
        minionDisplay.setFocusable(false);
        minionDisplayScrollPane.setViewportView(minionDisplay);

        missionDisplayScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        missionDisplay.setEditable(false);
        missionDisplay.setColumns(20);
        missionDisplay.setRows(5);
        missionDisplay.setFocusable(false);
        missionDisplay.setPreferredSize(new java.awt.Dimension(130, 94));
        missionDisplayScrollPane.setViewportView(missionDisplay);

        mScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mViewPanel.setAutoscrolls(true);
        mViewPanel.setPreferredSize(new java.awt.Dimension(420, 350));
        mViewPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        minionPanel.setAutoscrolls(true);
        minionPanel.setPreferredSize(new java.awt.Dimension(200, 350));
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 5);
        flowLayout2.setAlignOnBaseline(true);
        minionPanel.setLayout(flowLayout2);
        mViewPanel.add(minionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        missionPanel.setMaximumSize(new java.awt.Dimension(200, 350));
        missionPanel.setPreferredSize(new java.awt.Dimension(200, 350));
        missionPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 5));
        mViewPanel.add(missionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 0, -1, -1));

        mScrollPane.setViewportView(mViewPanel);

        labelCatnip.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        labelCatnip.setText(" Catnip:");

        labelCatnipNum.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        labelCatnipNum.setText("##########");

        rosterButton.setText("Roster");

        missionActionButton.setFont(missionActionButton.getFont().deriveFont(missionActionButton.getFont().getStyle() | java.awt.Font.BOLD, missionActionButton.getFont().getSize()+14));
        missionActionButton.setText("Assign");
        missionActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                missionActionButtonActionPerformed(evt);
            }
        });

        fileMenu.setText("File");

        fileSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        fileSave.setText("Save");
        fileSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileSaveActionPerformed(evt);
            }
        });
        fileMenu.add(fileSave);

        fileSaveAs.setText("Save As...");
        fileSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileSaveAsActionPerformed(evt);
            }
        });
        fileMenu.add(fileSaveAs);

        fileOpen.setText("Open...");
        fileOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileOpenActionPerformed(evt);
            }
        });
        fileMenu.add(fileOpen);

        fileExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        fileExit.setText("Exit");
        fileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileExitActionPerformed(evt);
            }
        });
        fileMenu.add(fileExit);

        menuBar.add(fileMenu);

        debugMenu.setText("Debug");

        debugSetTickRate.setText("Set Tick Rate");
        debugSetTickRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugSetTickRateActionPerformed(evt);
            }
        });
        debugMenu.add(debugSetTickRate);

        debugAddMinion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        debugAddMinion.setText("Add Minion");
        debugAddMinion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugAddMinionActionPerformed(evt);
            }
        });
        debugMenu.add(debugAddMinion);

        menuBar.add(debugMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(minionDisplayScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelCatnip, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelCatnipNum, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rosterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(missionDisplayScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(missionActionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(minionDisplayScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rosterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelCatnip, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelCatnipNum, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)
                        .addComponent(mScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(missionDisplayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(missionActionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileSaveAsActionPerformed
        saveAs();
    }//GEN-LAST:event_fileSaveAsActionPerformed

    private void fileSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileSaveActionPerformed
        if (!saveManager.saveGame()) {
            saveAs();
        } else {
            log("Game saved.");
        }
    }//GEN-LAST:event_fileSaveActionPerformed

    private void debugSetTickRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugSetTickRateActionPerformed
        int newTickRate = Integer.parseInt(JOptionPane.showInputDialog(this, "New tick rate:"));
        timer.setDelay(newTickRate);
        timer.restart();
    }//GEN-LAST:event_debugSetTickRateActionPerformed

    private void fileOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileOpenActionPerformed
        openFile();
    }//GEN-LAST:event_fileOpenActionPerformed

    private void fileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_fileExitActionPerformed

    private void debugAddMinionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugAddMinionActionPerformed
        addMinion();
    }//GEN-LAST:event_debugAddMinionActionPerformed

    private void missionActionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_missionActionButtonActionPerformed
        if (missionActionButton.getText().equals("Assign")) {
            beginMission();
        } else {
            completeMission();
        }
    }//GEN-LAST:event_missionActionButtonActionPerformed

    private void beginMission() {
        int minionID = minionPanel.getSelected();
        int missionID = missionPanel.getSelected();

        game.swapMissions(missionID, minionID);
        missionPanel.swap(missionID, minionID);
        missionPanel.processSelection(minionID);

        minionPanel.setActive(minionID, true);
        missionPanel.setActive(minionID, true);

        game.activateMission(minionID);

        log(game.getMinionName(minionID) + " started a "
                + game.getMission(minionID).getType().toString() + " mission.");

        missionDisplay.setText(game.printMission(minionID));
        updateMissionActionButton();
        updateViewPanel();
    }

    private void completeMission() {
        int minionID = minionPanel.getSelected();
        int missionID = missionPanel.getSelected();

        Mission m = game.getMission(missionID);

        log(game.getMinionName(minionID) + " completed a " + m.getType().toString()
                + " mission! Gained " + m.getReward() + " catnip.");

        game.completeMission(missionID);
        Mission newMission = game.getMission(missionID);
        missionPanel.completeMission(missionID, newMission.getType().toString(), newMission.getTimeRequired());
        missionPanel.processSelection(minionID);

        log("New " + newMission.getType().toString() + " mission discovered.");

        minionPanel.setActive(minionID, false);

        updateCatnip();
        updateViewPanel();
    }

    private void updateCatnip() {
        labelCatnipNum.setText("" + game.getCatnip());
    }

    private void updateViewPanel() {
        int newHeight = Math.max(minionPanel.getHeight(), missionPanel.getHeight());
        mViewPanel.setPreferredSize(new Dimension(mViewPanel.getWidth(), newHeight));
        mViewPanel.revalidate();
        mViewPanel.repaint();
        //scrollToBottom(mScrollPane);
    }

    private void addMinion() {
        minionPanel.addMinionBtn(game.getMinionName(minionPanel.getNumMinions()));
        updateViewPanel();
    }

    private void addMission(Mission m) {
        missionPanel.addMissionBtn(m.getType().toString(), m.getTimeRequired());
        updateViewPanel();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MtMGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            MtMGUI gui = new MtMGUI();
            gui.setVisible(true);
            gui.initOnExit();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem debugAddMinion;
    private javax.swing.JMenu debugMenu;
    private javax.swing.JMenuItem debugSetTickRate;
    private javax.swing.JMenuItem fileExit;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem fileOpen;
    private javax.swing.JMenuItem fileSave;
    private javax.swing.JMenuItem fileSaveAs;
    private javax.swing.JLabel labelCatnip;
    private javax.swing.JLabel labelCatnipNum;
    private javax.swing.JTextArea log;
    private javax.swing.JScrollPane logScrollPane;
    private javax.swing.JScrollPane mScrollPane;
    private javax.swing.JPanel mViewPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextArea minionDisplay;
    private javax.swing.JScrollPane minionDisplayScrollPane;
    private MtM.view.swingcomponents.MinionPanel minionPanel;
    private javax.swing.JButton missionActionButton;
    private javax.swing.JTextArea missionDisplay;
    private javax.swing.JScrollPane missionDisplayScrollPane;
    private MtM.view.swingcomponents.MissionPanel missionPanel;
    private javax.swing.JButton rosterButton;
    // End of variables declaration//GEN-END:variables

}
