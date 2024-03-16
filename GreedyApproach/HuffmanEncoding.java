package GreedyApproach;
import java.util.*;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class HuffmanEncoding {

    private void traverse(Node root, String ans, ArrayList<String> result) {
        // Base Case
        if (root.left == null && root.right == null) {
            result.add(ans);
            return;
        }

        traverse(root.left, ans + 0, result);
        traverse(root.right, ans + 1, result);
    }

    // Time : O(N * (log(N)) + N), Space: O(N)
    public ArrayList<String> huffmanCodes(String S, int f[], int N) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.data, n2.data));

        for (int i = 0; i < N; i++) {
            Node newNode = new Node(f[i]);
            pq.offer(newNode);
        }

        while (pq.size() > 1) {
            Node left = pq.peek();
            pq.poll();
            Node right = pq.peek();
            pq.poll();

            Node parent = new Node(left.data + right.data);
            parent.left = left;
            parent.right = right;

            pq.offer(parent);
        }

        Node root = pq.peek();
        ArrayList<String> result = new ArrayList<String>();
        String ans = "";

        traverse(root, ans, result);

        return result;

    }
}
