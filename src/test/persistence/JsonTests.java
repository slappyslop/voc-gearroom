package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertEquals(t.getStartDate(), jsont.getStartDate());
        assertEquals(t.getEndDate(), jsont.getEndDate());
        assertEquals(t.getGearList(), jsont.getGearList());
        for (Member m : t.getCommitted()) {
            checkMember(m, jsont.getCommitted().get(t.getCommitted().indexOf(m)));
        }
        for (Member m : t.getGoing()) {
            checkMember(m, jsont.getGoing().get(t.getGoing().indexOf(m)));
        }
        for (Member m : t.getInterested()) {
            checkMember(m, jsont.getInterested().get(t.getInterested().indexOf(m)));
        }

    }

    private void checkMember(Member tripM, Member jsonM) {
        assertEquals(tripM.getMyGear(), jsonM.getMyGear());
        assertEquals(tripM.getName(), jsonM.getName());
        assertNull(tripM.getLogInState());
        assertNull(jsonM.getLogInState());
        
    }
}
