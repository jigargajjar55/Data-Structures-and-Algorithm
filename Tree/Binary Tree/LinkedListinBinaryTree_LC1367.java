package Tree.Binary;

public class LinkedListinBinaryTree_LC1367 {

    // Definition for singly-linked list.
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

    // Definition for a binary tree node.
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //Similar problem: https://leetcode.com/problems/subtree-of-another-tree/description/


    private boolean dfs(ListNode head, TreeNode root){

        //Base Case
        //If linkedlist is exhausted, we found ll in tree
        if(head == null){
            return true;
        }
        //If tree is exhausted or values are not equal, return false
        if(root == null || head.val != root.val){
            return false;
        }

        //curr values are matching, so we will check for next nodes in left or right sub-tree
        return (dfs(head.next,root.left) || dfs(head.next,root.right));
    }

    //Time: O(N * M)
    //Space: O(N){Height of Tree, Aux. Stack Space}
    public boolean isSubPath(ListNode head, TreeNode root) {

        
        //Base Case
        if(root == null){
            return false;
        }

        //First check if we can find linkedlist starting from root node
        if(dfs(head,root)){
            return true;
        }

        //We will check for left sub-tree or right sub-tree recursive
        return (isSubPath(head,root.left) || isSubPath(head,root.right));
        
    }
}
