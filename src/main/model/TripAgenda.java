package model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

import persistence.Writable;

import org.json.JSONArray;

//Represents the trip agenda, a list of club trips
public class TripAgenda implements Writable {
    private List<Trip> agenda;

    //EFFECTS: Creates a TripAgenda with no trips posted
    public TripAgenda() {
        this.agenda = new ArrayList<>();
    }

    //REQUIRES: trip must not already be in agenda
    //MODIFIES: this
    //EFFECTS: Adds trips to the trip agenda
    public void addTrip(Trip t) {
        agenda.add(t);
        EventLog.getInstance().logEvent(new Event(t.getName() + " added to agenda"));
    }

    public List<Trip> getTrips() {
        return agenda;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("agenda", agendaToJson());
        return json;
    }

    // EFFECTS: returns trips in this trip agenda as a JSON array
    private JSONArray agendaToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Trip t : agenda) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
    

}
