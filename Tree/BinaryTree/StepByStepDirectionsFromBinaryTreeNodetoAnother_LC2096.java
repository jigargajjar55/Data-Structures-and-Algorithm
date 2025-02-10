import java.util.*;

public class TreeNode {
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

public class StepByStepDirectionsFromBinaryTreeNodetoAnother_LC2096 {



    //Approach 2 : Optimised approach
    //Get Lowest Comman Ancestor
    //Then we get path from root to startValue and destValue
    //For resultPath, directions from startValue will be always be "Up"
    //And directions from destValue will be "L" or "R"


    private TreeNode getLCA(TreeNode root, int startValue, int destValue){

        //Base Case
        if(root == null){
            return null;
        }
        if(root.val == startValue || root.val == destValue){
            return root;
        }


        TreeNode leftPart = getLCA(root.left,startValue, destValue);
        TreeNode rightPart = getLCA(root.right,startValue, destValue);

        if(leftPart != null && rightPart != null){
            return root;
        }

        if(leftPart != null && rightPart == null){
            return leftPart;
        }
        if(leftPart == null && rightPart != null){
            return rightPart;
        }
        return null;

    }
    private boolean getPath(TreeNode node,int value,StringBuilder path){

        //Base Case
        if(node == null){
            return false;
        }
        if(node.val == value){
            return true;
        }

        path.append('L');

        boolean leftPart = getPath(node.left,value, path); 

        if(leftPart){
            return true;
        }
        path.deleteCharAt(path.length() - 1);

        path.append('R');
        boolean rightPart = getPath(node.right,value, path);
        if(rightPart){
            return true;
        }
        path.deleteCharAt(path.length() - 1);

        return false;

    }
    //Time: O((3 * N) + (2 * Height of Tree))
    //Space: O(Height of Tree)
    public String getDirections2(TreeNode root, int startValue, int destValue) {

        TreeNode lowestCommanAncestor = getLCA(root,startValue, destValue);

        StringBuilder pathForStartValue = new StringBuilder();
        StringBuilder pathForDestValue = new StringBuilder();

        getPath(lowestCommanAncestor,startValue, pathForStartValue);
        getPath(lowestCommanAncestor,destValue, pathForDestValue);

        StringBuilder resultPath = new StringBuilder();
        int size = pathForStartValue.length();
        int index = 0;

        while(index < size){
            resultPath.append('U');
            index++;
        }

        size = pathForDestValue.length();
        index = 0;

        while(index < size){
            char ch = pathForDestValue.charAt(index);
            resultPath.append(ch);
            index++;
        }

        return resultPath.toString();



        
        
    }






    //Approach 1: Bruteforce approach --- Not so Space optimised
    class Pair {
        TreeNode node;
        StringBuilder path;

        Pair(TreeNode node, StringBuilder path) {
            this.node = node;
            this.path = path;
        }
    }

    private TreeNode getSrcNode(TreeNode root, Map<TreeNode, TreeNode> nodeToParent, int target) {

        TreeNode targetNode = new TreeNode(-1);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        nodeToParent.put(root, null);

        while (!q.isEmpty()) {

            TreeNode top = q.poll();
            if (top.val == target) {
                targetNode = top;
            }

            if (top.left != null) {
                q.offer(top.left);
                nodeToParent.put(top.left, top);
            }
            if (top.right != null) {
                q.offer(top.right);
                nodeToParent.put(top.right, top);
            }

        }

        return targetNode;

    }


    //Time: O(N + N)
    //Space: O(N * Height of Tree)
    public String getDirections1(TreeNode root, int startValue, int destValue) {

        Map<TreeNode, TreeNode> nodeToParent = new HashMap<>();
        TreeNode srcNode = getSrcNode(root, nodeToParent, startValue);

        Set<TreeNode> visited = new HashSet<>();
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(srcNode, new StringBuilder()));

        while (!q.isEmpty()) {

            Pair currPair = q.poll();

            if (currPair.node.val == destValue) {
                return currPair.path.toString();
            }

            visited.add(currPair.node);

            if (currPair.node.left != null && !visited.contains(currPair.node.left)) {

                StringBuilder str = new StringBuilder(currPair.path);
                str.append('L');

                q.offer(new Pair(currPair.node.left, str));
            }

            if (currPair.node.right != null && !visited.contains(currPair.node.right)) {

                StringBuilder str = new StringBuilder(currPair.path);
                str.append('R');

                q.offer(new Pair(currPair.node.right, str));
            }

            TreeNode parent = nodeToParent.get(currPair.node);

            if (parent != null && !visited.contains(parent)) {
                StringBuilder str = new StringBuilder(currPair.path);
                str.append('U');
                q.offer(new Pair(parent, str));
            }

        }

        return "-1";

    }
}