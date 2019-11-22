package ca.ubc.cs.cpsc210.moreiterators;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TwoListTest {
    private TwoLists twoLists = new TwoLists();

    @Test
    public void oneThenTwoTest() {
        int expected[] = { 1, 2, 3, 4, 5, 11, 12, 13 };
        twoLists.add(1, 1); twoLists.add(1, 2); twoLists.add(1, 3); twoLists.add(1, 4); twoLists.add(1, 5);
        twoLists.add(2, 11); twoLists.add(2, 12); twoLists.add(2, 13);

        twoLists.chooseIterator(TwoLists.OneThenTwo);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    public void alternatingTest() {
        int expected[] = { 1, 11, 2, 12, 3, 13, 4, 5 };
        twoLists.add(1, 1); twoLists.add(1, 2); twoLists.add(1, 3); twoLists.add(1, 4); twoLists.add(1, 5);
        twoLists.add(2, 11); twoLists.add(2, 12); twoLists.add(2, 13);

        twoLists.chooseIterator(TwoLists.Alternating);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    public void cartesianTest() {
        int expected[] = { 11, 12, 13, 22, 24, 26, 33, 36, 39, 44, 48, 52, 55, 60, 65 };
        twoLists.add(1, 1); twoLists.add(1, 2); twoLists.add(1, 3); twoLists.add(1, 4); twoLists.add(1, 5);
        twoLists.add(2, 11); twoLists.add(2, 12); twoLists.add(2, 13);

        twoLists.chooseIterator(TwoLists.Cartesian);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    public void oneThenTwoOneEmptyTest() {
        int expected[] = { 11, 12, 13 };
        twoLists.add(2, 11); twoLists.add(2, 12); twoLists.add(2, 13);

        twoLists.chooseIterator(TwoLists.OneThenTwo);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    public void alternatingOneEmptyTest() {
        int expected[] = { 11, 12, 13 };
        twoLists.add(2, 11); twoLists.add(2, 12); twoLists.add(2, 13);

        twoLists.chooseIterator(TwoLists.Alternating);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    public void cartesianOneEmptyTest() {
        int expected[] = { };
        twoLists.add(2, 11); twoLists.add(2, 12); twoLists.add(2, 13);

        twoLists.chooseIterator(TwoLists.Cartesian);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    public void oneThenTwoOTwoEmptyTest() {
        int expected[] = { 1, 2, 3, 4, 5 };
        twoLists.add(1, 1); twoLists.add(1, 2); twoLists.add(1, 3); twoLists.add(1, 4); twoLists.add(1, 5);

        twoLists.chooseIterator(TwoLists.OneThenTwo);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    public void alternatingTwoEmptyTest() {
        int expected[] = { 1, 2, 3, 4, 5 };
        twoLists.add(1, 1); twoLists.add(1, 2); twoLists.add(1, 3); twoLists.add(1, 4); twoLists.add(1, 5);

        twoLists.chooseIterator(TwoLists.Alternating);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    @Test
    public void cartesianTwoEmptyTest() {
        int expected[] = { };
        twoLists.add(1, 1); twoLists.add(1, 2); twoLists.add(1, 3); twoLists.add(1, 4); twoLists.add(1, 5);

        twoLists.chooseIterator(TwoLists.Cartesian);
        Iterator<Integer> it = twoLists.iterator();
        verify(expected, it);
    }

    private void verify(int[] expected, Iterator<Integer> it) {
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], it.next());
        }
        assertFalse(it.hasNext());
    }
}
