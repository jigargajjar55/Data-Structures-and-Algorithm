package Heap;

import java.util.*;

public class LearnPriorityQueue {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        pq.offer(40);
        pq.offer(12);
        pq.offer(24);
        pq.offer(36);

        System.out.println(pq);

        pq.poll();
        System.out.println(pq);

        System.out.println(pq.peek());
        pq.poll();
        System.out.println(pq);

        System.out.println(pq.peek());
    }

}
