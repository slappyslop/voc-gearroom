package model;

import java.util.ArrayList;
import java.util.List;

//Represents a club member having name, names of gear they already own, and a log of trips they have interacted with
public class Member {
    private List<String> myGear; //a list of names of gear the member owns
    private String name; // name of the member
    private String logInState;

    //Requires: name is unique
    public Member(String name) {
        this.name = name;
        myGear = new ArrayList<String>();

    }

    //MODIFIES: this, t
    //EFFECTS: Adds member to committed list of trip, latest member last, also adds trip to committed list of member,
    //         returns list of gear that was unable to be reserved in the clubroom
    public List<String> registerCommitted(Trip t, GearRoom gr) {
        return t.addToCommitted(this, gr);
    }

    //MODIFIES: this, t
    //EFFECTS: Adds member to interested list of trip, latest member last, also adds trip to interested list of member
    public void registerInterested(Trip t) {
        t.addToInterested(this);
    }

    //MODIFIES: this
    //EFFECTS: Adds a piece of gear to your owned list, this means you dont want to rent it.
    public void addToMyGear(String s) {
        myGear.add(s);
    }


    public void setLogInState(String s) {
        this.logInState = s;
    }

    public String getName() {
        return name;
    }

    public List<String> getMyGear() {
        return myGear;
    }

    public String getLogInState() {
        return logInState;
    }




}
