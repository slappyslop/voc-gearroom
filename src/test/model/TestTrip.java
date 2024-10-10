package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class TestTrip {
    Trip testTrip;
    Member m1;
    Member m2;
    Gear g1;
    Gear g2;
    List<Member> interested;
    List<Member> committed;
    List<Member> going;
    GearRoom testgr;
    GearRoom testgr2;

    @BeforeEach
    void runBefore(){
        testgr = new GearRoom();
        testgr2 = new GearRoom();
        testTrip = new Trip(testgr);
        m1 = new Member("A");
        m2 = new Member("B");
        g1 = new Gear("AA");
        g2 = new Gear("BB");

        going = testTrip.getGoing();
        interested = testTrip.getInterested();
        committed = testTrip.getCommitted();



    }

    @Test
    void testConstructor(){
        assertTrue(going.isEmpty());
        assertTrue(committed.isEmpty());
        assertTrue(interested.isEmpty());
    }

    @Test //TODO
    void testAddToGoing(){
        testTrip.addToGoing(m1);
        assertEquals(1, going.size());
        assertEquals(m1, going.get((0)));
        assertEquals(1, m1.getGoingTrips().size());
        assertEquals(testTrip, m1.getGoingTrips().get(0));
    }
    @Test //TODO
    void testAddMultipleGoing() {
        testTrip.addToGoing(m1);
        testTrip.addToGoing(m2);
        assertEquals(2, going.size());
        assertEquals(m1, going.get(0));
        assertEquals(m2, going.get(1));
    }

    @Test
    void testAddToCommitted() {
        testTrip.addToCommitted(m1);
        assertEquals(1, committed.size());
        assertEquals(m1, committed.get((0)));  
    }

    @Test
    void testAddMultipleCommitted() {
        testTrip.addToCommitted(m1);
        testTrip.addToCommitted(m2);
        assertEquals(2, committed.size());
        assertEquals(m1, committed.get(0));
        assertEquals(m2, committed.get(1));
    }

    @Test
    void testAddToInterested() {
        testTrip.addToInterested(m1);
        assertEquals(1, interested.size());
        assertEquals(m1, interested.get((0))); 
    }
    @Test
    void testAddMultipleInterested() {
        testTrip.addToInterested(m1);
        testTrip.addToInterested(m2);
        assertEquals(2, interested.size());
        assertEquals(m1, interested.get(0));
        assertEquals(m2, interested.get(1));
    }

    @Test
    void testCheckEnoughGear() {
        assertFalse(testTrip.checkEnoughGear());
        testgr.addGear(g1);
        testgr.addGear(g2);
        assertTrue(testTrip.checkEnoughGear());
        assertTrue(testTrip.checkEnoughGear());
        assertFalse(testTrip.checkEnoughGear());
    }

}
