package model;

import java.util.List;

public class Trip {

    public Trip(){


    }

    //MODIFIES: this, m
    //EFFECTS: Adds the member to the going list of the trip
    public void addToGoing(Member m) {
        //TODO

    }
    //MODIFIES: this
    //EFFECTS: Adds the member to the committed list of the trip
    public void addToCommitted(Member m){
        //TODO
    }

    //MODIFIES: this
    //EFFECTS: Adds the member to the committed list of the trip
    public void addToInterested(Member m){

    }

    //EFFECTS: Gets size of the trip
    public int getSize() {
        //TODO: stub
        return 0;
    }

    //EFFECTS: Returns max gear required for trip 
    public List<Gear> getMaxGear(){
        return null;
    }

    //EFFECTS: Returns list of going members on trip
    public List<Member> getGoing() {
        return null;
    }

    //EFFECTS: Returns list of committed members
    public List<Member> getCommitted() {
        return null;
    }

    //EFFECTS: Returns list of interested members
    public List<Member> getInterested() {
        return null;

    }

    //EFFECTS: Returns date of trip 
    public int getDate(){
        return 0;
    }


}
