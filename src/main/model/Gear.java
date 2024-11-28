package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//Represents a piece of club gear, having a name and a set of reserved dates
public class Gear implements Writable {

    private String name; // name of the gear
    private List<Integer> reservations; // days the gear has been reserved

    // EFFECTS: constructs a new piece of gear with name and no reservations
    public Gear(String name) {
        this.name = name;
        this.reservations = new ArrayList<Integer>();
    }

    public String getName() {
        return name;
    }

    // REQUIRES: 0 <= start <= end, isReserved(start, end) == false
    // MODIFIES: this
    // EFFECTS: Marks the gear as reserved over a specific date range (inclusive)
    public void reserve(int start, int end) {
        for (int i = start; i <= end; i++) {
            reservations.add(i);
            EventLog.getInstance().logEvent(new Event(name + " reserved on " + String.valueOf(i)));
        }
    }

    public List<Integer> getReservations() {
        return reservations;
    }

    // REQUIRES: 0 <= start <= end
    // EFFECTS: Returns true if a piece of gear is reserved over a range of dates
    public boolean isReserved(int start, int end) {
        for (int i : reservations) {
            if ((start <= i) && (i <= end)) {
                EventLog.getInstance()
                        .logEvent(new Event("Checked if " + name + " was reserved on " + String.valueOf(i)));
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("reservations", intsToJson());
        return json;
    }

    // EFFECTS: returns reservations of this gear as a JSON array
    private JSONArray intsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (int i : reservations) {
            jsonArray.put(i);
        }
        return jsonArray;
    }

}
