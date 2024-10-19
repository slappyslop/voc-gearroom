package persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

import model.GearRoom;

/*Taken from JSONSERIALIZATIONDEMO 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GearRoom read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null;
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private GearRoom parseWorkRoom(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addThingies(GearRoom gr, JSONObject jsonObject) {
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addThingy(GearRoom gr, JSONObject jsonObject) {
    }
}

