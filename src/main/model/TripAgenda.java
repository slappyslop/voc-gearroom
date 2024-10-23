package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;

import org.json.JSONObject;

//Represents the trip agenda, a list of club trips
public class TripAgenda {
    private List<Trip> agenda;

    //EFFECTS: Creates a TripAgenda
    public TripAgenda() {
        this.agenda = new ArrayList<>();
    }

    //REQUIRES: trip must not already be in agenda
    //MODIFIES: this
    //EFFECTS: Adds trips to the trip agenda
    public void addTrip(Trip t) {
        agenda.add(t);
    }

    public List<Trip> getTrips() {
        return agenda;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("agenda", agendaToJson());
        return json;
    }

    private JSONArray agendaToJson() {
        JSONArray jsonArray = new JSONArray();

        for(Trip t : agenda) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
    

}
