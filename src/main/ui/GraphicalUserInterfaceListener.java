package ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import model.Event;
import model.EventLog;

public class GraphicalUserInterfaceListener implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {
        for (Event el : EventLog.getInstance()) {
            System.out.println(el);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
