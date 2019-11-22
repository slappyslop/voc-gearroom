package ca.ubc.cs.cpsc210.moreiterators;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection-like class that stores its data in two separate lists
 *
 * Iterating over the data could return the data in a number of different ways:
 *      1) OneThenTwo:  all the data from listone followed by all the data from listtwo
 *      2) Alternating: the data from listone and listtwo alternating (one from listone, then
 *         one from listtwo, then one from listone, ...). When one list runs out, then
 *         return the rest of the data from the other list until it too runs out
 *      3) Cartesian: the data from the two lists combined (cartesian product style) by multiplying.
 *         If there are four items in listone, named l11, l12, l13, l14 and three items
 *         in listtwo named:l21, l22, l23, then these elements would be return:
 *                       l11 * l21, l11 * l22, l11 * l23,
 *                       l12 * l21, l12 * l22, l12 * l23,
 *                       l13 * l21, l13 * l22, l13 * l23,
 *                       l14 * l21, l14 * l22, l14 * l23,
 *      A client can choose which iterator is returned by calling the chooseIterator() method before calling
 *      iterator().
 */
public class TwoLists {
    private List<Integer> listone;
    private List<Integer> listtwo;
    public static final int OneThenTwo = 0;
    public static final int Alternating = 1;
    public static final int Cartesian = 2;
    private int whichIterator = -1;

    public TwoLists() {
        listone = new ArrayList<Integer>();
        listtwo = new ArrayList<Integer>();
    }

    // EFFECTS: Choose which iterator should be returned when iterator() is called. If no iterator
    //  has been chosen, then return a random iterator.
    public void chooseIterator(int whichIterator) {
        this.whichIterator = whichIterator;
    }

    public void add(int which, Integer value) {
        switch (which) {
            case 1:
                listone.add(value);
                break;
            case 2:
                listtwo.add(value);
                break;
            default:
                throw new RuntimeException("Invalid list index in TwoLists.add");
        }
    }
}
