package gui;

import javax.swing.JPanel;

import model.Trip;

public class TripPanel extends JPanel {
    Trip trip;

    TripPanel(Trip trip) {
        this.trip = trip;
    }
}
