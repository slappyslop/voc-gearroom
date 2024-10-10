package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class TestTrip {
    Trip testTrip;
    Member m1;
    Member m2;
    List<Member> interested;
    List<Member> committed;
    List<Member> going;


    @BeforeEach
    void runBefore(){
        testTrip = new Trip();
        m1 = new Member("A");
        m2 = new Member("B");
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

    @Test
    void testAddToGoing(){
        testTrip.addToGoing(m1);
        assertEquals(1, going.size());
        assertEquals(m1, going.get((0)));
    }
    @Test
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

    void testAddMultipleInterested() {
        testTrip.addToInterested(m1);
        testTrip.addToInterested(m2);
        assertEquals(2, interested.size());
        assertEquals(m1, interested.get(0));
        assertEquals(m2, interested.get(1));
    }
}
