
package Tree.BinaryTree;
import java.util.*;

import javax.swing.tree.TreeNode;

public class DeleteNodesAndReturnForest_LC1110 {

    //Approach 2:
     private TreeNode postOrderDelete(TreeNode root,Set<Integer> nodeToBeDeleted,List<TreeNode> result){

        //Base Case
        if(root == null){
            return null;
        }

        //L
        root.left = postOrderDelete(root.left,nodeToBeDeleted,result);
        //R
        root.right = postOrderDelete(root.right,nodeToBeDeleted,result);

        if(nodeToBeDeleted.contains(root.val)){

            if(root.left != null){
                result.add(root.left);
            }
            if(root.right != null){
                result.add(root.right);
            }
            return null;
        }

        return root;


    }
    //Time: O(No. Of Nodes + L)
    //Space: O(Height of Tree + L){L: No. of Node to be deleted}
    public List<TreeNode> delNodes2(TreeNode root, int[] to_delete) {

        List<TreeNode> result = new ArrayList<>();
        Set<Integer> nodeToBeDeleted = new HashSet<>();
        int n = to_delete.length;

        for(int i=0; i<n; i++){
            nodeToBeDeleted.add(to_delete[i]);
        }

        postOrderDelete(root,nodeToBeDeleted,result);

        if(!nodeToBeDeleted.contains(root.val)){
            result.add(root);
        }

        return result;
        
    }
    

    //Approach 1:
    class Tuple{
        TreeNode node;
        TreeNode parent;
        boolean isLeft;
        Tuple(TreeNode node,TreeNode parent, boolean isLeft){
            this.node = node;
            this.parent = parent;
            this.isLeft = isLeft;
        }
    }
    private void getMappingBWnodetoParent(TreeNode root,Map<Integer, Tuple> nodeToParent){

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        nodeToParent.put(root.val, new Tuple(root,null,false));

        while(!q.isEmpty()){
            TreeNode top = q.poll();

            if(top.left != null){
                nodeToParent.put(top.left.val, new Tuple(top.left,top, true));
                q.offer(top.left);
            }
            if(top.right != null){
                nodeToParent.put(top.right.val, new Tuple(top.right,top, false));
                q.offer(top.right);
            }
        }

    }
    //Time: O(N + L)
    //Space: O(N)
    public List<TreeNode> delNodes1(TreeNode root, int[] to_delete) {

        Map<Integer, Tuple> nodeToParent = new HashMap<>();
        getMappingBWnodetoParent(root,nodeToParent);

        int n = to_delete.length;

        for(int i=0; i<n; i++){
            int nodeToBeDeleted = to_delete[i];
            if(nodeToParent.containsKey(nodeToBeDeleted)){
                Tuple nodeTuple = nodeToParent.get(nodeToBeDeleted);

                //First will process its child if exist
                TreeNode node = nodeTuple.node;
                TreeNode parent = nodeTuple.parent;
                boolean isLeft = nodeTuple.isLeft;

                if(node.left != null){
                    if(nodeToParent.containsKey(node.left.val)){
                        Tuple leftNodeTuple = nodeToParent.get(node.left.val);
                        leftNodeTuple.parent = null;
                        node.left = null;
                    }
                }
                if(node.right != null){
                    if(nodeToParent.containsKey(node.right.val)){
                        Tuple rightNodeTuple = nodeToParent.get(node.right.val);
                        rightNodeTuple.parent = null;
                        node.right = null;
                    }
                }

                if(parent != null){

                    if(isLeft){
                        parent.left = null;
                    }else{
                        parent.right = null;
                    }
                }

                nodeToParent.remove(nodeToBeDeleted);
            }
        }

        List<TreeNode> result = new ArrayList<>();

        for(int nodeValue : nodeToParent.keySet()){
            Tuple currTuple = nodeToParent.get(nodeValue);
            if(currTuple.parent == null){
                result.add(currTuple.node);
            }
        }

        return result;
        
    }
}
