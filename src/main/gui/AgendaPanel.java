package gui;

import javax.swing.JPanel;

import model.Member;

/*
 * Panel that handles the trip agenda screen
 */
public class AgendaPanel extends JPanel {
    private Member currentMember;

    public AgendaPanel() {
        currentMember = null;
    }

    public void setCurrentMember(Member currentMember) {
        this.currentMember = currentMember;
    }

}
