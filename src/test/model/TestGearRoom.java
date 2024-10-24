package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGearRoom {
    GearRoom testGearRoom;
    Gear g1;
    Gear g2;
    List<Gear> gr;

    @BeforeEach
    void runBefore() {
        testGearRoom = new GearRoom();
        g1 = new Gear("A");
        g2 = new Gear("B");
        gr = testGearRoom.getGearRoom();
    }

    @Test
    void testConstructor() {
        assertTrue(gr.isEmpty());
    }

    @Test
    void testAddGear() {
        testGearRoom.addGear(g1);
        assertEquals(1, gr.size());
        assertEquals(g1, gr.get((0)));
    }

    @Test
    void testAddMultipleGear() {
        testGearRoom.addGear(g1);
        testGearRoom.addGear(g2);
        assertEquals(2, gr.size());
        assertEquals(g1, gr.get(0));
        assertEquals(g2, gr.get(1));
    }

}
