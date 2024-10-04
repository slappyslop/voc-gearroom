package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class TestTripAgenda {
    TripAgenda testAgenda;
    Trip trip1;
    Trip trip2;


    @BeforeEach
    void runBefore(){
        testAgenda = new TripAgenda();
        trip1 = new Trip();
        trip2 = new Trip();
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
