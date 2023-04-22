package Queue;

import java.util.*;

public class DoublyEndedQueueMain {

    public static class Deque {
        int arr[];
        int front;
        int rear;
        int size;

        // Initialize your data structure.
        public Deque(int n) {
            size = n;
            arr = new int[size];
            front = -1;
            rear = -1;
        }

        // Pushes 'X' in the front of the deque. Returns true if it gets pushed into the
        // deque, and false otherwise.
        public boolean pushFront(int x) {
            // Check if dequeue full
            if (isFull()) {
                // System.out.println("Queue is full!");
                return false;
            } else if (isEmpty()) {
                front = 0;
                rear = 0;
            } else if (front == 0 && rear != size - 1) {
                front = size - 1;
            } else {
                front--;
            }
            arr[front] = x;
            return true;
        }

        // Pushes 'X' in the back of the deque. Returns true if it gets pushed into the
        // deque, and false otherwise.
        public boolean pushRear(int x) {
            // Check if dequeue full
            if (isFull()) {
                // System.out.println("Queue is full!");
                return false;
            } else if (isEmpty()) {
                front = 0;
                rear = 0;
            } else if (rear == size - 1 && front != 0) {
                rear = 0;
            } else {
                rear++;
            }
            arr[rear] = x;
            return true;
        }

        // Pops an element from the front of the deque. Returns -1 if the deque is
        // empty, otherwise returns the popped element.
        public int popFront() {
            if (isEmpty()) {
                return -1;
            }

            int ans = arr[front];
            arr[front] = -1;

            if (front == rear) {
                front = -1;
                rear = -1;
            } else if (front == size - 1) {
                front = 0;
            } else {
                front++;
            }
            return ans;
        }

        // Pops an element from the back of the deque. Returns -1 if the deque is empty,
        // otherwise returns the popped element.
        public int popRear() {
            if (isEmpty()) {
                return -1;
            }

            int ans = arr[rear];
            arr[rear] = -1;

            if (front == rear) {
                front = -1;
                rear = -1;
            } else if (rear == 0) {
                rear = size - 1;
            } else {
                rear--;
            }
            return ans;
        }

        // Returns the first element of the deque. If the deque is empty, it returns -1.
        public int getFront() {
            if (isEmpty()) {
                return -1;
            }

            return arr[front];
        }

        // Returns the last element of the deque. If the deque is empty, it returns -1.
        public int getRear() {
            if (isEmpty()) {
                return -1;
            }

            return arr[rear];
        }

        // Returns true if the deque is empty. Otherwise returns false.
        public boolean isEmpty() {
            if (front == -1) {
                return true;
            } else {
                return false;
            }
        }

        // Returns true if the deque is full. Otherwise returns false.
        public boolean isFull() {
            if ((front == 0 && rear == size - 1) || (front != 0 && rear == (front - 1) % (size - 1))) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        /*
         * // ArrayDeque Collection framework in java
         * ArrayDeque<Integer> adq = new ArrayDeque<>();
         * 
         * adq.offerFirst(1);
         * adq.offerLast(12);
         * adq.offerLast(123);
         * System.out.println(adq);
         * System.out.println(adq.pollFirst());
         * 
         * System.out.println(adq);
         * System.out.println(adq.pollLast());
         * System.out.println(adq.pollLast());
         * System.out.println(adq.peek());
         * System.out.println(adq.peekFirst());
         * System.out.println(adq.peekLast());
         * 
         * System.out.println(adq.poll());
         * System.out.println("poll() :" + adq);
         * 
         * System.out.println(adq.pollFirst());
         * System.out.println("poll() :" + adq);
         * 
         * System.out.println(adq.pollLast());
         * System.out.println("poll() :" + adq);
         * 
         * System.out.println(adq.isEmpty());
         */

    }

}
