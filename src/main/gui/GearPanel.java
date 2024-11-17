package gui;

import javax.swing.JPanel;

import model.Member;

/*
 * Panel that handles the gear screen
 */
public class GearPanel extends JPanel {
    private Member currentMember;

    //Displays the gear view scfeen
    GearPanel() {
        currentMember = null;
    }

    public void setCurrentMember(Member currentMember) {
        this.currentMember = currentMember;
    }
}
