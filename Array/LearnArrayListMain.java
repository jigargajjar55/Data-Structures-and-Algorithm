package Array;

import java.util.*;

public class LearnArrayListMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // List<Integer> list = new ArrayList<>();
        //
        // list.add(1);
        // list.add(2);
        // list.add(3);
        // System.out.println(list);
        //
        // list.set(0, 10);
        // System.out.println(list);
        //
        // List<Integer> newList = new ArrayList<>();
        // newList.add(150);
        // newList.add(160);
        //
        // list.addAll(newList);
        // System.out.println(list);
        //
        //
        // System.out.println(list.get(4));

        List<Integer> list = new ArrayList<>();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);
        list.add(70);
        list.add(80);
        list.add(90);
        list.add(100);
        // list.remove(1);
        // System.out.println(list);
        // list.remove(Integer.valueOf(80));
        // System.out.println(list);
        // list.clear();
        // System.out.println(list);

        System.out.println(list.contains(50));

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            System.out.println("iterator: " + it.next());
        }
    }

}
