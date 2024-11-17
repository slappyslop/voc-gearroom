package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Got the idea to use lambda functions for listeners here
 * https://www.codejava.net/java-core/the-java-language/java-8-lambda-listener-example
 */
public class LoginPanel extends JPanel {
    private static int BUTTON_Y = 2;
    private static int LABEL_X = 1;

    private JButton leader;
    private JButton member;
    private JButton gearMaster;
    private JTextField nameField;
    private GUI gui;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JTextField userField;

    // EFFECTS: displays the login Screen
    public LoginPanel(GUI gui) {
        this.gui = gui;
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(gbl);
        makeLabel("Name", 1);
        makeLabel("Login as:", 2);
        leader = makeButton("Leader", 2);
        gearMaster = makeButton("Gear Master", 3);
        member = makeButton("Member", 4);
        userField = makeNameField();

        gearMaster.addActionListener(new GearMasterListener());
        leader.addActionListener(new LeaderListener());
        member.addActionListener(new MemberListener());

    }

    // MODIFIES: this
    // EFFECTS: Creates the username field
    private JTextField makeNameField() {
        nameField = new JTextField(20);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameField, gbc);
        return nameField;
    }

    // MODIFIES: this
    // EFFECTS: Creates a button with name and x position
    private JButton makeButton(String name, int x) {
        JButton button = new JButton(name);
        gbc.gridx = x;
        gbc.gridy = BUTTON_Y;
        gbc.gridwidth = 1;
        add(button, gbc);
        return button;
    }

    // MODIFIES: this
    // EFFECTS: Creates a label with name and y position
    private void makeLabel(String name, int y) {
        JLabel label = new JLabel(name);
        gbc.gridx = LABEL_X;
        gbc.gridy = y;
        add(label, gbc);
    }

    // Handles gear master button click
    private class GearMasterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = userField.getText();
            if (!username.equals("")) {
                gui.logIn("gear master", username);
            }

        }

    }

    // Handles leader button click
    private class LeaderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = userField.getText();
            if (!username.equals("")) {
                gui.logIn("leader", username);
            }
        }

    }

    // Handles member button click
    private class MemberListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = userField.getText();
            if (!username.equals("")) {
                gui.logIn("member", username);
            }
        }

    }

}
