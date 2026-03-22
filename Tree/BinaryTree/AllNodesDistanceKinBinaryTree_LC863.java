package Tree.BinaryTree;
import java.util.*;
 
public class AllNodesDistanceKinBinaryTree_LC863 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
   
    private void getAllParentMapping(TreeNode root, Map<TreeNode,TreeNode> nodeToParent){

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        nodeToParent.put(root, null);

        while(!q.isEmpty()){
            int size = q.size();

            for(int i=0; i<size; i++){
                TreeNode top = q.poll();

                if(top.left != null){
                    q.offer(top.left);
                    nodeToParent.put(top.left, top);
                }

                if(top.right != null){
                    q.offer(top.right);
                    nodeToParent.put(top.right, top);
                }
            }
        }

    }
    //Time: O(N + N), Space: O(N + N + Height Of Tree)
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        Map<TreeNode,TreeNode> nodeToParent = new HashMap<>();
        getAllParentMapping(root, nodeToParent);

        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(target);
        visited.add(target.val);

        int level = 0;
        

        while(!q.isEmpty()){

            int size = q.size();

            if(level == k){
                while(!q.isEmpty()){
                    result.add(q.peek().val);
                    q.poll();
                }
                break;
            }

            for(int i=0; i<size; i++){

                TreeNode top = q.peek();
                q.poll();

                if(top.left != null && (!visited.contains(top.left.val))){
                    q.offer(top.left);
                    visited.add(top.left.val);
                }

                if(top.right != null && (!visited.contains(top.right.val))){
                    q.offer(top.right);
                    visited.add(top.right.val);
                }

                TreeNode parent = nodeToParent.get(top);

                if(parent != null && (!visited.contains(parent.val))){
                    q.offer(parent);
                    visited.add(parent.val);
                }

            }
            level++;


        }

        return result;
    }

    //Time: O(N + N)
    //Space: O(Height of Tree)
    private int dfs(TreeNode root, TreeNode target, int k, int depth, List<Integer> result){

        //Base Case
        if(root == null){
            return 0;
        }

        if(depth == k){
            result.add(root.val);
            return 0;
        }

        int left = 0, right = 0;
        if(root.val == target.val || depth > 0){
            left = dfs(root.left,target,k, depth+1, result);
            right = dfs(root.right,target,k, depth+1,result);
        }else{
            left = dfs(root.left,target,k, depth, result);
            right = dfs(root.right,target,k, depth,result);
        }

        if(root.val == target.val){
            return 1;
        }

        if(left == k || right == k){
            result.add(root.val);
            return 0;
        }

        if(left > 0){
            dfs(root.right,target,k,left+1,result);
            return left+1;
        }

        if(right > 0){
            dfs(root.left,target,k,right+1,result);
            return right+1;
        }

        return 0;

    }

    public List<Integer> distanceK1(TreeNode root, TreeNode target, int k) {
        

        List<Integer> result = new ArrayList<>();

        if(k == 0){
            result.add(target.val);
        }else{
            dfs(root,target,k, 0, result);
        }
        
        return result;
    }






}