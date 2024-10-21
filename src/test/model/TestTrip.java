package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class TestTrip {
    Trip testTrip;
    Member m1;
    Member m2;
    Member m3;
    Member m4;
    Gear g1;
    Gear g2;
    Gear g3;
    Gear g4;
    Gear g5;
    List<Member> interested;
    List<Member> committed;
    List<Member> going;
    GearRoom testgr;
    List<String> gl;

    @BeforeEach

    void runBefore(){
        testgr = new GearRoom();
        gl = new ArrayList<String>();
        testTrip = new Trip(gl);
        m1 = new Member("A");
        m2 = new Member("B");
        m3 = new Member("C");
        m4 = new Member("D");
        g1 = new Gear("skis");
        g2 = new Gear("boots");

        going = testTrip.getGoing();
        interested = testTrip.getInterested();
        committed = testTrip.getCommitted();



    }

    @Test
    void testConstructor(){
        assertTrue(going.isEmpty());
        assertTrue(committed.isEmpty());
        assertTrue(interested.isEmpty());
        assertEquals(gl, testTrip.getGearList());
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
    void testAddToCommitted() {
        testTrip.addToInterested(m1);
        testTrip.addToCommitted(m1, testgr);
        assertEquals(1, committed.size());
        assertEquals(m1, committed.get((0))); 
        assertFalse(interested.contains(m1)); 
    }

     @Test
      void testAddToCommittedOnly() {
        testTrip.addToCommitted(m1, testgr);
        assertEquals(1, committed.size());
        assertEquals(m1, committed.get((0))); 
        assertFalse(interested.contains(m1)); 
    }

    @Test
    void testAddMultipleCommitted() {
        testTrip.addToInterested(m1);
        testTrip.addToInterested(m2);
        testTrip.addToCommitted(m1, testgr);
        testTrip.addToCommitted(m2, testgr);
        assertEquals(2, committed.size());
        assertEquals(m1, committed.get(0));
        assertEquals(m2, committed.get(1));
        assertFalse(interested.contains(m1));
        assertFalse(interested.contains(m2));
    }

    @Test
    void testAddMultipleCommittedOnly() {
        testTrip.addToCommitted(m1, testgr);
        testTrip.addToCommitted(m2, testgr);
        assertEquals(2, committed.size());
        assertEquals(m1, committed.get(0));
        assertEquals(m2, committed.get(1));
        assertFalse(interested.contains(m1));
        assertFalse(interested.contains(m2));
    }
    
    @Test 
    void testAddToGoing(){
        testTrip.addToCommitted(m1, testgr);
        testTrip.addToGoing(m1);
        assertEquals(1, going.size());
        assertEquals(m1, going.get((0)));
        assertEquals(1, m1.getGoingTrips().size());
        assertEquals(testTrip, m1.getGoingTrips().get(0));
        assertFalse(committed.contains(m1));
    }
    @Test 
    void testAddMultipleGoing() {
        testTrip.addToInterested(m3);
        testTrip.addToInterested(m4);
        testTrip.addToCommitted(m1, testgr);
        testTrip.addToCommitted(m2, testgr);
        testTrip.addToGoing(m1);
        testTrip.addToGoing(m2);
        testTrip.addToGoing(m3);
        testTrip.addToGoing(m4);
        assertEquals(4, going.size());
        assertEquals(m1, going.get(0));
        assertEquals(m2, going.get(1));
        assertEquals(m3, going.get(2));
        assertEquals(m4, going.get(3));
        assertFalse(committed.contains(m1));
        assertFalse(committed.contains(m2));
        assertFalse(interested.contains(m3));
        assertFalse(interested.contains(m4));
    }

    

    @Test
    void testGetMemberRequiredGearAll() {
      gl.add("skis");
      gl.add("boots");
      gl.add("jacket");
      List<String> requiredGear = testTrip.getMemberRequiredGear(m1);
      assertEquals(3, requiredGear.size());
      assertEquals("skis", requiredGear.get(0));
      assertEquals("boots", requiredGear.get(1));
      assertEquals("jacket", requiredGear.get(2));

    }

    @Test
    void testGetMemberRequiredGearSome() {
      gl.add("skis");
      gl.add("boots");
      gl.add("jacket");
      m1.addToMyGear("skis");
      m1.addToMyGear("boots");
      m1.addToMyGear("climbing shoes");
      List<String> requiredGear = testTrip.getMemberRequiredGear(m1);
      assertEquals(1, requiredGear.size());
      assertEquals("jacket", requiredGear.get(0));
    }

    @Test
    void testGetMemberRequiredGearNone() {
      gl.add("skis");
      gl.add("boots");
      gl.add("jacket");
      m1.addToMyGear("skis");
      m1.addToMyGear("boots");
      m1.addToMyGear("climbing shoes");
      m1.addToMyGear("jacket");
      List<String> requiredGear = testTrip.getMemberRequiredGear(m1);
      assertTrue(requiredGear.isEmpty());
      
    }


    @Test
    void testCheckEnoughGear() {
        gl.add("skis");
        gl.add("boots");
        gl.add("jacket");
        g3 = new Gear("jacket");
        g4 = new Gear("climbing shoes");
        testgr.addGear(g1);
        testgr.addGear(g2);
        testgr.addGear(g3);
        testgr.addGear(g4);

        assertTrue(testTrip.checkEnoughGear(m1, testgr).isEmpty());
        assertEquals(gl, testTrip.checkEnoughGear(m2, testgr));
        
    }
    @Test
    void testCheckEnoughGearAllAndNone() {
        gl.add("skis");
        gl.add("boots");
        gl.add("jacket");
        g3 = new Gear("jacket");
        g4 = new Gear("climbing shoes");
        testgr.addGear(g1);
        testgr.addGear(g2);
        testgr.addGear(g3);
        testgr.addGear(g4);

        m2.addToMyGear("skis");
        m2.addToMyGear("boots");
        m2.addToMyGear("jacket");

        assertTrue(testTrip.checkEnoughGear(m1, testgr).isEmpty());
        assertTrue(testTrip.checkEnoughGear(m2, testgr).isEmpty());
        
    }
    @Test
    void testCheckEnoughGearPassSomeAndSome() {
        gl.add("skis");
        gl.add("boots");
        gl.add("jacket");
        g3 = new Gear("jacket");
        g4 = new Gear("climbing shoes");
        testgr.addGear(g1);
        testgr.addGear(g2);
        testgr.addGear(g3);
        testgr.addGear(g4);

        m2.addToMyGear("skis");
        m2.addToMyGear("jacket");
        m1.addToMyGear("boots");

        assertTrue(testTrip.checkEnoughGear(m1, testgr).isEmpty());
        assertTrue(testTrip.checkEnoughGear(m2, testgr).isEmpty());
        
    }

    @Test
    void testCheckEnoughGearFailSomeAndSome() {
        gl.add("skis");
        gl.add("boots");
        gl.add("jacket");
        g3 = new Gear("jacket");
        testgr.addGear(g3);

        m1.addToMyGear("skis");
        m2.addToMyGear("boots");
        List<String> expectedm1 = new ArrayList<String>();
        List<String> expectedm2 = new ArrayList<String>();
        expectedm1.add("boots");
        expectedm2.add("skis");
        expectedm2.add("jacket");
        assertEquals(expectedm1, testTrip.checkEnoughGear(m1, testgr));
        assertEquals(expectedm2, testTrip.checkEnoughGear(m2, testgr));
        

    }

    @Test
    void testDates() {
        testTrip.setStartDate(3);
        testTrip.setEndDate(5);
        assertEquals(testTrip.getStartDate(), 3);
        assertEquals(testTrip.getEndDate(), 5);
    }

    @Test
    void testNames(){
        testTrip.setName("Trip to Garibaldi Park");
        assertEquals("Trip to Garibaldi Park", testTrip.getName());
    }

    
}
