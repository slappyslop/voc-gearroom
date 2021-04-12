package ca.ubc.cs.cpsc210.moreiterators;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection-like class that stores its data in two separate lists
 *
 * Iterating over the data could return the data in a number of different ways:
 *      1) OneThenTwo:  all the data from listOne followed by all the data from listTwo
 *      2) Alternating: the data from listOne and listTwo alternating (one from listOne, then
 *         one from listTwo, then one from listOne, ...). When one list runs out, then
 *         return the rest of the data from the other list until it too runs out
 *      3) Cartesian: the data from the two lists combined (cartesian product style) by multiplying.
 *         If there are four items in listOne, named l11, l12, l13, l14 and three items
 *         in listTwo named l21, l22, l23, then these elements would be returned:
 *                       l11 * l21, l11 * l22, l11 * l23,
 *                       l12 * l21, l12 * l22, l12 * l23,
 *                       l13 * l21, l13 * l22, l13 * l23,
 *                       l14 * l21, l14 * l22, l14 * l23,
 *      A client can choose which iterator is returned by calling the chooseIterator() method before calling
 *      iterator().
 */
public class TwoLists {

    public static final int ONE_THEN_TWO = 0;
    public static final int ALTERNATING = 1;
    public static final int CARTESIAN = 2;

    private List<Integer> listOne;
    private List<Integer> listTwo;

    private int whichIterator = -1;

    public TwoLists() {
        this.listOne = new ArrayList<>();
        this.listTwo = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Sets the iterator that will be returned when iterator() is called. If no iterator
    // has been chosen, then return a random iterator.
    public void chooseIterator(int whichIterator) {
        this.whichIterator = whichIterator;
    }

    // MODIFIES: this
    // EFFECTS: adds value to list one if which is 1, and to list two if which is 2;
    // throws a RuntimeException if which is neither 1 nor 2
    public void add(int which, int value) {
        switch (which) {
            case 1:
                this.listOne.add(value);
                break;
            case 2:
                this.listTwo.add(value);
                break;
            default:
                throw new IllegalArgumentException("TwoLists.add with illegal which argument " + which);
        }
    }
}
