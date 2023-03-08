package Queue;

import java.util.Arrays;

public class kQueueinAnArrayMain {

    public static class kQueue {
        int n;
        int k;
        int front[];
        int rear[];
        int freeSpot;
        int next[];
        int arr[];

        kQueue(int n, int k) {
            this.n = n;
            this.k = k;
            front = new int[k];
            rear = new int[k];
            Arrays.fill(front, -1);
            Arrays.fill(rear, -1);
            next = new int[n];
            for (int i = 0; i < n; i++) {
                next[i] = i + 1;
            }
            next[n - 1] = -1;
            arr = new int[n];
            freeSpot = 0;
        }

        void enqueue(int data, int qn) {
            // Overflow condition
            if (freeSpot == -1) {
                System.out.println("No empty space present!");
                return;
            }

            // find first free index
            int index = freeSpot;

            freeSpot = next[index];
            // check whether first element
            if (front[qn - 1] == -1) {
                front[qn - 1] = index;
            } else {
                // link new element to the previous element
                next[rear[qn - 1]] = index;
            }

            next[index] = -1;

            rear[qn - 1] = index;

            arr[index] = data;
        }

        int dequeue(int qn) {
            // underflow
            if (front[qn - 1] == -1) {
                System.out.println("Queue is underflow!");
                return -1;
            }

            int index = front[qn - 1];
            front[qn - 1] = next[index];

            next[index] = freeSpot;
            freeSpot = index;

            return arr[index];
        }

        void print() {
            System.out.println(Arrays.toString(arr));
        }

    }

    public static void main(String[] args) {

        kQueue k = new kQueue(10, 3);

        k.enqueue(10, 1);
        k.enqueue(15, 1);
        k.enqueue(20, 2);
        k.enqueue(25, 1);
        k.enqueue(30, 1);
        k.enqueue(35, 1);
        k.enqueue(40, 1);
        k.enqueue(45, 1);
        k.enqueue(50, 1);
        k.enqueue(55, 1);
        k.print();

        System.out.println(k.dequeue(1));
        System.out.println(k.dequeue(2));
        System.out.println(k.dequeue(1));
        System.out.println(k.dequeue(1));

        k.enqueue(250, 1);
        k.enqueue(251, 1);
        k.enqueue(252, 1);
        k.enqueue(253, 1);
        k.print();

    }

}
