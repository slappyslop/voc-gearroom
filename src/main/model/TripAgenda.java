package model;

import java.util.ArrayList;
import java.util.List;

//Represents the trip agenda, a list of club trips
public class TripAgenda {

    private List<Trip> agenda;

    public TripAgenda() {
        this.agenda = new ArrayList<>();
    }

    //REQUIRES: trip must not already be in agenda
    //MODIFIES: this
    //EFFECTS: Adds trips to the trip agenda
    public void addTrip(Trip t) {
        agenda.add(t);
    }

    public List<Trip> getTrips() {
        return agenda;
    }
    

}
