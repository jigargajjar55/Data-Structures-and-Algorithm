package Tree.BinaryTree;

public class SmallestSubtreewithalltheDeepestNodes_LC865 {

    class Pair{
        TreeNode node;
        int depth;
        Pair(TreeNode node, int depth){
            this.node = node;
            this.depth = depth;
        }
    }
    private Pair getLCADeepestNodes(TreeNode root){

        //Base Case
        if(root == null){
            return new Pair(root, 0);
        }
        if(root.left == null && root.right == null){
            return new Pair(root, 1);
        }

        Pair leftPart = getLCADeepestNodes(root.left);
        Pair rightPart = getLCADeepestNodes(root.right);

        if(leftPart.depth == rightPart.depth){
            return new Pair(root,leftPart.depth+1);
        }else if(leftPart.depth < rightPart.depth){
            return new Pair(rightPart.node, rightPart.depth + 1);
        }else{
            return new Pair(leftPart.node, leftPart.depth + 1);
        }

    }
    //Time: O(N)
    //Space: O(Height of Tree)
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Pair result = getLCADeepestNodes(root);
        return result.node;
    }
    
}
