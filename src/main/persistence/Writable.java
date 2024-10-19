package persistence;

import org.json.JSONObject;

/*Taken from JSONSERIALIZATIONDEMO 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
public interface Writable {
    //EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
