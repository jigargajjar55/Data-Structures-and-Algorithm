package LinkedList;

public class PartitionList_LC86 {
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

    // https://leetcode.com/problems/partition-list/solutions/2315494/java-c-detailed-explanation/
    
    //Time: O(N), Space: O(N){For result LinkedList}

    private ListNode partition(ListNode head, int x) {

        ListNode leftHead = new ListNode(-1);
        ListNode leftNode = leftHead;
        ListNode rightHead = new ListNode(-1);
        ListNode rightNode = rightHead;

        ListNode curr = head;
        ListNode forward = null;

        while (curr != null) {

            forward = curr.next;
            curr.next = null;

            if (curr.val < x) {
                leftNode.next = curr;
                leftNode = leftNode.next;

            } else {
                rightNode.next = curr;
                rightNode = rightNode.next;
            }
            curr = forward;
        }

        ListNode resultHead = leftHead;
        leftNode.next = rightHead.next;
        resultHead = resultHead.next;

        return resultHead;

    }
}