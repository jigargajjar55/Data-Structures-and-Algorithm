import java.util.*;

public class ValidateBinaryTreeNodes_LC1361 {
    
    // https://leetcode.com/problems/validate-binary-tree-nodes/solutions/4178076/easy-detailed-explanation-bfs/?envType=daily-question&envId=2023-10-17
    

    //Time: O(2 * N)
    //Space: O(2 * N){Size of Set and Queue}


    private int getRootForBinaryTree(int n,int[] leftChild,int[] rightChild){

        //We are taking set, add all nodes in set
        Set<Integer> nodes = new HashSet<>();
        for(int i=0; i<n; i++){
            nodes.add(i);
        }

        //Now we are removing nodes from left and right array,
        //If we have size of set is 1, it means it has one and only one root in binary tree.
        //If it has more than 1 root or no root, it is not valid binary tree.
        for(int i=0; i<n; i++){

            if(nodes.contains(leftChild[i])){
                nodes.remove(leftChild[i]);
            }

            if(nodes.contains(rightChild[i])){
                nodes.remove(rightChild[i]);
            }
        }

        if(nodes.size() == 1){
            return nodes.iterator().next();
        }else{
            return -1;
        }

    }
   
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {

        //First Step: Find root of binary tree
        int root = getRootForBinaryTree(n,leftChild,rightChild);

        //Once we get a root, we will check if root was found or not.
        if(root == -1){
            return false;
        }

        //We will do BFS
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> q = new ArrayDeque<>();

        q.offer(root);
       

        while(!q.isEmpty()){

            int top = q.peek();
            q.poll();

            if(!visited.add(top)){
                return false;
            }

            int left = leftChild[top];
            int right = rightChild[top];


            if(left != -1){
                q.offer(left);
            }

            if(right != -1){ 
                q.offer(right);               
            }
        }

        //If set size is equal to N{Cover all nodes in binary tree while doing BFS}, it's valid binary tree.
        //Otherwise return false.
        return visited.size() == n;
        
    }
}
