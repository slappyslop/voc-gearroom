package ca.ubc.cs.cpsc210.moreiterators;

public class Main {

    public static void main(String[] args) {
        TwoLists twoLists = new TwoLists();

        twoLists.add(1, 1);
        twoLists.add(1, 2);
        twoLists.add(1, 3);
        twoLists.add(1, 4);
        twoLists.add(1, 5);
        twoLists.add(2, 11);
        twoLists.add(2, 12);
        twoLists.add(2, 13);

        for (Integer i : twoLists) {
            System.out.println(i);
        }
    }
}
