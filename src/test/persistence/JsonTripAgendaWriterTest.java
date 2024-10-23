package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;

import model.Gear;
import model.GearRoom;
import model.Member;
import model.Trip;
import model.TripAgenda;
/*
 * Taken from JSONSERIALIZATIONDEMO
 *  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonTripAgendaWriterTest extends JsonTests {
    private TripAgenda testTa;
    private TripAgenda testTa2;
    private GearRoom gr;
    private List<String> glski;
    private List<String> glcamp;
    private List<String> glhike;
    private Trip trip1;
    private Trip trip2;
    private Trip trip3;
    private Member m1;
    private Member m2;
    private Member m3;

    @Test
    void testWriterInvalidFile() {
        try {
            JsonTripAgendaWriter writer = new JsonTripAgendaWriter(".data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyTripAgenda() {
         try {
            TripAgenda testEmptyTa = new TripAgenda();
            JsonTripAgendaWriter writer = new JsonTripAgendaWriter("./data/testWriterEmptyTripAgenda.json");
            writer.open();
            writer.write(testEmptyTa);
            writer.close();

            JsonTripAgendaReader reader = new JsonTripAgendaReader("./data/testWriterEmptyTripAgenda.json", gr);
            testTa = reader.read();
            assertEquals(0, testTa.getTrips().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTripAgenda(){
        try{
            initializeTripAgenda();
            JsonTripAgendaWriter writer = new JsonTripAgendaWriter("./data/testWriterGeneralTripAgenda.json");
            writer.open();
            writer.write(testTa);
            writer.close();

            JsonTripAgendaReader reader = new JsonTripAgendaReader("./data/testWriterGeneralTripAgenda.json", gr);
            testTa2 = reader.read();
            List<Trip> agenda = testTa2.getTrips();

            for (Trip trip : agenda) {
                checkTrip(trip, testTa.getTrips().get(agenda.indexOf(trip)));
            }

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private void initializeTripAgenda() {
        testTa = new TripAgenda();
        gr = new GearRoom();
        glski = new ArrayList<String>();
        glcamp = new ArrayList<String>();
        glhike = new ArrayList<String>();
        glski.add("skis");
        glski.add("jacket");
        glski.add("boots");
        glhike.add("boots");
        glhike.add("poles");
        glhike.add("fleece");
        glcamp.add("tent");
        glcamp.add("sleeping bag");
        glcamp.add("jacket");
        glcamp.add("stove");
        initTrips();
        initGear();
        initMembers();

    }
    
    private void initTrips() {
        trip1 = new Trip(glski);
        trip1.setName("Backcountry skiing at Elfin Lakes");
        trip1.setStartDate(1);
        trip1.setEndDate(2);

        trip2 = new Trip(glhike);
        trip2.setName("Hiking at Hollyburn");
        trip2.setStartDate(2);
        trip2.setEndDate(5);

        trip3 = new Trip(glcamp);
        trip3.setName("Camping in the Fraser Valley");
        trip3.setStartDate(3);
        trip3.setEndDate(4);

        testTa.addTrip(trip1);
        testTa.addTrip(trip2);
        testTa.addTrip(trip3);
    }

    private void initMembers() {
        m1 = new Member("Shravan");
        m1.addToMyGear("boots");
        m2 = new Member("Kumar");
        m3 = new Member("A");
        m1.registerCommitted(trip1, gr);
        trip1.addToGoing(m1);
        m2.registerCommitted(trip1, gr);
        m3.registerInterested(trip1);
        m2.registerInterested(trip2);
        m3.registerCommitted(trip2, gr);
        m1.registerInterested(trip2);
        m3.registerInterested(trip3);
        m2.registerCommitted(trip3, gr);
        

    }

    private void initGear() {
        gr.addGear(new Gear("skis"));
        gr.addGear(new Gear("skis"));
        gr.addGear(new Gear("skis"));
        gr.addGear(new Gear("skis"));
        gr.addGear(new Gear("boots"));
        gr.addGear(new Gear("boots"));
        gr.addGear(new Gear("boots"));
        gr.addGear(new Gear("boots"));
        gr.addGear(new Gear("boots"));
        gr.addGear(new Gear("boots"));
        gr.addGear(new Gear("tent"));
        gr.addGear(new Gear("tent"));
        gr.addGear(new Gear("poles"));
        gr.addGear(new Gear("poles"));
        gr.addGear(new Gear("poles"));
        gr.addGear(new Gear("poles"));
        gr.addGear(new Gear("poles"));
    }


    

    
    

}
