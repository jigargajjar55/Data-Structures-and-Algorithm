import java.util.*;

public class FindLargestValueInEachTreeRow_LC515 {
     // Definition for a binary tree node.
  private class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
        this.left = left;
         this.right = right;
      }
  }

    //BFS traversal in Binary Tree
    //Time: O(N){Number of nodes}
    //Space: O(N){For Queue}
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int size = q.size();
            int maxi = Integer.MIN_VALUE;

            for(int i=0; i<size; i++){
                TreeNode top = q.peek();
                q.poll();

                int topValue = top.val;
                maxi = Math.max(maxi, topValue);

                if(top.left != null){
                    q.offer(top.left);
                }
                if(top.right != null){
                    q.offer(top.right);
                }
            }
            result.add(maxi);
        }

        return result;
        
    }
}