import java.util.*;

class TreeNode {
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

public class AllPossibleFullBinaryTrees_LC894 {
    


    //Time: O((2^n)/2), Space: O(n * (2^n)/2)
    private List<TreeNode> getAllFBT(int n,Map<Integer, List<TreeNode>> dp){

        //Base Case
        if(n == 0){
            return null;
        }

        //Overlapping sub-problem
        if(dp.containsKey(n)){
            return dp.get(n);
        }

        // create new list for all possible trees of size n.
        List<TreeNode> list = new ArrayList<>();
        // start from left is 1 and go until left is less then n
        int leftSize = 1;
        int rightSize = n - leftSize - 1;

        while(rightSize > 0){

            List<TreeNode> leftList = getAllFBT(leftSize,dp);
            List<TreeNode> rightList = getAllFBT(rightSize,dp);
            // create each possible combination with left and right sub trees.
            for(TreeNode left : leftList){
                for(TreeNode right : rightList){
                    TreeNode curr = new TreeNode(0);

                    curr.left = left;
                    curr.right = right;

                    list.add(curr);
                }
            }
            leftSize += 2;
            rightSize -= 2;
        }
        // add all combinations in cache
        dp.put(n,list);
        return list;
    }
    public List<TreeNode> allPossibleFBT(int n) {

        List<TreeNode> result = new ArrayList<>();

        // if n is even return empty list as we can not make full binary tree.
        if(n % 2 == 0){
            return result;
        }

        Map<Integer, List<TreeNode>> dp = new HashMap<>();

        // for n = 1 there will be only one tree with one node.
        result.add(new TreeNode(0));       
        dp.put(1, result);

        return getAllFBT(n,dp);
        
    }


}
