package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

//Represents a club member having name, names of gear they already own,log of trips they have interacted with
// and log in state
public class Member implements Writable {
    private List<String> myGear; //a list of names of gear the member owns
    private String name; // name of the member
    private String logInState; // whether the member is currently logged in

    //REQUIRES: Name is unique
    //EFFECTS: Creates a member with a name and no gear
    public Member(String name) {
        this.name = name;
        myGear = new ArrayList<String>();

    }

    //MODIFIES: this, t
    //EFFECTS: Adds member to committed list of trip, latest member last, also adds trip to committed list of member,
    //         returns list of gear that was unable to be reserved in the clubroom
    public List<String> registerCommitted(Trip t, GearRoom gr) {
        return t.addToCommitted(this, gr);
    }

    //MODIFIES: this, t
    //EFFECTS: Adds member to interested list of trip, latest member last, also adds trip to interested list of member
    public void registerInterested(Trip t) {
        t.addToInterested(this);
    }

    //MODIFIES: this
    //EFFECTS: Adds a piece of gear to your owned list, this means you dont want to rent it.
    public void addToMyGear(String s) {
        myGear.add(s);
    }


    public void setLogInState(String s) {
        this.logInState = s;
    }

    public String getName() {
        return name;
    }

    public List<String> getMyGear() {
        return myGear;
    }

    public String getLogInState() {
        return logInState;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        JSONArray myGearArray = new JSONArray();
        for (String string : myGear) {
            myGearArray.put(string);
        }
        json.put("myGear", myGearArray);
        return json;
    }

    // @Override
    // public int hashCode() {
    //     final int prime = 31;
    //     int result = 1;
    //     result = prime * result + ((name == null) ? 0 : name.hashCode());
    //     return result;
    // }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null)
    //         return false;
    //     if (getClass() != obj.getClass())
    //         return false;
    //     Member other = (Member) obj;
    //     if (name == null) {
    //         if (other.name != null)
    //             return false;
    //     } else if (!name.equals(other.name))
    //         return false;
    //     return true;
    // }




}
