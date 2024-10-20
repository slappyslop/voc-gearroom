package persistence;
import java.io.*;
import model.GearRoom;
import org.json.JSONObject;


/*Taken from JSONSERIALIZATIONDEMO 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/

// Represents a writer that writes JSON representation of GearRoom to file
public class JsonWriter {
    private static final int TAB = 4;
    private String destination;
    private PrintWriter writer;
    
    public JsonWriter(String destination){
        this.destination = destination;
    }


    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(GearRoom gr) {
        JSONObject json = gr.toJson();
        saveToFile(json.toString(TAB)); 
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }
    
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }   
}
