package gui;

import javax.swing.JFrame;


public class GUI {
    private int FRAME_HEIGHT = 200;
    private int FRAME_WIDTH = 500;
    private JFrame frame;
    private LoginPanel panel;

    public GUI() {
        frame = new JFrame();
        panel = new LoginPanel();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        new GUI();
    }
}
