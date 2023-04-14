package LinkedList;

import java.util.*;

public class CircularLinkedList {

    Node tail;

    public class Node {
        int data;
        Node next;

        Node(int d) {
            this.data = d;
            this.next = null;
        }
    }

    public void insertNode(int element, int d) {

        // assuming that the element is present

        // Empty CLL
        if (tail == null) {
            Node newNode = new Node(d);
            tail = newNode;
            newNode.next = newNode;
        } else {
            // Non-empty list
            Node curr = tail;

            while (curr.data != element) {
                curr = curr.next;
            }

            // element is found at arr node
            Node temp = new Node(d);
            temp.next = curr.next;
            curr.next = temp;
        }
    }

    public void deleteNode(int value) {
        // empty cll
        if (tail == null) {
            System.out.println("List is empty!");
            return;
        } else {
            // Non-empty
            // Assuming that value is present
            Node prev = tail;
            Node curr = prev.next;

            while (curr.data != value) {
                prev = curr;
                curr = curr.next;
            }

            prev.next = curr.next;

            // 1 Node CLL
            if (curr == prev) {
                tail = null;
            }
            // >= 2 Node CLL
            else if (tail == curr) {
                tail = prev;
            }
            curr.next = null;
        }

    }

    public void print() {

        if (tail == null) {
            System.out.println("Empty CLL");
            return;
        }

        Node temp = tail;

        do {
            System.out.print(temp.data + "  ");
            temp = temp.next;
        } while (temp != tail);

        System.out.println();
    }

    public boolean isCircularLL() {
        // LL is empty
        if (tail == null) {
            return true;
        }

        // >=1 nodes in LL
        Node temp = tail.next;

        while (temp != null && temp != tail) {
            temp = temp.next;
        }

        if (temp == tail) {
            return true;
        }

        return false;

    }

    public boolean detectLoop() {
        if (tail == null) {
            return false;
        }
        Map<Node, Boolean> visited = new HashMap<>();

        while (tail != null) {

            // cycle is present
            if (visited.get(tail) == true) {
                return true;
            }

            visited.put(tail, true);
            tail = tail.next;

        }

        return false;
    }

    public static void main(String[] args) {

        CircularLinkedList cdl = new CircularLinkedList();

        // cdl.insertNode(0,10);
        // cdl.insertNode(10,20);
        // cdl.insertNode(20,30);
        // cdl.insertNode(30,9);
        cdl.print();

        if (cdl.isCircularLL()) {
            System.out.println("It's circular Linked list!");
        } else {
            System.out.println("It's not circular Linked list!");
        }

        // cdl.deleteNode(20);
        // cdl.print();

    }

}
