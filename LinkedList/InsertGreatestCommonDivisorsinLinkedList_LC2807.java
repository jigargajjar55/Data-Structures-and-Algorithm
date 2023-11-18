package LinkedList;
import java.util.*;


class Solution {
    
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private int getGCD(int a,int b){        
        
        if(b == 0){
            return a;
        }
        
        return getGCD(b, a % b);
        
    }
    
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        
        ListNode curr = head;
        int val1 = curr.val;
        
        ListNode prev = curr;        
        curr = curr.next;
        
        while(curr != null){
            
            int val2 = curr.val;
            
            int gcdValue = getGCD(val1,val2);
            
            ListNode newNode = new ListNode(gcdValue);
            prev.next = newNode;           
            newNode.next = curr;
            
            prev = curr;
            curr = curr.next;
            
            val1 = val2;

        }
       
        return head;
        
    }
}