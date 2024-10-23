package persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

public class JsonTripAgendaReaderTest extends JsonTests {
    private TripAgenda testTa;
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
    void testReaderNonExistentFile() {
        JsonTripAgendaReader reader = new JsonTripAgendaReader("./data/noSuchFile.json", gr);
        try {
            testTa = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTripAgenda() {
        JsonTripAgendaReader reader = new JsonTripAgendaReader("./data/testReaderEmptyTripAgenda.json", gr);
        try {
            testTa = reader.read();
            assertTrue(testTa.getTrips().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    void testReaderGeneralTripAgenda() {
        initializeTripAgenda();
        JsonTripAgendaReader reader = new JsonTripAgendaReader("./data/testReaderGeneralTripAgenda.json", gr);
        try {
            TripAgenda readTa = reader.read();
            List<Trip> readAgenda = readTa.getTrips();
            for (Trip trip : readAgenda) {
                checkTrip(testTa.getTrips().get(readAgenda.indexOf(trip)), trip);
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
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

}

