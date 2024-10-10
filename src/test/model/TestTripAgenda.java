package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class TestTripAgenda {
    TripAgenda testAgenda;
    Trip trip1;
    Trip trip2;
    GearRoom gr;
    List<String> gl;


    @BeforeEach
    void runBefore(){
        gr = new GearRoom();
        testAgenda = new TripAgenda();
        gl = new ArrayList<String>();
        trip1 = new Trip(gr, gl);
        trip2 = new Trip(gr, gl);
    }

    @Test
    void testConstructor(){
        assertEquals(testAgenda.getTrips().size(), 0);
    }

    @Test
    void testAddTrip(){
        List<Trip> tripList = testAgenda.getTrips();
        assertEquals(0, tripList.size());
        testAgenda.addTrip(trip1);
        assertEquals(1, tripList.size());
        assertEquals(trip1, tripList.get(0));
        

    }
    @Test
    void testAddMultipleTrips(){
        List<Trip> tripList = testAgenda.getTrips();
        assertEquals(0, tripList.size());
        testAgenda.addTrip(trip1);
        assertEquals(1, tripList.size());
        assertEquals(trip1, tripList.get(0));
        testAgenda.addTrip(trip2);
        assertEquals(2, tripList.size());
        assertEquals(trip1, tripList.get(0));
        assertEquals(trip2, tripList.get(1));
        
    }
}
