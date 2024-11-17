package gui;

import javax.swing.JPanel;

import model.Member;

public class AgendaPanel extends JPanel {
    private Member currentMember;

    public AgendaPanel() {
        currentMember = null;
    }

    public void setCurrentMember(Member currentMember) {
        this.currentMember = currentMember;
    }

}
