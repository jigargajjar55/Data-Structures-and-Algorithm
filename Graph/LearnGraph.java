
import java.util.*;

class graph<T> {

    HashMap<T, List<T>> adj = new HashMap<>();

    void addEdge(T u, T v, boolean direction) {
        // Direction = false -> Undirected Graph
        // Direction = true -> Directed Graph

        // create an edge from u to v
        if (!adj.containsKey(u)) {
            adj.put(u, new ArrayList<>());
        }
        adj.get(u).add(v);

        if (direction == false) {

            if (!adj.containsKey(v)) {
                adj.put(v, new ArrayList<>());
            }
            adj.get(v).add(u);
        }

    }

    // Time: O(nodes * edges), Space: O(nodes * edges)
    void printAdjList() {

        for (T key : adj.keySet()) {

            System.out.print(key + "-->");

            for (T n : adj.get(key)) {
                System.out.print(n + ", ");
            }

            System.out.println();

        }

    }

}

public class LearnGraph {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of nodes: ");
        int nodes = sc.nextInt();

        System.out.println("Enter the number of edges: ");
        int edges = sc.nextInt();

        graph<Integer> g = new graph();

        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            // creating an undirected graph
            g.addEdge(u, v, false);

        }

        // printing an undirected graph
        g.printAdjList();

    }

}
