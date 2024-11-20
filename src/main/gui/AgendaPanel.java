package gui;

import javax.swing.JPanel;

import model.Member;

/*
 * Panel that handles the trip agenda screen
 */
public class AgendaPanel extends JPanel {
    private Member currentMember;
    private GUI gui;
    
    public AgendaPanel(Member m, GUI gui) {
        this.gui = gui;
        currentMember = m;
        
    }

    public void setCurrentMember(Member currentMember) {
        this.currentMember = currentMember;
    }

}
