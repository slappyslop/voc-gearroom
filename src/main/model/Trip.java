package model;

import java.util.ArrayList;
import java.util.List;

//Represents a club trip, with name, access to a pool of gear, members who are going, committed, and interested, a start and end date,
// and a list of names of required gear
public class Trip {

    private String name;
    private GearRoom gr; //Accessible pool of club gear
    private List<Member> going; //list of members going on trip
    private List<Member> committed; //list of members committed to trip
    private List<Member> interested; //list of members interested in trip
    private int startDate; //start date of trip
    private int endDate; // end date of trip
    private List<String> gearList; //list of gear required for trip per person

    //REQUIRES: gearList must not contain duplicates
    public Trip(GearRoom gr, List<String> gearList) {
        going = new ArrayList<Member>();
        committed = new ArrayList<Member>();
        interested = new ArrayList<Member>();
        this.gr = gr;
        this.gearList = gearList;

    }

    // REQUIRES: Must be invoked by trip leader only, committed.contains(m)
    // MODIFIES: this
    // EFFECTS: Adds the member to the going list of the trip,
    // adds trip to going of member
    public void addToGoing(Member m) {
        going.add(m);
        m.registerGoing(this);

    }

    // MODIFIES: this
    // EFFECTS: Adds the member to the committed list of the trip,
    // returns true if there is enough gear in the club room
    // false if there is not.
    public boolean addToCommitted(Member m) {
        committed.add(m);
        return checkEnoughGear(m);
    }

    // MODIFIES: this
    // EFFECTS: Adds the member to the committed list of the trip
    public void addToInterested(Member m) {
        interested.add(m);
    }

    // MODIFIES: this, gr
    // EFFECTS: Checks if there is enough unreserved gear for member in the gear
    // room
    // Marks gear as reserved and returns true if there is, returns false if no gear
    public boolean checkEnoughGear(Member m) {
        List<String> requiredGear = getMemberRequiredGear(m);
        for (Gear g : gr.getGearRoom()) {
            if (!g.isReserved(startDate, endDate)) {
                for (String s : requiredGear) {
                    if (g.getName().equals(s)) {
                        g.reserve(startDate, endDate);
                        requiredGear.remove(s);
                        break;
                    }
                }
            } else continue;

        }
        return requiredGear.isEmpty();
    }

    // EFFECTS: Subtracts trip.requiredGear from member.myGear and returns what gear
    // the member must rent
    public List<String> getMemberRequiredGear(Member m) {
        List<String> requiredGear = new ArrayList<String>();
        for (String s : gearList) {
            requiredGear.add(s);
        }
        for (String ms : m.getMyGear()) {
            for (String s : requiredGear) {
                if (ms.equals(s)) {
                    requiredGear.remove(s);
                    break;
                }

            }
        }
        return requiredGear;

    }

    public void setName(String s) {
        this.name = s;
    }

    // EFFECTS: Returns list of going members on trip
    public List<Member> getGoing() {
        return going;
    }

    public List<Member> getCommitted() {
        return committed;
    }

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

    public String getName() {
        return name;
    }

}
