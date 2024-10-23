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
        assertTrue(m.getMyGear().isEmpty());
        assertEquals("Test", m.getName());
    }

    @Test
    void testRegisterCommitted() {
        m.registerCommitted(t1, gr);
        assertEquals(1, t1.getCommitted().size());
        assertEquals(m, t1.getCommitted().get(0));
        m.registerCommitted(t2, gr);


    }

    @Test
    void testRegisterInterested() {
        m.registerInterested(t1);
        assertEquals(1, t1.getInterested().size());
        assertEquals(m, t1.getInterested().get(0));
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
