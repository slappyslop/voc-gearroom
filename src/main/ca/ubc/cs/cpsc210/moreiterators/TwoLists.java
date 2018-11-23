package ca.ubc.cs.cpsc210.moreiterators;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection-like class that stores its data in two separate lists
 *
 * Iterating over the data could return the data in a number of different ways:
 *      1) all the data from listone followed by all the data from listtwo
 *      2) the data from listone and listtwo alternating (one from listone, then
 *         one from listtwo, then one from listone, ...). When one list runs out, then
 *         return the rest of the data from the other list until it too runs out
 *      3) the data from the two lists combined (cartesian product style) by multiplying.
 *         If there are three items in each list, named l11, l12, l13 and l21, l22, l23
 *         This would be l11 * l21, l11 * l22, l11 * l23,
 *                       l12 * l21, l12 * l22, l12 * l23,
 *                       l13 * l21, l13 * l22, l13 * l23,
 */
public class TwoLists {
    private List<Integer> listone;
    private List<Integer> listtwo;

    public TwoLists() {
        listone = new ArrayList<Integer>();
        listtwo = new ArrayList<Integer>();
    }

    public void add(int which, Integer value) {
        switch (which) {
            case 1:
                listone.add(value);
                break;
            case 2:
                listtwo.add(value);
                break;
        }
    }
}
