package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMember {
    Member m;
    Trip t1;
    Trip t2;
    GearRoom gr;
    List<String> gl;

    @BeforeEach
    void runBefore() {
        m = new Member("Test");
        gr = new GearRoom();
        gl = new ArrayList<>();
        t1 = new Trip(gl);
        t2 = new Trip(gl);

    }

    @Test
    void testConstructor() {
        assertTrue(m.getCommittedTrips().isEmpty());
        assertTrue(m.getGoingTrips().isEmpty());
        assertTrue(m.getInterestedTrips().isEmpty());
        assertTrue(m.getMyGear().isEmpty());
        assertEquals("Test", m.getName());
    }

    @Test
    void testRegisterGoing() {
        m.registerGoing(t1);
        assertEquals(1, m.getGoingTrips().size());
        assertEquals(t1, m.getGoingTrips().get(0));
        m.registerGoing(t2);
        assertEquals(2, m.getGoingTrips().size());
        assertEquals(t1, m.getGoingTrips().get(0));
        assertEquals(t2, m.getGoingTrips().get(1));
    }

    @Test
    void testRegisterCommitted() {
        m.registerCommitted(t1, gr);
        assertEquals(1, m.getCommittedTrips().size());
        assertEquals(t1, m.getCommittedTrips().get(0));
        assertEquals(1, t1.getCommitted().size());
        assertEquals(m, t1.getCommitted().get(0));
        m.registerCommitted(t2, gr);
        assertEquals(2, m.getCommittedTrips().size());
        assertEquals(t1, m.getCommittedTrips().get(0));
        assertEquals(t2, m.getCommittedTrips().get(1));

    }

    @Test
    void testRegisterInterested() {
        m.registerInterested(t1);
        assertEquals(1, m.getInterestedTrips().size());
        assertEquals(t1, m.getInterestedTrips().get(0));
        assertEquals(1, t1.getInterested().size());
        assertEquals(m, t1.getInterested().get(0));
        m.registerInterested(t2);
        assertEquals(2, m.getInterestedTrips().size());
        assertEquals(t1, m.getInterestedTrips().get(0));
        assertEquals(t2, m.getInterestedTrips().get(1));

    }

    @Test
    void testAddToMyGear() {
        m.addToMyGear("skis");
        assertEquals(1, m.getMyGear().size());
        assertEquals("skis", m.getMyGear().get(0));
        m.addToMyGear("climbing shoes");
        assertEquals(2, m.getMyGear().size());
        assertEquals("skis", m.getMyGear().get(0));
        assertEquals("climbing shoes", m.getMyGear().get(1));
    }

    @Test
    void testLogInStates() {
        m.setLogInState("leader");
        assertEquals("leader", m.getLogInState());
    }
}
