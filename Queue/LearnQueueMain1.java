package Queue;

import java.util.Scanner;

public class LearnQueueMain1 {

    Node head;
    Node tail;
    int size;
    int count = 0;

    // Queue using Array
    public static class Queue {
        int arr[];
        int qfront;
        int rear;
        int size;

        Queue(int size) {
            this.size = size;
            arr = new int[size];
            qfront = 0;
            rear = 0;
        }

        boolean isEmpty() {
            if (qfront == rear) {
                return true;
            } else {
                return false;
            }
        }

        // For adding element in rear end of Queue
        void enqueue(int data) {
            // If queue is full
            if (rear == size) {
                System.out.println("Queue is full!");
                return;
            }

            arr[rear] = data;
            rear++;

        }

        // For removing element from front end of Queue
        int dequeue() {
            // If queue is empty
            if (qfront == rear) {
                return -1;
            } else {
                int ans = arr[qfront];
                qfront++;

                if (qfront == rear) {
                    qfront = 0;
                    rear = 0;
                }
                return ans;
            }

        }

        int front() {
            if (qfront == rear) {
                return -1;
            }

            return arr[qfront];

        }

    }

    // Queue using LinkedList
    public class Node {
        int data;
        Node next;

        Node(int d) {
            this.data = d;
            this.next = null;
        }
    }

    boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    void enqueue(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            count++;
        } else {
            if (count < size) {
                tail.next = newNode;
                tail = newNode;
                count++;
            } else {
                System.out.println("Queue is full!");
            }

        }
    }

    int dequeue() {
        if (head == null) {
            System.out.println("Queue is empty!");
            return -1;
        }
        int ans = head.data;
        head = head.next;
        count--;
        return ans;
    }

    int front() {
        if (head == null) {
            System.out.println("Queue is empty!");
            return -1;
        }

        return head.data;
    }

    public static void main(String[] args) {
        /*
         * Queue<Integer> q = new LinkedList<>();
         * q.offer(2);
         * q.offer(3);
         * q.offer(4);
         * System.out.println(q.peek());
         * q.poll();
         * System.out.println(q.size());
         * q.poll();
         * System.out.println(q.peek());
         * q.poll();
         * System.out.println(q.isEmpty());
         */
        // Implement Queue using Array
        /*
         * Queue q = new Queue(3);
         * 
         * q.enqueue(0);
         * q.enqueue(1);
         * System.out.println(q.dequeue());
         * System.out.println(q.dequeue());
         * System.out.println(q.isEmpty());
         */

        // Implement Queue using LinkedList
        LearnQueueMain1 lq = new LearnQueueMain1();

        lq.size = 3;
        lq.enqueue(0);
        lq.enqueue(1);
        lq.enqueue(2);

        System.out.println(lq.dequeue());
        System.out.println(lq.isEmpty());

    }

}
