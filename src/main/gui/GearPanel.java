package gui;

import javax.swing.JPanel;

import model.Member;

public class GearPanel extends JPanel {
    private Member currentMember;

    GearPanel() {
        currentMember = null;
    }

    public void setCurrentMember(Member currentMember) {
        this.currentMember = currentMember;
    }
}
