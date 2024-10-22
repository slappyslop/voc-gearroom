package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import model.Gear;

import model.Trip;
import model.Member;


/*Inspired by JSONSERIALIZATIONDEMO 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
public class JsonTests {
    protected void checkGear(String name, List<Integer> reservations, Gear gear) {  
        assertEquals(name, gear.getName());
        assertEquals(reservations, gear.getReservations());
    }

    protected void checkTrip(Trip t, Trip jsont) {
        assertEquals(t.getGoing(), jsont.getGoing());
        assertEquals(t.getCommitted(), jsont.getCommitted());
        assertEquals(t.getInterested(), jsont.getInterested());
        assertEquals(t.getStartDate(), t.getEndDate());
        assertEquals(t.getGearList(), jsont.getGearList());

    }
}
