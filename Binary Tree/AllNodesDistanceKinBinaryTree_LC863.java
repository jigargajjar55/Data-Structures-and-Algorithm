import java.util.*;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
 
public class AllNodesDistanceKinBinaryTree_LC863 {
    
   
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
}