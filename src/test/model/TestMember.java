package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMember {
    Member m1;
    Trip t1;
    Trip t2;
    GearRoom gr;
    List<String> gl;

    @BeforeEach
    void runBefore() {
        m1 = new Member("Test");
        gr = new GearRoom();
        gl = new ArrayList<>();
        t1 = new Trip(gl);
        t2 = new Trip(gl);

    }

    @Test
    void testConstructor() {
        assertTrue(m1.getMyGear().isEmpty());
        assertEquals("Test", m1.getName());
    }

    @Test
    void testRegisterCommitted() {
        m1.registerCommitted(t1, gr);
        assertEquals(1, t1.getCommitted().size());
        assertEquals(m1, t1.getCommitted().get(0));
        m1.registerCommitted(t2, gr);


    }

    @Test
    void testRegisterInterested() {
        m1.registerInterested(t1);
        assertEquals(1, t1.getInterested().size());
        assertEquals(m1, t1.getInterested().get(0));
    }

    @Test
    void testAddToMyGear() {
        m1.addToMyGear("skis");
        assertEquals(1, m1.getMyGear().size());
        assertEquals("skis", m1.getMyGear().get(0));
        m1.addToMyGear("climbing shoes");
        assertEquals(2, m1.getMyGear().size());
        assertEquals("skis", m1.getMyGear().get(0));
        assertEquals("climbing shoes", m1.getMyGear().get(1));
    }

    @Test
    void testLogInStates() {
        m1.setLogInState("leader");
        assertEquals("leader", m1.getLogInState());
    }
}
