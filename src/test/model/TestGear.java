package model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class TestGear {
    Gear g1;
    List<Integer> reservations;

    @BeforeEach
    void runBefore() {
        g1 = new Gear("Test");
        reservations = g1.getReservations();
    }

    @Test
    void testConstructor() {
        assertTrue(g1.getReservations().isEmpty());
    }

    @Test
    void testReserve() {
        g1.reserve(1, 1);
        assertEquals(1, reservations.size());
        assertEquals(1, reservations.get(0));
        g1.reserve(3, 5);
        assertEquals(4, reservations.size());
        assertEquals(3, reservations.get(1));
        assertEquals(4, reservations.get(2));
        assertEquals(5, reservations.get(3));
    }

    @Test
    void testIsReserved() {
        assertFalse(g1.isReserved(0, 0));
        assertFalse(g1.isReserved(4, 7));
        g1.reserve(3, 7);
        assertTrue(g1.isReserved(4, 7));
        assertTrue(g1.isReserved(3, 4));
        assertTrue(g1.isReserved(7, 9));
        assertTrue(g1.isReserved(7, 9));
        assertTrue(g1.isReserved(4, 4));
        assertFalse(g1.isReserved(1, 3));
        assertFalse(g1.isReserved(8, 8));
    } 

}
