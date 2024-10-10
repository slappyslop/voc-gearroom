package model;
import java.util.List;
import java.util.ArrayList;

//Represents the club's shared pool of gear available to rent
public class GearRoom {
    private List<Gear> gearRoom;

    GearRoom() {
        this.gearRoom = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Adds gear to the gearroom and makes it available for trips to use,
    //         latest added gear is last
    public void addGear(Gear g){
        gearRoom.add(g);
    }

    public List<Gear> getGearRoom() {

        return gearRoom;
    }
}
