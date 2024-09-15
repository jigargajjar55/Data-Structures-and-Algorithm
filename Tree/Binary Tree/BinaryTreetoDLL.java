package Tree.Binary Tree;

public class BinaryTreetoDLL {


    class Pair{
        Node startNode;
        Node endNode;
        Pair(Node startNode, Node endNode){
            this.startNode = startNode;
            this.endNode = endNode;
        }
    }
    
    Pair dfs(Node root){
        
        //Base Case
        if(root == null || (root.left == null && root.right == null)){
            return new Pair(root,root);
        }
        
        
        Pair leftPart = dfs(root.left);
        Pair rightPart = dfs(root.right);
        
        root.left = null;
        root.right = null;
        
        if(leftPart.endNode != null){
            
            leftPart.endNode.right = root;
            root.left = leftPart.endNode;
            
        }
        if(rightPart.startNode != null){
            root.right = rightPart.startNode;
            rightPart.startNode.left = root;
        }
        
        Pair currPart = new Pair(root,root);
        
        if(leftPart.startNode != null){
            currPart.startNode = leftPart.startNode;
        }
        if(rightPart.endNode != null){
            currPart.endNode = rightPart.endNode;
        }
        
        
        return currPart;
        
    }
    
    //Time: O(N)
    //Space: O(Height of Tree)
    Node bToDLL(Node root)
    {
        
        Pair node = dfs(root);
        
        Node ans = node.startNode;
        
        return ans;

    }


    void inOrder(Node root,List<Node> inorderList){
        
        //Base Case
        if(root == null){
            return;
        }
        
        
        inOrder(root.left,inorderList);
        
        inorderList.add(root);
        
        inOrder(root.right,inorderList);
        
    }
    //Function to convert binary tree to doubly linked list and return it.
    //Time: O(N + N)
    //Space: O(Height of Tree + N)
    Node bToDLL1(Node root)
    {
        
        List<Node> inorderList = new ArrayList<>();
        
        inOrder(root,inorderList);
        
        int size = inorderList.size();
        Node head = inorderList.get(0);
        Node curr = head;
        curr.left = null;
        curr.right = null;
        
        for(int i=1; i<size; i++){
            
            Node temp = inorderList.get(i);
            
            curr.right = temp;
            temp.left = curr;
            
            curr = temp;
            
        }
        
        return head;

    }
    
}
