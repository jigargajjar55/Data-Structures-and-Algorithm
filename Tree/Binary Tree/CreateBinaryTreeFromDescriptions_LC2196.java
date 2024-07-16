import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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

public class CreateBinaryTreeFromDescriptions_LC2196 {

    // Approach 2
    // Time: O(2 * D)
    // Space: O(No. of Nodes in Tree)
    public TreeNode createBinaryTree2(int[][] descriptions) {

        // Initialize root node with dummy value
        TreeNode root = new TreeNode(-1);

        int d = descriptions.length;
        Map<Integer, TreeNode> map = new HashMap<>();

        // Create node of all child and maping with its value since its unique
        for (int i = 0; i < d; i++) {
            int child = descriptions[i][1];

            if (!map.containsKey(child)) {
                map.put(child, new TreeNode(child));
            }

        }

        // We will loop through again and check if any parent is not created or not
        // The one parent which is not created, that's our root(Description is valid)
        // We will make relationship between parent and child based on its directions
        for (int i = 0; i < d; i++) {
            int parent = descriptions[i][0];
            int child = descriptions[i][1];
            int isLeft = descriptions[i][2];

            if (!map.containsKey(parent)) {
                root = new TreeNode(parent);
                map.put(parent, root);
            }

            TreeNode parentNode = map.get(parent);
            TreeNode childNode = map.get(child);

            if (isLeft == 1) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
        }

        return root;

    }

    // Approach 1
    // Create map for Parent to Child
    // Create map for node to its Parent, to verify the root node of Tree
    // Time: O(D + (2 * N)){No of Nodes in Tree}
    // Space: O(3 * N)
    public TreeNode createBinaryTree1(int[][] descriptions) {

        int d = descriptions.length;
        Map<Integer, List<int[]>> parentToChildMap = new HashMap<>();
        Map<Integer, Integer> nodeToParent = new HashMap<>();

        for (int i = 0; i < d; i++) {
            int parent = descriptions[i][0];
            int child = descriptions[i][1];
            int isLeft = descriptions[i][2];

            nodeToParent.put(child, parent);

            if (!parentToChildMap.containsKey(parent)) {
                parentToChildMap.put(parent, new ArrayList<>());
            }

            parentToChildMap.get(parent).add(new int[] { child, isLeft });

        }

        // Since descriptions is valid, It has to be one root of Binary tree
        int rootValue = -1;
        for (int parentValue : parentToChildMap.keySet()) {

            if (!nodeToParent.containsKey(parentValue)) {
                rootValue = parentValue;
            }
        }
        TreeNode root = new TreeNode(rootValue);

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {

            TreeNode curr = q.poll();

            if (parentToChildMap.containsKey(curr.val)) {

                for (int[] nbr : parentToChildMap.get(curr.val)) {

                    TreeNode child = new TreeNode(nbr[0]);

                    if (nbr[1] == 1) {
                        curr.left = child;
                    } else {
                        curr.right = child;
                    }
                    q.offer(child);
                }

            }

        }

        return root;

    }
}