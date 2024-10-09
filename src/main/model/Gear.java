package model;

public class Gear {

    public Gear() {}

    public String getName() {
        return "A";
    }
    //REQUIRES: Date must be > 0
    //MODIFIES: this
    //EFFECTS: Marks the gear as reserved on a specific date
    public void reserve(int date) {
        //TODO 
    }

    //EFFECTS: Returns true if a piece of gear is reserved on a specific date 
    public boolean isReserved(int date) {
        return false;
    }

}
