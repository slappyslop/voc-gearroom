package persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import model.TripAgenda;
import model.Trip;
import model.Member;
import model.GearRoom;

/*Taken from JSONSERIALIZATIONDEMO 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
public class JsonTripAgendaReader {
    private String source;
    private GearRoom gr;

    // EFFECTS: constructs reader to read from source file
    public JsonTripAgendaReader(String source, GearRoom gr) {
        this.source = source; 
        this.gr = gr;
    }

    // EFFECTS: reads Trip Agenda from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TripAgenda read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTripAgenda(jsonObject);
        
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Trip Agenda from JSON object and returns it
    private TripAgenda parseTripAgenda(JSONObject jsonObject) {
        TripAgenda ta = new TripAgenda();
        addTrips(ta, jsonObject);
        return ta;
    }

    // MODIFIES: ta
    // EFFECTS: parses trips from JSON object and adds them to workroom
    private void addTrips(TripAgenda ta, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("agenda");
        for (Object json : jsonArray) {
            JSONObject trip = (JSONObject) json;
            addTrip(ta, trip);
        }
        
    }

    // MODIFIES: ta
    // EFFECTS: parse trips from JSON object and adds it to ta
    private void addTrip(TripAgenda ta, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int startDate = jsonObject.getInt("startDate");
        int endDate = jsonObject.getInt("endDate");
        List<String> gearList = new ArrayList<String>();
        JSONArray goingArray = jsonObject.getJSONArray("going");
        JSONArray committedArray = jsonObject.getJSONArray("committed");
        JSONArray interestedArray = jsonObject.getJSONArray("interested");

        JSONArray readGearList = jsonObject.getJSONArray("gearList");
        for (Object json : readGearList) {
            String gear = (String) json;
            gearList.add(gear); 
        }
        
        Trip t = new Trip(gearList);
        t.setName(name);
        t.setStartDate(startDate);
        t.setEndDate(endDate);
        addGoingMembers(t,goingArray);
        addCommittedMembers(t, committedArray);
        addInterestedMembers(t, interestedArray);
        ta.addTrip(t);
        
    }

    private void addInterestedMembers(Trip t, JSONArray interestedArray) {
        for (Object json : interestedArray) {
            JSONObject member = (JSONObject) json;
            Member m = createAMember(member);
            m.registerInterested(t);
        }
    }

    private void addCommittedMembers(Trip t, JSONArray committedArray) {
        for (Object json : committedArray) {
            JSONObject member = (JSONObject) json;
            Member m = createAMember(member);
            m.registerCommitted(t, gr);
        }
    }

    private void addGoingMembers(Trip t, JSONArray goingArray) {
        for (Object json : goingArray) {
            JSONObject member = (JSONObject) json;
            Member m = createAMember(member);
            t.addToGoing(m);
        }
    }

    private Member createAMember(JSONObject member) {
        Member m = new Member(member.getString("name"));
        JSONArray myGearArray = member.getJSONArray("myGear");
        for (Object jsonGear : myGearArray) {
            String gear = (String) jsonGear;
            m.addToMyGear(gear);
        }
        return m;
    }
            
      
}

    




