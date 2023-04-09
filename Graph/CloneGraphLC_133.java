package Graph;

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class CloneGraphLC_133 {

    // Time : O(N + E), Space : O(3 * N)
    public Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }

        Map<Integer, Node> visited = new HashMap<>();
        Map<Integer, Set<Integer>> cloneNbr = new HashMap<>();
        Queue<Node> q = new LinkedList<>();

        Node root = new Node(node.val);
        visited.put(root.val, root);
        cloneNbr.put(root.val, new HashSet<>());

        q.offer(node);

        while (!q.isEmpty()) {

            Node front = q.peek();
            q.poll();

            Node cloneNode = visited.get(front.val);

            if (front.neighbors.size() > 0) {

                for (Node nbr : front.neighbors) {

                    if (visited.containsKey(nbr.val)) {

                        if (!cloneNbr.get(cloneNode.val).contains(nbr.val)) {
                            System.out.println(nbr.val);

                            Node temp = visited.get(nbr.val);

                            cloneNode.neighbors.add(temp);
                            temp.neighbors.add(cloneNode);

                            cloneNbr.get(cloneNode.val).add(nbr.val);
                            cloneNbr.get(nbr.val).add(cloneNode.val);

                        }

                    } else {

                        Node temp = new Node(nbr.val);
                        cloneNode.neighbors.add(temp);
                        temp.neighbors.add(cloneNode);

                        cloneNbr.put(temp.val, new HashSet<>());
                        cloneNbr.get(temp.val).add(cloneNode.val);
                        cloneNbr.get(cloneNode.val).add(temp.val);

                        visited.put(temp.val, temp);

                        q.offer(nbr);

                    }
                }

            }

        }

        return root;

    }
}
