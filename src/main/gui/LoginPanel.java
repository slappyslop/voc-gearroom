package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
    private int BUTTON_WIDTH = 100;
    private int BUTTON_HEIGHT = 40;
    private int BUTTON_Y = 50;
    private int LABEL_X = 10;
    private int LABEL_WIDTH = 80;
    private int LABEL_HEIGHT = 25;
    private JTextField nameField;

    public LoginPanel() {
        setLayout(null);

        makeLabel("Name", 20);
        makeLabel("Login as", 50);

        nameField = new JTextField(20);
        nameField.setBounds(100, 20, 165, 25);
        add(nameField);
        makeButton("Leader", 100);
        makeButton("Gear Master", 200);
        makeButton("Member", 300);
    }

    private void makeButton(String name, int x) {
        JButton button = new JButton(name);
        button.setBounds(x, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(button);
    }

    private void makeLabel(String name, int y) {
        JLabel label = new JLabel(name);
        label.setBounds(LABEL_X, y, LABEL_WIDTH, LABEL_HEIGHT);
        add(label);
    }


}
