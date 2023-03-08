package Queue;

import java.util.*;
import java.io.*;

public class CircularQueueMain {

    public class CircularQueue {
        int arr[];
        int front;
        int rear;
        int size;

        // Initialize your data structure.
        public CircularQueue(int n) {
            size = n;
            arr = new int[size];
            front = -1;
            rear = -1;
        }

        /*
         * Enqueues 'X' into the queue. Returns true if it gets pushed into the stack,
         * and false otherwise.
         */
        public boolean enqueue(int value) {
            // To check whether queue is full
            if ((front == 0 && rear == size - 1) || (rear == (front - 1) % (size - 1))) {
                // System.out.println("Queue is full!");
                return false;
            } else if (rear == -1) { // Single element
                front = 0;
                rear = 0;
            } else if (rear == size - 1 && front != 0) {
                rear = 0; // To maintain Circular nature
            } else {
                // Normal case
                rear++;
            }
            arr[rear] = value;
            return true;
        }

        /*
         * Dequeues top element from queue. Returns -1 if the stack is empty, otherwise
         * returns the popped element.
         */
        public int dequeue() {
            // To check queue is empty
            if (front == -1) {
                // System.out.println("Queue is empty!");
                return -1;
            }
            int ans = arr[front];
            arr[front] = -1;
            // Single element
            if (front == rear) {
                front = -1;
                rear = -1;
            } else if (front == size - 1) {
                front = 0; // To maintain Circular nature
            } else {
                // Normal case
                front++;
            }
            return ans;
        }
    };

}
