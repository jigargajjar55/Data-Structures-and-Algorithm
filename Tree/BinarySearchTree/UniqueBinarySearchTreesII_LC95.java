import java.util.*;

public class UniqueBinarySearchTreesII_LC95 {

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
    
    //Time: O(Exponential), Space: O(N){Aux. Stack Space}
    private List<TreeNode> solveByDFS(int start, int end){

        List<TreeNode> list = new ArrayList<>();
        //Base Case
        if(start > end){  //Empty part
            list.add(null);
            return list;
        }
        if(start == end){
            list.add(new TreeNode(start));  //Only one node
            return list;
        }

        for(int i=start; i<=end; i++){

            List<TreeNode> leftSubTree = solveByDFS(start, i-1);
            List<TreeNode> rightSubTree = solveByDFS(i+1, end);

            for(TreeNode left : leftSubTree){
                for(TreeNode right : rightSubTree){

                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        } 
        return list;
    }

    public List<TreeNode> generateTrees(int n) {

        return solveByDFS(1,n);
        
    }
}
