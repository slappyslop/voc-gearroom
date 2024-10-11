package model;

import java.util.ArrayList;
import java.util.List;

//Represents a piece of club gear, having a name and a set of reserved dates
public class Gear {

    private String name; //name of the gear
    private List<Integer> reservations; //days the gear has been reserved

    public Gear(String name) {
        this.name = name;
        this.reservations = new ArrayList<Integer>();
    }

    public String getName() {
        return name;
    }
    
    //REQUIRES: 0 <= start <= end, isReserved(start, end) == false
    //MODIFIES: this    
    //EFFECTS: Marks the gear as reserved over a specific date range (inclusive)
    public void reserve(int start, int end) {
        for (int i = start; i <= end; i++) {
            reservations.add(i);
        }
    }

    public List<Integer> getReservations() {
        return reservations;
    }


    //REQUIRES: 0 <= start <= end
    //EFFECTS: Returns true if a piece of gear is reserved over a range of dates
    public boolean isReserved(int start, int end) {
        for (int i : reservations) {
            if ((start <= i) && (i <= end)) {
                return true;
            } 
        }
        return false;
    } 

}
