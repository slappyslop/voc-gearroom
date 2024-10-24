package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

//Represents a club trip, with name, access to a pool of gear,
// members who are going, committed, and interested, a start and end date,
// and a list of names of required gear
public class Trip implements Writable {

    private String name; // name of the trip
    private List<Member> going; // list of members going on trip
    private List<Member> committed; // list of members committed to trip
    private List<Member> interested; // list of members interested in trip
    private int startDate; // start date of trip
    private int endDate; // end date of trip
    private List<String> gearList; // list of gear required for trip per person

    // REQUIRES: gearList must not contain duplicates
    //EFFECTS: constructs a Trip with a gearList, and no signed on members
    public Trip(List<String> gearList) {
        going = new ArrayList<Member>();
        committed = new ArrayList<Member>();
        interested = new ArrayList<Member>();
        this.gearList = gearList;

    }

    // REQUIRES: Must be invoked by trip leader only
    // MODIFIES: this
    // EFFECTS: Adds the member to the going list of the trip,
    // adds trip to going of member, removes from member from committed.
    public void addToGoing(Member m) {
        going.add(m);
        if (committed.contains(m)) {
            committed.remove(m);
        }
        if (interested.contains(m)) {
            interested.remove(m);
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds the member to the committed list of the trip,
    // returns the list of gear that was unable to be reserved,
    // Removes member from interested
    public List<String> addToCommitted(Member m, GearRoom gr) {
        committed.add(m);
        if (interested.contains(m)) {
            interested.remove(m);
        }
        return checkEnoughGear(m, gr);
    }

    // MODIFIES: this
    // EFFECTS: Adds the member to the committed list of the trip, there should
    // never
    public void addToInterested(Member m) {
        interested.add(m);
    }

    //EFFECTS: Checks if getMemberRequiredGear(m) is available to rent in the clubroom
    // on day of trip, returns unrentable gear
    public List<String> checkEnoughGear(Member m, GearRoom gr) {
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
            } else {
                continue;
            }

        }
        return requiredGear;
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

    public List<String> getGearList() {
        return gearList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("startDate", startDate);
        json.put("endDate", endDate);
        JSONArray gearListArray = new JSONArray();
        for (String gear : gearList) {
            gearListArray.put(gear);
        }
        json.put("gearList", gearListArray);
        json.put("going", memberListToJsonArray(going));
        json.put("committed", memberListToJsonArray(committed));
        json.put("interested", memberListToJsonArray(interested));

        return json;
    }

    // EFFECTS: returns members in memberList as a JSON array
    private JSONArray memberListToJsonArray(List<Member> memberList) {
        JSONArray jsonArray = new JSONArray();
        for (Member m : memberList) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;

    }

}
