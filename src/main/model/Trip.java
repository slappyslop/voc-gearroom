package model;

import java.util.ArrayList;
import java.util.List;

public class Trip {

    private List<Member> going;
    private List<Member> committed;
    private List<Member> interested;

    public Trip(){
        going = new ArrayList<Member>();
        committed = new ArrayList<Member>();
        interested = new ArrayList<Member>();


    }
    //REQUIRES: Must be invoked by trip leader only
    //MODIFIES: this
    //EFFECTS: Adds the member to the going list of the trip
    public void addToGoing(Member m) {
        going.add(m);

    }
    //MODIFIES: this
    //EFFECTS: Adds the member to the committed list of the trip
    public void addToCommitted(Member m){
        committed.add(m);
    }

    //MODIFIES: this
    //EFFECTS: Adds the member to the committed list of the trip
    public void addToInterested(Member m){
        interested.add(m);

    }

    //EFFECTS: Returns list of going members on trip
    public List<Member> getGoing() {
        return going;
    }

    //EFFECTS: Returns list of committed members
    public List<Member> getCommitted() {
        return committed;
    }

    //EFFECTS: Returns list of interested members
    public List<Member> getInterested() {
        return interested;

    }

}
