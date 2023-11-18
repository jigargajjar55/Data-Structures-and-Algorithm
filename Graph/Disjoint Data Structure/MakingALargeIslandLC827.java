import java.util.*;

class MakingALargeIslandLC827 {

    private class DisjointSet {
        int[] parent;
        public int[] size;

        DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int findParent(int node) {
            if (node == parent[node]) {
                return node;
            }

            parent[node] = findParent(parent[node]);

            return parent[node];
        }

        public void unionBySize(int u, int v) {
            int ulp_u = findParent(u);
            int ulp_v = findParent(v);

            if (ulp_u == ulp_v) {
                return;
            }

            if (size[ulp_u] < size[ulp_v]) {
                parent[ulp_u] = ulp_v;
                size[ulp_v] += size[ulp_u];
            } else {
                parent[ulp_v] = ulp_u;
                size[ulp_u] += size[ulp_v];
            }
        }
    }

    private boolean isSafe(int row, int col, int n) {
        if ((row >= 0 && row < n) && (col >= 0 && col < n)) {
            return true;
        } else {
            return false;
        }
    }

    public int largestIsland(int[][] grid) {

        int maxSize = 0;
        int n = grid.length;

        DisjointSet ds = new DisjointSet(n * n);
        int[] delRow = { -1, 0, 1, 0 };
        int[] delCol = { 0, 1, 0, -1 };

        // Step1
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (grid[row][col] == 0) {
                    continue;
                }

                int node = (n * row) + col;

                for (int ind = 0; ind < 4; ind++) {
                    int adjRow = row + delRow[ind];
                    int adjCol = col + delCol[ind];

                    if (isSafe(adjRow, adjCol, n) && grid[adjRow][adjCol] == 1) {
                        int adjNode = (n * adjRow) + adjCol;

                        ds.unionBySize(node, adjNode);
                    }
                }

            }
        }

        // Step2
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (grid[row][col] == 1) {
                    continue;
                }

                int node = (n * row) + col;
                Set<Integer> set = new HashSet<>();

                for (int ind = 0; ind < 4; ind++) {
                    int adjRow = row + delRow[ind];
                    int adjCol = col + delCol[ind];

                    if (isSafe(adjRow, adjCol, n)) {
                        if (grid[adjRow][adjCol] == 1) {
                            int adjNode = (n * adjRow) + adjCol;
                            int ulp_A = ds.findParent(adjNode);

                            set.add(ulp_A);
                        }
                    }
                }

                int temp = 1;
                for (int nd : set) {
                    temp += ds.size[nd];
                }
                maxSize = Math.max(maxSize, temp);
            }
        }

        // For edges case -- safe check
        for (int node = 0; node < (n * n); node++) {
            int ulp_A = ds.findParent(node);

            maxSize = Math.max(maxSize, ds.size[ulp_A]);
        }

        return maxSize;

    }
}