package persistence;
import model.GearRoom;
import org.json.JSONObject;


/*Taken from JSONSERIALIZATIONDEMO 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
// Represents a writer that writes JSON representation of GearRoom to file
public class JsonGearRoomWriter extends JsonWriter {
    
    public JsonGearRoomWriter(String destination){
        super(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of GearRoom to file
    public void write(GearRoom gr) {
        JSONObject json = gr.toJson();
        saveToFile(json.toString(TAB)); 
    }

}
