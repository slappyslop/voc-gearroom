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
    private List<String> gearList;

    public Trip(GearRoom gr, List<String> gearList) {
        going = new ArrayList<Member>();
        committed = new ArrayList<Member>();
        interested = new ArrayList<Member>();
        this.gr = gr;
        this.gearList = gearList;

    }

    // REQUIRES: Must be invoked by trip leader only
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

    public List<String> getGearList() {
        return gearList;
    }

}
