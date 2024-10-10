package model;

import java.util.List;

public class Member {

    public Member(String name){

    }

    //MODIFIES: this, t
    //EFFECTS: Adds member to committed list of trip, latest member last, also adds trip to committed list of member
    public void registerCommitted(Trip t) {
        // TODO
        t.addToCommitted(this);
    }

    //MODIFIES: this, t
    //EFFECTS: Adds member to interested list of trip, latest member last, also adds trip to interested list of member
    public void registerInterested(Trip t) {
        //TODO
        t.addToInterested(this);
    }

    public String getName() {
        return "";
    }

    public List<Trip> getCommittedTrips() {
        return null;
    }

    public List<Trip> getInterestedTrips(){
        return null;
    }

    public List<Trip> getGoingTrips(){
        return null;
    }




}
