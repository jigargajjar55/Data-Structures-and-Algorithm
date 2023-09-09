package LinkedList;

import java.util.*;

public class ReverseLinkedListII_LC92 {

    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private ListNode getRevLL(ListNode head) {
        ListNode prev = null;
        ListNode forward = null;
        ListNode curr = head;

        while (curr != null) {
            forward = curr.next;
            curr.next = prev;

            prev = curr;
            curr = forward;
        }

        return prev;
    }

    // Time: O(N + (~N)), Space: O(1)
    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode ptr = head;
        ListNode prev = null;
        ListNode forward = null;
        ListNode leftNode = null;
        ListNode rightNode = null;

        int count = 0;
        // Need to identify left and right position nodes

        while (ptr != null) {
            count++;

            // If count is eqaul left position, we found left node
            if (count == left) {
                leftNode = ptr;
            }

            // If leftNode not found yet, we will consider previous node
            if (leftNode == null) {
                prev = ptr;
            }

            // If count is eqaul right position, we found right node and next node will be
            // forward node and break loop
            if (count == right) {
                rightNode = ptr;
                ptr = ptr.next;
                forward = ptr;
                break;
            }

            ptr = ptr.next;
        }

        // Will separate part from linked list that need to reverse
        if (prev != null) {
            prev.next = null;
        }
        if (rightNode != null) {
            rightNode.next = null;
        }

        // reverse linkedlist part
        ListNode revList = getRevLL(leftNode);

        // Form result list by connecting reverse linked list part
        ListNode ansHead = null;
        // If left is 1, no previous node
        if (prev == null) {
            ansHead = revList;
        } else {
            ansHead = head;
            prev.next = revList;
        }
        // Connecting reverse list tail into list
        leftNode.next = forward;

        return ansHead;
    }
}