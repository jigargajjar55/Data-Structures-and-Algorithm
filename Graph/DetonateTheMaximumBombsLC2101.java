package Graph;

import java.util.*;

public class DetonateTheMaximumBombsLC2101 {

    // Approach: Reading problem statement , It will give you hint of using Directed
    // Graph as one bomb detonate other bomb if its in range.
    // How can we determine if one bomb can detonate to other? We can create
    // adjacency list for directed graph. O(N ^ 2)
    // We will consider edge from bomb A to bomb B if distance between this 2
    // cordinates less than or equal to radius of bomb A.

    // Distance = sqrt( ((xa - xb)^2) + ((ya-yb)^2) )
    // radius of A >= distance, That means bomb A can detonate bomb B

    // Once we create adj list, We will try to do BFS/DFS for each bomb and try to
    // maximize it.
    // Constraint allows us to do bruteforce approach

    private int dfs(int bomb, Map<Integer, List<Integer>> adj, boolean[] visited) {
        visited[bomb] = true;
        int count = 1;

        if (adj.containsKey(bomb)) {
            for (int nbrBomb : adj.get(bomb)) {
                if (!visited[nbrBomb]) {
                    count += dfs(nbrBomb, adj, visited);
                }
            }
        }

        return count;

    }

    public int maximumDetonation(int[][] bombs) {

        Map<Integer, List<Integer>> adj = new HashMap<>();
        int n = bombs.length;

        for (int i = 0; i < n; i++) {
            int srcX = bombs[i][0];
            int srcY = bombs[i][1];
            int srcR = bombs[i][2];

            adj.putIfAbsent(i, new ArrayList<>());

            for (int j = 0; j < n; j++) {

                if (i == j) {
                    continue;
                }

                int x = bombs[j][0];
                int y = bombs[j][1];

                boolean isTake = false;

                long distX = (srcX - x);
                long distY = (srcY - y);

                long squareX = distX * distX;
                long squareY = distY * distY;

                if (srcR >= Math.sqrt(squareX + squareY)) {
                    isTake = true;
                }

                if (isTake) {
                    adj.get(i).add(j);
                    System.out.println(i + " -> " + j);
                }

            }
        }

        int maxBomb = -(int) (1e9);

        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];

            int bombCount = dfs(i, adj, visited);
            maxBomb = Math.max(maxBomb, bombCount);
        }

        return maxBomb;

    }
}
