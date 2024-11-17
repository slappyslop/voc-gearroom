package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Member;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Main GUI class that handles the GUI
 */
public class GUI {
    private int FRAME_HEIGHT = 200;
    private int FRAME_WIDTH = 500;
    private JFrame frame;
    private JPanel containerPanel;
    private LoginPanel login;
    private GearPanel gearPanel;
    private AgendaPanel agendaPanel;
    private CardLayout crd;
    private Member currentMember;

    //EFFECTS: creates a GUI that manages all the panels
    public GUI() {
        crd = new CardLayout();
        frame = new JFrame();
        containerPanel = new JPanel(crd);
        gearPanel = new GearPanel();
        agendaPanel = new AgendaPanel();
        login = new LoginPanel(this);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        containerPanel.add(gearPanel, "gear");
        containerPanel.add(agendaPanel, "agenda");
        containerPanel.add(login, "login");
        frame.add(containerPanel);
        crd.show(containerPanel, "login");

        frame.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: logs a user into the app and displays the next screen according to their role
    public void logIn(String role, String userName) {
        currentMember = new Member(userName);
        currentMember.setLogInState(role);

        if (role.equals("gear master")) {
            gearPanel.setCurrentMember(currentMember);
            crd.show(containerPanel, "gear");
        } else {
            agendaPanel.setCurrentMember(currentMember);
            crd.show(containerPanel, "agenda");
        }
        
        
    }

    // EFFECTS: runs the gui
    public static void main(String[] args) {
        new GUI();
    }

}
