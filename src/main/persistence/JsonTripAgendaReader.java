package persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.IOException;

import model.TripAgenda;
import model.Trip;
import model.Member;
import model.GearRoom;

public class JsonTripAgendaReader {
    private String source;
    
    // EFFECTS: constructs reader to read from source file
    public JsonTripAgendaReader(String source) {
        this.source = source; 
    }

    // EFFECTS: reads Trip Agenda from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TripAgenda read() throws IOException {
        return null;
        
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null;
    }

    // EFFECTS: parses Trip Agenda from JSON object and returns it
    private TripAgenda parseTripAgenda(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: ta
    // EFFECTS: parses trips from JSON object and adds them to workroom
    private void addTrips(TripAgenda ta, JSONObject jsonObject) {
        
    }

    // MODIFIES: ta
    // EFFECTS: parses gear from JSON object and adds it to workroom
    private void addTrip(TripAgenda ta, JSONObject jsonObject) {
        
    }

}
