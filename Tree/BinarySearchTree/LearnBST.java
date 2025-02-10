import java.util.*;

public class LearnBST {

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int d) {
            this.data = d;
            this.left = null;
            this.right = null;
        }
    }

    public static Node insertIntoBST(Node root, int data) {

        // base case
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data > root.data) {
            root.right = insertIntoBST(root.right, data);
        } else {
            root.left = insertIntoBST(root.left, data);
        }
        return root;
    }

    public static Node takeInput(Node root) {
        Scanner sc = new Scanner(System.in);
        int data = sc.nextInt();

        while (data != -1) {
            root = insertIntoBST(root, data);
            data = sc.nextInt();
        }

        return root;
    }

    public static void levelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();

        q.offer(root);
        q.offer(null);

        while (!q.isEmpty()) {

            Node node = q.peek();
            q.poll();

            if (node == null) {
                System.out.println();
                if (!q.isEmpty()) {
                    q.offer(null);
                }

            } else {
                System.out.print(node.data + "  ");

                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }

        }
    }

    public static void inorder(Node root) {
        // Base condition
        if (root == null) {
            return;
        }

        inorder(root.left);
        System.out.print(root.data + "  ");
        inorder(root.right);
    }

    public static void preorder(Node root) {
        // Base condition
        if (root == null) {
            return;
        }

        System.out.print(root.data + "  ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void postorder(Node root) {
        // Base condition
        if (root == null) {
            return;
        }

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + "  ");
    }

    public static Node deleteFromBST(Node root, int val) {

        // Base case
        if (root == null) {
            return root;
        }

        if (root.data == val) {

            // 0 child
            if (root.left == null && root.right == null) {
                return null;
            }

            // 1 child

            // left child
            if (root.left != null && root.right == null) {
                return root.left;

            }

            // right child
            if (root.left == null && root.right != null) {
                return root.right;

            }

            // 2 child
            int mini = minValue(root.right);
            root.data = mini;
            root.right = deleteFromBST(root.right, mini);
            return root;

        } else if (root.data > val) {
            root.left = deleteFromBST(root.left, val);
            return root;
        } else {
            root.right = deleteFromBST(root.right, val);
            return root;
        }

    }

    public static int minValue(Node root) {
        Node temp = root;

        while (temp.left != null) {
            temp = temp.left;
        }
        return temp.data;
    }

    public static int maxValue(Node root) {
        Node temp = root;

        while (temp.right != null) {
            temp = temp.right;
        }
        return temp.data;
    }

    public static void main(String[] args) {
        Node root = null;

        System.out.println("Enter data to create BST:");
        root = takeInput(root);

        System.out.println("Printing the BST:");
        levelOrder(root);

        root = deleteFromBST(root, 50);

        System.out.println("Printing the BST:");
        levelOrder(root);

        System.out.println();
        System.out.print("In-Order Traversal:  ");
        inorder(root);

        /*
         * System.out.println();
         * System.out.println("Min value in BST: "+ minValue(root));
         * 
         * 
         * 
         * System.out.println();
         * System.out.println("Max value in BST: " + maxValue(root));
         * 
         * 
         * 
         * System.out.println();
         * System.out.print("In-Order Traversal:  ");
         * inorder(root);
         * 
         * System.out.println();
         * System.out.print("Pre-Order Traversal:  ");
         * preorder(root);
         * 
         * 
         * System.out.println();
         * System.out.print("Post-Order Traversal:  ");
         * postorder(root);
         */

    }

}
