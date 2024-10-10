package model;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    
    private GearRoom gr;
    private List<Member> going;
    private List<Member> committed;
    private List<Member> interested;
    private int startDate;
    private int endDate;

    public Trip(GearRoom gr){ 
        going = new ArrayList<Member>();
        committed = new ArrayList<Member>();
        interested = new ArrayList<Member>();
        this.gr = gr;


    }
    //REQUIRES: Must be invoked by trip leader only
    //MODIFIES: this
    //EFFECTS: Adds the member to the going list of the trip, 
    //          adds trip to going of member
    public void addToGoing(Member m) {
        going.add(m);
        m.registerGoing(this);

    }
    //MODIFIES: this
    //EFFECTS: Adds the member to the committed list of the trip, 
    //         returns true if there is enough gear in the club room
    //         false if there is not.
    public boolean addToCommitted(Member m) {
        committed.add(m);
        return checkEnoughGear();
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
    //MODIFIES: this, gr
    //EFFECTS: Checks if there is enough unreserved gear for one additional person in the gear room
    //         Marks gear as reserved and returns true if there is, returns false if no gear
    public boolean checkEnoughGear() {
        for(Gear g : gr.getGearRoom()) {
            if (!g.isReserved(startDate, endDate)) {
                g.reserve(startDate, endDate);
                return true;
            }
        }
        return false;
    }

    //EFFECTS: Returns list of committed members
    public List<Member> getCommitted() {
        return committed;
    }

    //EFFECTS: Returns list of interested members
    public List<Member> getInterested() {
        return interested;

    }

    public void setStartDate(int start) {
        this.startDate = start;
    }
    
    public void setEndDate(int end) {
        this.endDate = end;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getEndDate() {
        return endDate;
    }

}
