package ca.ubc.cs.cpsc210.moreiterators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TwoListTest {
    private TwoLists twoLists;

    @BeforeEach
    void runBefore() {
        twoLists = new TwoLists();
    }

    @Test
    void oneThenTwoTest() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 11, 12, 13);
        twoLists.add(1, 1);
        twoLists.add(1, 2);
        twoLists.add(1, 3);
        twoLists.add(1, 4);
        twoLists.add(1, 5);
        twoLists.add(2, 11);
        twoLists.add(2, 12);
        twoLists.add(2, 13);

        twoLists.chooseIterator(TwoLists.ONE_THEN_TWO);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    void alternatingTest() {
        List<Integer> expected = Arrays.asList(1, 11, 2, 12, 3, 13, 4, 5);
        twoLists.add(1, 1);
        twoLists.add(1, 2);
        twoLists.add(1, 3);
        twoLists.add(1, 4);
        twoLists.add(1, 5);
        twoLists.add(2, 11);
        twoLists.add(2, 12);
        twoLists.add(2, 13);

        twoLists.chooseIterator(TwoLists.ALTERNATING);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    void cartesianTest() {
        List<Integer> expected = Arrays.asList(11, 12, 13, 22, 24, 26, 33, 36, 39, 44, 48, 52, 55, 60, 65);
        twoLists.add(1, 1);
        twoLists.add(1, 2);
        twoLists.add(1, 3);
        twoLists.add(1, 4);
        twoLists.add(1, 5);
        twoLists.add(2, 11);
        twoLists.add(2, 12);
        twoLists.add(2, 13);

        twoLists.chooseIterator(TwoLists.CARTESIAN);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    void oneThenTwoFirstEmptyTest() {
        List<Integer> expected = Arrays.asList(11, 12, 13);
        twoLists.add(2, 11);
        twoLists.add(2, 12);
        twoLists.add(2, 13);

        twoLists.chooseIterator(TwoLists.ONE_THEN_TWO);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    void alternatingFirstEmptyTest() {
        List<Integer> expected = Arrays.asList(11, 12, 13);
        twoLists.add(2, 11);
        twoLists.add(2, 12);
        twoLists.add(2, 13);

        twoLists.chooseIterator(TwoLists.ALTERNATING);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    void cartesianFirstEmptyTest() {
        List<Integer> expected = new ArrayList<Integer>();
        twoLists.add(2, 11);
        twoLists.add(2, 12);
        twoLists.add(2, 13);

        twoLists.chooseIterator(TwoLists.CARTESIAN);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    void oneThenTwoSecondEmptyTest() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        twoLists.add(1, 1);
        twoLists.add(1, 2);
        twoLists.add(1, 3);
        twoLists.add(1, 4);
        twoLists.add(1, 5);

        twoLists.chooseIterator(TwoLists.ONE_THEN_TWO);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    void alternatingSecondEmptyTest() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        twoLists.add(1, 1);
        twoLists.add(1, 2);
        twoLists.add(1, 3);
        twoLists.add(1, 4);
        twoLists.add(1, 5);

        twoLists.chooseIterator(TwoLists.ALTERNATING);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    void cartesianTwoSecondEmptyTest() {
        List<Integer> expected = new ArrayList<Integer>();
        twoLists.add(1, 1);
        twoLists.add(1, 2);
        twoLists.add(1, 3);
        twoLists.add(1, 4);
        twoLists.add(1, 5);

        twoLists.chooseIterator(TwoLists.CARTESIAN);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    void oneThenTwoBothEmptyTest() {
        List<Integer> expected = new ArrayList<Integer>();

        twoLists.chooseIterator(TwoLists.ONE_THEN_TWO);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    void alternatingBothEmptyTest() {
        List<Integer> expected = new ArrayList<Integer>();

        twoLists.chooseIterator(TwoLists.ALTERNATING);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    void cartesianTwoBothEmptyTest() {
        List<Integer> expected = new ArrayList<Integer>();

        twoLists.chooseIterator(TwoLists.CARTESIAN);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    private void verify(List<Integer> expected, Iterator<Integer> it) {
        for (Integer next : expected) {
            assertTrue(it.hasNext());
            assertEquals(next, it.next());
        }
        assertFalse(it.hasNext());
    }
}
