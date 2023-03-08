package Stack;

import java.util.*;

public class LearnStackMain1 {

    // Using Array
    public static class StackUsingArray {
        int arr[];
        int top;
        int size;

        StackUsingArray(int size) {
            this.size = size;
            arr = new int[size];
            top = -1;
        }

        void push(int element) {
            if (top < size - 1) {
                top++;
                arr[top] = element;
            } else {
                System.out.println("Stack Overflow!");
            }

        }

        void pop() {
            if (top >= 0) {
                top--;

            } else {
                System.out.println("Stack is empty");
            }

        }

        int peek() {
            if (top >= 0) {
                return arr[top];
            } else {
                System.out.println("Stack is Empty!");
                return -1;
            }

        }

        boolean isEmpty() {
            if (top == -1) {
                return true;
            } else {
                return false;
            }
        }

    }

    Node head;
    int count = 0;
    int size;

    public class Node {
        int data;
        Node next;

        Node(int d) {
            this.data = d;
            this.next = null;
        }

    }

    public void push(int d) {
        Node newNode = new Node(d);
        if (head == null) {
            count++;
            head = newNode;
            return;
        }
        // System.out.println(count);
        if (count < size) {
            newNode.next = head;
            head = newNode;
            count++;
        } else {
            System.out.println("Stack is overflow!");
        }

    }

    public void pop() {
        if (head == null) {
            System.out.println("Stack is empty!");
            return;
        }
        count--;
        head = head.next;

    }

    public int peek() {
        if (head != null) {
            return head.data;
        }
        return -1;
    }

    public void print() {
        if (head == null) {
            System.out.println("Stack is empty!");
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "  ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /*
         * TODO Auto-generated method stub
         * Stack<Integer> s = new Stack();
         * 
         * s.push(1);
         * s.push(2);
         * s.push(3);
         * 
         * System.out.println(s.peek());
         * System.out.println(s.pop());
         * System.out.println(s.isEmpty());
         */

        // StackUsingArray stack = new StackUsingArray(5);
        // stack.push(23);
        // stack.push(24);
        // stack.push(25);
        // stack.push(25);
        // stack.push(25);
        // stack.pop();
        // System.out.println(stack.peek());
        // System.out.println(stack.isEmpty());

        LearnStackMain1 stack = new LearnStackMain1();
        stack.size = 3;

        // stack.push(50);
        // stack.push(40);
        // stack.push(30);
        // stack.push(20);
        // stack.push(10);
        // stack.print();
        // stack.pop();
        // stack.print();

        // System.out.println("Peek element of stack: " + stack.peek());

        String str = "Gajjar";
        Stack<Character> s = new Stack<>();

        for (char c : str.toCharArray()) {
            s.push(c);
        }
        String ans = "";
        while (!s.isEmpty()) {
            ans += s.pop();
        }

        System.out.println(ans);

    }

}
