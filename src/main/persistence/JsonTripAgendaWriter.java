package persistence;

import org.json.JSONObject;

import model.TripAgenda;

/*Taken from JSONSERIALIZATIONDEMO 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
public class JsonTripAgendaWriter extends JsonWriter {

    //EFFECTS: creates an instance of JSONTripAgendaWriter
    JsonTripAgendaWriter(String destination) {
        super(destination);

    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of TripAgenda to file
    public void write(TripAgenda ta) {
        JSONObject json = ta.toJson();
        saveToFile(json.toString(TAB));
    }
}
