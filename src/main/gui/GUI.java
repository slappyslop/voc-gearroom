package gui;

import java.awt.CardLayout;


import model.Member;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Main GUI class that handles the GUI
 */
public class GUI extends JFrame {
    private static int FRAME_HEIGHT = 200;
    private static int FRAME_WIDTH = 500;
    private JPanel containerPanel;
    private LoginPanel login;
    private GearPanel gearPanel;
    private AgendaPanel agendaPanel;
    private CardLayout crd;
    private Member currentMember;

    //EFFECTS: creates a GUI that manages all the panels
    public GUI() {
        super("Trip App");
        crd = new CardLayout();
        containerPanel = new JPanel(crd);
        login = new LoginPanel(this);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        containerPanel.add(login, "login");
        add(containerPanel);
        crd.show(containerPanel, "login");
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: logs a user into the app and displays the next screen according to their role
    public void logIn(String role, String userName) {
        currentMember = new Member(userName);
        currentMember.setLogInState(role);

        if (role.equals("gear master")) {
            gearPanel = new GearPanel(currentMember, this);
            containerPanel.add(gearPanel, "gear");
            crd.show(containerPanel, "gear");
        } else {
            agendaPanel = new AgendaPanel(currentMember, this);
            containerPanel.add(agendaPanel, "agenda");
            crd.show(containerPanel, "agenda");
        }
        
        
    }

    // EFFECTS: runs the gui
    public static void main(String[] args) {
        new GUI();
    }

    // MODIFIES: this
    // EFFECTS: Logs out of the program resetting everything
    public void logOut() {
       new GUI();
    }

}
