package LinkedList;
import java.util.*;



public class LRUCache_LC146 {

    private class ListNode{
        int key;
        int value;
        ListNode next;
        ListNode prev;
        ListNode(int key, int value){
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }    
    }

    
    ListNode head;
    ListNode tail;
    Map<Integer, ListNode> keyToNode;
    int capacity;

    public LRUCache_LC146(int capacity) {
        head = new ListNode(-1,-1);
        tail = new ListNode(-1,-1);        
        keyToNode = new HashMap<>();
        this.capacity = capacity;

        head.next = tail;
        tail.prev = head;        
    }
    //Avg. Time: O(1)
    private void addNodeToHead(ListNode node){
        node.next = head.next;
        node.prev = head.next.prev;

        head.next.prev = node;
        head.next = node;
    }

    //Avg. Time: O(1)
    private void deleteNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    //Avg. Time: O(1)
    public int get(int key) {

        int value = -1;

        if(keyToNode.containsKey(key)){
            ListNode node = keyToNode.get(key);
            value = node.value;
            deleteNode(node);

            addNodeToHead(node);            
        }

        return value;
        
    }
    
    //Avg. Time: O(1)
    public void put(int key, int value) {

        if(keyToNode.containsKey(key)){
            ListNode node = keyToNode.get(key);
            //Update the value of node
            node.value = value;

            deleteNode(node);
            addNodeToHead(node); 

        }else{

            ListNode newNode = new ListNode(key,value);            

            if(keyToNode.size() == capacity){

                int lruNodeKey = tail.prev.key;

                ListNode lruNode = keyToNode.get(lruNodeKey);
                deleteNode(lruNode);

                keyToNode.remove(lruNodeKey);
            }

            addNodeToHead(newNode);
            keyToNode.put(key,newNode);
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */