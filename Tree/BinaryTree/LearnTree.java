import java.util.*;

class Node {
    int data;
    Node left;
    Node right;

    Node(int d) {
        this.data = d;
        this.left = null;
        this.right = null;
    }
}

public class LearnTree {

    public static Node buildTree(Node root) {

        System.out.println("Enter the data: ");
        Scanner sc = new Scanner(System.in);
        int data = sc.nextInt();

        if (data == -1) {
            return null;
        }

        root = new Node(data);

        System.out.println("Enter data for inserting in left of " + data);
        root.left = buildTree(root.left);
        System.out.println("Enter data for inserting in right of " + data);
        root.right = buildTree(root.right);

        return root;

    }

    public static Node buildTreeFromLevelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();

        System.out.println("Enter the data for root: ");
        Scanner sc = new Scanner(System.in);
        int data = sc.nextInt();
        root = new Node(data);

        q.offer(root);

        while (!q.isEmpty()) {
            Node temp = q.peek();
            q.poll();

            System.out.println("Enter left node for: " + temp.data);
            int leftData = sc.nextInt();

            if (leftData != -1) {
                temp.left = new Node(leftData);
                q.offer(temp.left);
            }

            System.out.println("Enter right node for: " + temp.data);
            int rightData = sc.nextInt();

            if (rightData != -1) {
                temp.right = new Node(rightData);
                q.offer(temp.right);
            }
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

    public static void levelOrderTraversal(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node front = q.poll();

                System.out.print(front.data + "  ");

                if (front.left != null) {
                    q.offer(front.left);
                }
                if (front.right != null) {
                    q.offer(front.right);
                }
            }
            System.out.println();
        }

    }

    public static void reverseLevelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        Stack<Node> st = new Stack<>();

        q.offer(root);
        q.offer(null);
        Node temp;
        while (!q.isEmpty()) {
            temp = q.peek();
            q.poll();

            if (temp == null) {
                st.push(null);
                if (!q.isEmpty()) {
                    q.offer(null);
                }
            } else {
                st.push(temp);

                if (temp.right != null) {
                    q.offer(temp.right);
                }

                if (temp.left != null) {
                    q.offer(temp.left);
                }
            }
        }

        while (!st.isEmpty()) {
            temp = st.peek();
            if (temp == null) {
                System.out.println();
            } else {
                System.out.print(temp.data + "  ");
            }

            st.pop();
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

    public static void inorderIterative(Node root) {
        Stack<Node> st = new Stack<>();

        Node curr = root;

        while (curr != null || st.size() > 0) {

            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }

            curr = st.peek();
            st.pop();
            System.out.print(curr.data + "  ");
            curr = curr.right;

        }
    }

    public static void preorderIterative(Node root) {
        Stack<Node> st = new Stack<>();
        Node curr;
        st.push(root);

        while (!st.isEmpty()) {
            curr = st.peek();
            System.out.print(curr.data + "  ");
            st.pop();

            if (curr.right != null) {
                st.push(curr.right);
            }
            if (curr.left != null) {
                st.push(curr.left);
            }

        }

    }

    public static void postorderIterative(Node root) {
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();

        st1.push(root);

        while (!st1.isEmpty()) {

            Node temp = st1.peek();

            st2.push(temp);
            st1.pop();

            if (temp.left != null) {
                st1.push(temp.left);
            }

            if (temp.right != null) {
                st1.push(temp.right);
            }

        }

        while (!st2.isEmpty()) {
            System.out.print(st2.peek().data + "  ");
            st2.pop();
        }

    }

    public static void morrisTraversal(Node root) {
        // base case
        if (root == null) {
            return;
        }

        Node current = root;

        while (current != null) {

            if (current.left == null) {
                System.out.print(current.data + "  ");
                current = current.right;
            } else {
                Node predecessor = findPre(current);
                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    System.out.print(current.data + "  ");
                    current = current.right;
                }
            }
        }
    }

    public static Node findPre(Node root) {

        Node temp = root.left;

        while (temp.right != null && temp.right != root) {
            temp = temp.right;
        }
        return temp;
    }

    public static void main(String[] args) {
        Node root = null;

        root = buildTreeFromLevelOrder(root);
        // levelOrder(root);

        // System.out.println();
        // System.out.print("In-Order Traversal: ");
        // inorder(root);
        //
        // System.out.println();
        // System.out.print("In-Order Traversal using Morris Traversal: ");
        // morrisTraversal(root);

        // root = buildTree(root);
        // 1 3 7 -1 -1 11 -1 -1 5 17 -1 -1 -1
        System.out.println("Level-Order Traversal:");
        // levelOrder(root);
        levelOrderTraversal(root);
        /*
         * System.out.println("Reverse Level-Order Traversal:");
         * reverseLevelOrder(root);
         * 
         * System.out.println();
         * System.out.print("In-Order Traversal:  ");
         * //inorder(root);
         * inorderIterative(root);
         * 
         * System.out.println();
         * System.out.print("Pre-Order Traversal:  ");
         * //preorder(root);
         * preorderIterative(root);
         * 
         * System.out.println();
         * System.out.print("Post-Order Traversal:  ");
         * //postorder(root);
         * postorderIterative(root);
         */

    }

}
