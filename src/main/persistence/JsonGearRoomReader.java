package persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.IOException;

import model.Gear;
import model.GearRoom;

/*Taken from JSONSERIALIZATIONDEMO 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/

// Represents a reader that reads gearroom from JSON data stored in file
public class JsonGearRoomReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonGearRoomReader(String source) {
        this.source = source; 
    }

    // EFFECTS: reads Gear Room from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GearRoom read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGearRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Gear Room from JSON object and returns it
    private GearRoom parseGearRoom(JSONObject jsonObject) {
        GearRoom gr = new GearRoom();
        addGears(gr, jsonObject);
        return gr;
    }

    // MODIFIES: gr
    // EFFECTS: parses gears from JSON object and adds them to gearroom
    private void addGears(GearRoom gr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("gearRoom");
        for (Object json : jsonArray) {
            JSONObject gear = (JSONObject) json;
            addGear(gr, gear);
        }
    }

    // MODIFIES: gr
    // EFFECTS: parses gear from JSON object and adds it to gearroom
    private void addGear(GearRoom gr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Gear g = new Gear(name);
        JSONArray reservations = jsonObject.getJSONArray("reservations");
        for (Object i : reservations) {
            int j = (int) i;
            g.reserve(j, j);
        }
        gr.addGear(g);
    }

    
}

