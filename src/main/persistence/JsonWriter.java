package persistence;

import model.GearRoom;

/*Taken from JSONSERIALIZATIONDEMO 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/

// Represents a writer that writes JSON representation of GearRoom to file
public class JsonWriter {
    
    public JsonWriter(String destination){
    }


    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open(){

    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(GearRoom wr) {
        
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
    }
    
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
    }   
}
