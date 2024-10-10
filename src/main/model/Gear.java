package model;

import java.util.ArrayList;
import java.util.List;

public class Gear {
    private String name;
    private List<Integer> reservations;

    public Gear(String name) {
        this.name = name;
        this.reservations = new ArrayList<Integer>();
    }

    public String getName() {
        return name;
    }
    //REQUIRES: 0 <= start <= end, Shouldn't already be reserved on those dates
    //MODIFIES: this    
    //EFFECTS: Marks the gear as reserved over a specific date range (inclusive)
    public void reserve(int start, int end) {
        for(int i = start; i <= end; i++) {
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
