package ca.ubc.cs.cpsc210.moreiterators;

public class Main {

    public static void main(String[] args) {
        TwoLists tl = new TwoLists();

        tl.add(1, 1); tl.add(1, 2); tl.add(1, 3); tl.add(1, 4); tl.add(1, 5);
        tl.add(2, 11); tl.add(2, 12); tl.add(2, 13);

        for (Integer i : tl) {
            System.out.println(i);
        }
    }
}
