package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import model.Gear;



/*Inspired by JSONSERIALIZATIONDEMO 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
public class JsonGearTest {
    protected void checkGear(String name, List<Integer> reservations, Gear gear) {  
        assertEquals(name, gear.getName());
        assertEquals(reservations, gear.getReservations());
    }

}
