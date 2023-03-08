package LinkedList;

public class DoublyLinkedList {

    Node head;
    Node tail;

    public class Node {
        int data;
        Node prev;
        Node next;

        Node(int d) {
            this.data = d;
            this.prev = null;
            this.next = null;
        }
    }

    public void insertAtHead(int d) {

        Node temp = new Node(d);

        if (head == null) {
            head = temp;
            tail = temp;
        } else {
            temp.next = head;
            head.prev = temp;
            head = temp;
        }

    }

    public void insertAtTail(int d) {
        Node temp = new Node(d);

        // Node tail = head;

        // If DLL is empty
        if (tail == null) {
            head = temp;
            tail = temp;
            return;
        }

        // while(tail.next != null) {
        // tail = tail.next;
        // }

        temp.prev = tail;
        tail.next = temp;
        tail = temp;

    }

    // Append the node in doubly LL
    public void append(int d) {
        Node new_node = new Node(d);

        // Node last = head;

        new_node.next = null;

        if (head == null) {
            new_node.prev = null;
            head = new_node;
            tail = new_node;
            return;
        }

        // while(last.next != null) {
        // last = last.next;
        // }

        tail.next = new_node;

        new_node.prev = tail;
        tail = new_node;

    }

    public void insertAtPosition(int position, int d) {

        if (position == 1) {
            insertAtHead(d);
            return;
        }

        Node temp = head;
        int cnt = 1;

        while (cnt < position - 1) {
            temp = temp.next;
            cnt++;
        }

        if (temp.next == null) {
            insertAtTail(d);
            return;
        }

        Node new_node = new Node(d);

        new_node.next = temp.next;

        temp.next.prev = new_node;

        temp.next = new_node;

        new_node.prev = temp;

    }

    public void deleteAtPosition(int position) {

        if (position == 1) {
            Node temp = head;
            temp.next.prev = null;
            head = temp.next;
            temp.next = null;
        } else {

            Node prevNode = null;
            Node currNode = head;

            int count = 1;
            while (count <= position - 1) {
                prevNode = currNode;
                currNode = currNode.next;
                count++;
            }

            prevNode.next = currNode.next;
            currNode.next.prev = prevNode;

            currNode.prev = null;

            currNode.next = null;

        }

    }

    public void print() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + "  ");
            temp = temp.next;
        }
        System.out.println();
    }

    public int getLength() {
        Node temp = head;
        int len = 0;

        while (temp != null) {
            len++;
            temp = temp.next;
        }
        return len;
    }

    public static void main(String[] args) {

        DoublyLinkedList dll = new DoublyLinkedList();
        // System.out.println(dll.getLength());

        dll.append(12);
        dll.append(120);
        dll.print();

        dll.insertAtHead(1);
        dll.print();

        dll.insertAtTail(1200);
        dll.print();

        // dll.insertAtPosition(3, 13);
        // dll.print();
        //
        // dll.insertAtPosition(6, 1201);
        // dll.print();
        //
        // dll.deleteAtPosition(2);
        // dll.print();

    }

}
