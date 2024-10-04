package model;

import java.util.ArrayList;
import java.util.List;

public class TripAgenda {

    private List<Trip> agenda;

    TripAgenda() {
        this.agenda = new ArrayList<>();
    }
    //REQUIRES: trip must not already be in agenda
    //MODIFIES: this
    //EFFECTS: Adds trips to the trip agenda
    public void addTrip(Trip t){
        agenda.add(t);
    }

    public List<Trip> getTrips(){
    return agenda;
    }
    

}
