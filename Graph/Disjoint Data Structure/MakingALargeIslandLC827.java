import java.util.*;

class MakingALargeIslandLC827 {

    private class DisJointSet {
        int[] parent;
        int[] size;
        int noOfNodes;

        DisJointSet(int noOfNodes) {
            this.noOfNodes = noOfNodes;
            this.parent = new int[noOfNodes];
            this.size = new int[noOfNodes];

            for (int i = 0; i < noOfNodes; i++) {
                this.parent[i] = i;
                this.size[i] = 1;
            }
        }

        public int findParent(int node) {

            // Base Case
            if (node == parent[node]) {
                return node;
            }

            parent[node] = findParent(parent[node]);

            return parent[node];
        }

        public void unionBysize(int u, int v) {
            int ulp_u = findParent(u);
            int ulp_v = findParent(v);

            if (ulp_u == ulp_v) {
                return;
            }

            if (size[ulp_u] > size[ulp_v]) {
                size[ulp_u] += size[ulp_v];
                parent[ulp_v] = ulp_u;
            } else {
                size[ulp_v] += size[ulp_u];
                parent[ulp_u] = ulp_v;
            }
        }

        public int getSize(int node) {
            return size[node];
        }

    }

    private boolean isSafe(int row, int col, int m, int n, int[][] grid) {

        if ((row >= 0 && row < m) && (col >= 0 && col < n) && (grid[row][col] == 1)) {
            return true;
        }
        return false;

    }

    // Time: O((M * N * 4 * (4a)))
    // Space: O(M * N)
    public int largestIsland(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        DisJointSet ds = new DisJointSet(m * n);

        int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        // Step1: connect all island which exist
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {

                if (grid[row][col] == 0) {
                    continue;
                }

                int node = (n * row) + col;

                for (int[] dir : dirs) {
                    int nRow = row + dir[0];
                    int nCol = col + dir[1];

                    if (isSafe(nRow, nCol, m, n, grid)) {
                        int adjNode = (n * nRow) + nCol;

                        ds.unionBysize(node, adjNode);
                    }
                }

            }
        }

        int result = 0;

        // Step2: Change 0 to 1 for each 0 cell and check for largest island
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {

                int node = (n * row) + col;

                // For edges case -- safe check
                if (grid[row][col] == 1) {

                    int ulp_Node = ds.findParent(node);

                    int currSize = ds.getSize(ulp_Node);

                    if (result < currSize) {
                        result = currSize;
                    }

                    continue;
                }

                Set<Integer> set = new HashSet<>();

                for (int[] dir : dirs) {
                    int nRow = row + dir[0];
                    int nCol = col + dir[1];

                    if (isSafe(nRow, nCol, m, n, grid)) {
                        int adjNode = (n * nRow) + nCol;

                        int ulp_adjNode = ds.findParent(adjNode);
                        set.add(ulp_adjNode);
                    }
                }

                int currSize = 1;
                for (int currNode : set) {
                    currSize += ds.getSize(currNode);
                }

                if (result < currSize) {
                    result = currSize;
                }

            }
        }

        return result;

    }

}