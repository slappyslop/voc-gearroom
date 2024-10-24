package model;

import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;

import persistence.Writable;

//Represents the club's shared pool of gear available to rent
public class GearRoom implements Writable {
    private List<Gear> gearRoom;

    //EFFECTS: constructs a GearRoom with a list of gear
    public GearRoom() {
        this.gearRoom = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Adds gear to the gearroom and makes it available for trips to use,
    //         latest added gear is last
    public void addGear(Gear g) {
        gearRoom.add(g);
    }

    public List<Gear> getGearRoom() {

        return gearRoom;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("gearRoom", gearRoomtoJson());
        return json;
    }

    // EFFECTS: returns gear in this gear room as a JSON array
    private JSONArray gearRoomtoJson() {
        JSONArray jsonArray = new JSONArray();

        for (Gear g : gearRoom) {
            jsonArray.put(g.toJson());
        }
        return jsonArray;
    }

    
}
