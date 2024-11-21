package gui;

import javax.swing.JPanel;

import model.GearRoom;
import model.Trip;

public class TripPanel extends JPanel {
    Trip trip;
    GUI gui;
    GearRoom gearRoom;

    TripPanel(Trip trip, GUI gui, GearRoom gr) {
        this.trip = trip;
        this.gui = gui;
        this.gearRoom = gr;
    }
}
