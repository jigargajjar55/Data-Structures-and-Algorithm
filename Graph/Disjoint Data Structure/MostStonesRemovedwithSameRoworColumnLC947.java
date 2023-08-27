import java.util.*;

class DisJointDS {
    List<Integer> rank;
    List<Integer> size;
    List<Integer> parent;

    DisJointDS(int noOfNodes) {
        rank = new ArrayList<>();
        size = new ArrayList<>();
        parent = new ArrayList<>();
        for (int i = 0; i <= noOfNodes; i++) {
            rank.add(0);
            size.add(1);
            parent.add(i);
        }
    }

    // Time : O(4a)
    public int findParent(int node) {

        if (node == parent.get(node)) {
            return node;
        }

        parent.set(node, findParent(parent.get(node)));

        return parent.get(node);
    }

    // Time : O(4a)
    public void unionByRank(int u, int v) {

        int ulp_u = findParent(u);
        int ulp_v = findParent(v);

        // If both node's ultimate parent is same, nothing to do
        if (ulp_u == ulp_v) {
            return;
        }
        if (rank.get(ulp_v) < rank.get(ulp_u)) {
            parent.set(ulp_v, ulp_u);
        } else if (rank.get(ulp_v) > rank.get(ulp_u)) {
            parent.set(ulp_u, ulp_v);
        } else {
            parent.set(ulp_v, ulp_u);
            int updatedRank = rank.get(ulp_u);
            rank.set(ulp_u, updatedRank + 1);
        }
    }

    // Time : O(4a)
    public void unionBySize(int u, int v) {

        int ulp_u = findParent(u);
        int ulp_v = findParent(v);

        // If both node's ultimate parent is same, nothing to do
        if (ulp_u == ulp_v) {
            return;
        }

        if (size.get(ulp_v) < size.get(ulp_u)) {
            parent.set(ulp_v, ulp_u);
            int updatedSize = size.get(ulp_v) + size.get(ulp_u);
            size.set(ulp_u, updatedSize);
        } else {

            parent.set(ulp_u, ulp_v);
            int updatedSize = size.get(ulp_v) + size.get(ulp_u);
            size.set(ulp_v, updatedSize);

        }

    }

}

class Solution {
    public int removeStones(int[][] stones) {
        int maxRow = 0;
        int maxCol = 0;
        int n = stones.length;
        for (int i = 0; i < n; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }

        DisJointDS ds = new DisJointDS(maxRow + maxCol + 1);
        Set<Integer> set = new HashSet<>();

        for (int[] stone : stones) {
            int nodeRow = stone[0];
            int nodeCol = maxRow + stone[1] + 1;

            ds.unionBySize(nodeRow, nodeCol);

            set.add(nodeRow);
            set.add(nodeCol);
        }

        int compCount = 0;
        for (int node : set) {
            if (ds.findParent(node) == node) {
                compCount++;
            }
        }

        return (n - compCount);

    }
}
