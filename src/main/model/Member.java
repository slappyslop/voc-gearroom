package model;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private List<Trip> committed;
    private List<Trip> interested;
    private List<Trip> going;
    private List<String> myGear;
    private String name;


    public Member(String name){
        this.name = name;
        committed = new ArrayList<Trip>();
        interested = new ArrayList<Trip>();
        going = new ArrayList<Trip>();
        myGear = new ArrayList<String>();

    }

    //MODIFIES: this, t
    //EFFECTS: Adds member to committed list of trip, latest member last, also adds trip to committed list of member
    public void registerCommitted(Trip t) {
        committed.add(t);
        t.addToCommitted(this);
    }

    //MODIFIES: this, t
    //EFFECTS: Adds member to interested list of trip, latest member last, also adds trip to interested list of member
    public void registerInterested(Trip t) {
        interested.add(t);
        t.addToInterested(this);
    }
    //MODIFIES: this
    //EFFECTS: Adds a piece of gear to your owned list, this means you dont want to rent it.
    public void addToMyGear(String s) {
        myGear.add(s);
    }

    public void registerGoing(Trip t){
        going.add(t);
    }

    public String getName() {
        return name;
    }

    public List<Trip> getCommittedTrips() {
        return committed;
    }

    public List<Trip> getInterestedTrips(){
        return interested;
    }

    public List<Trip> getGoingTrips(){
        return going;
    }

    public List<String> getMyGear(){
        return myGear;
    }




}
