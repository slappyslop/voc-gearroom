package model;

public class Gear {

    public Gear() {}

    public String getName() {
        return "A";
    }
    //REQUIRES: 0 <= start <= end
    //MODIFIES: this    
    //EFFECTS: Marks the gear as reserved over a specific date range
    public void reserve(int start, int end) {
        //TODO 
    }


    //REQUIRES: 0 <= start <= end
    //EFFECTS: Returns true if a piece of gear is reserved over a range of dates
    public boolean isReserved(int start, int end) {
        return false;
    }

}
