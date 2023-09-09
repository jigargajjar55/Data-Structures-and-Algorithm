package LinkedList;

import java.util.*;

public class SplitLinkedListinParts_LC725 {
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

    private int getLength(ListNode head) {
        ListNode ptr = head;
        int count = 0;
        while (ptr != null) {
            count++;
            ptr = ptr.next;
        }

        return count;

    }

    // Time: O(N + K + (N + K)), Space: O(K){1D array}
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] result = new ListNode[k];
        // Edge Case
        if (head == null) {
            return result;
        }

        int linkedListLength = getLength(head);

        int[] nodeCount = new int[k];

        int quotient = (linkedListLength / k);
        if (quotient > 0) {
            Arrays.fill(nodeCount, quotient);
        }

        int reminder = (linkedListLength % k);
        int index = 0;

        while (reminder > 0) {
            nodeCount[index]++;
            index++;
            reminder--;
        }

        ListNode ptr = head;
        for (int i = 0; i < k; i++) {

            int nodes = nodeCount[i];

            if (nodes > 0 && ptr != null) {
                ListNode ansHead = new ListNode(-1);
                ListNode ansTail = ansHead;

                while (nodes > 0 && ptr != null) {
                    ansTail.next = new ListNode(ptr.val);
                    ansTail = ansTail.next;
                    ptr = ptr.next;
                    nodes--;
                }
                ansHead = ansHead.next;

                result[i] = ansHead;
            }
        }

        return result;

    }
}