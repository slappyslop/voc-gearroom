package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.crypto.URIReferenceException;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Got the idea to use lambda functions for listeners here
 * https://www.codejava.net/java-core/the-java-language/java-8-lambda-listener-example
 */
public class LoginPanel extends JPanel {
    private int BUTTON_WIDTH = 100;
    private int BUTTON_HEIGHT = 40;
    private int BUTTON_Y = 50;
    private int LABEL_X = 10;
    private int LABEL_WIDTH = 80;
    private int LABEL_HEIGHT = 25;
    private JButton leader;
    private JButton member;
    private JButton gearMaster;
    private JTextField nameField;
    private GUI gui;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JTextField userField;

    public LoginPanel (GUI gui) {
        this.gui = gui;
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(gbl);
        makeLabel("Name", 20);
        makeLabel("Login as:", 50);
        leader = makeButton("Leader", 100);
        gearMaster = makeButton("Gear Master", 200);
        member = makeButton("Member", 300);
        userField = makeNameField();

        gearMaster.addActionListener(new GearMasterListener());
        leader.addActionListener(new LeaderListener());
        member.addActionListener(new MemberListener());

    }


    private JTextField makeNameField() {
        nameField = new JTextField(20);
        gbc.gridx = 100;
        gbc.gridy = 20;
        gbc.gridwidth = 165;
        gbc.gridheight = 25;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbl.setConstraints(nameField, gbc);
        add(nameField);
        return nameField;
    }


    private JButton makeButton(String name, int x) {
        JButton button = new JButton(name);
        gbc.gridx = x;
        gbc.gridy = BUTTON_Y;
        gbc.gridwidth = BUTTON_WIDTH;
        gbc.gridheight = BUTTON_HEIGHT;
        gbc.fill = GridBagConstraints.NONE;
        gbl.setConstraints(button, gbc);
        add(button);
        return button;
    }

    private void makeLabel(String name, int y) {
        JLabel label = new JLabel(name);
        gbc.gridx = LABEL_X;
        gbc.gridy = y;
        gbc.gridwidth = LABEL_WIDTH;
        gbc.gridheight = LABEL_HEIGHT;
        gbl.setConstraints(label, gbc);
        label.setBounds(LABEL_X, y, LABEL_WIDTH, LABEL_HEIGHT);
        add(label);
    }

    private class GearMasterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = userField.getText();
            if (!username.equals("")) {
                gui.logIn("gear", username);
            }

        }

    }

    private class LeaderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = userField.getText();
            if (!username.equals("")) {
                gui.logIn("leader", username);
            }
        }

    }

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
