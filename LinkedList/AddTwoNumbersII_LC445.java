package LinkedList;

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

public class AddTwoNumbersII_LC445 {


    
    private ListNode reverseList(ListNode Head) {
        ListNode curr = Head;
        ListNode prev = null;
        ListNode forward = null;

        while (curr != null) {
            forward = curr.next;
            curr.next = prev;

            prev = curr;
            curr = forward;
        }

        return prev;

    }

    private ListNode additionOfTwoList(ListNode First, ListNode Second) {

        ListNode ansHead = new ListNode(-1);
        ListNode ansTail = ansHead;

        int carry = 0;

        while (First != null || Second != null || carry != 0) {
            int val1 = 0, val2 = 0;
            if (First != null) {
                val1 = First.val;
            }
            if (Second != null) {
                val2 = Second.val;
            }
            int sum = carry + val1 + val2;

            int digit = sum % 10;
            carry = sum / 10;

            ListNode newNode = new ListNode(digit);
            ansTail.next = newNode;
            ansTail = newNode;

            if (First != null) {
                First = First.next;
            }
            if (Second != null) {
                Second = Second.next;
            }
        }

        ansHead = ansHead.next;

        return ansHead;

    }

    //Time: O(N + M + Max(N,M)), Space: O(Max(N,M))
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // Reversing list to do addition from right to left digits
        ListNode firstList = reverseList(l1);
        ListNode secondList = reverseList(l2);

        // Add 2 numbers and creating result linked-list but it will be in reverse order
        // as it does calculate from Right to Left
        ListNode ansHead = additionOfTwoList(firstList, secondList);

        // Inorder to get correct order, we will reverse the result list
        ansHead = reverseList(ansHead);

        return ansHead;

    }
}