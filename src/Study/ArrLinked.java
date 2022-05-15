package Study;

import java.util.ArrayList;
import java.util.LinkedList;

public class ArrLinked {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(12);
        list.add(0, 5);
        list.remove(2);

        LinkedList<String> linked = new LinkedList<>();
        linked.add("Max");
        linked.add("Vlad");
        linked.addLast("Dim");
        linked.add(0, "Alex");
        linked.remove(2);

        for (Integer x : list)
            System.out.println(x);

        for (String name : linked)
            System.out.println(name);
    }
}
