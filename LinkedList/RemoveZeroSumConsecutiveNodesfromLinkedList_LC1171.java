package LinkedList;
import java.util.*;

class RemoveZeroSumConsecutiveNodesfromLinkedList_LC1171 {

    // https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/solutions/366350/c-o-n-explained-with-pictures/?envType=daily-question&envId=2024-03-12

    // Time: O(N)
    // Space: O(N)
    //2 pass solution

    class ListNode {
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

    
    public ListNode removeZeroSumSublists(ListNode head) {

        Map<Integer, ListNode> map = new HashMap<>();

        ListNode newHead = new ListNode(0);
        newHead.next = head;

        int sum = 0;

        for (ListNode curr = newHead; curr != null; curr = curr.next) {

            sum += curr.val;
            map.put(sum, curr);
        }

        sum = 0;

        for (ListNode curr = newHead; curr != null; curr = curr.next) {

            sum += curr.val;

            curr.next = map.get(sum).next;
        }

        newHead = newHead.next;

        return newHead;
    }
}