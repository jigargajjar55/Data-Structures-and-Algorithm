import java.util.*;

class DisJointDS {
    List<Integer> rank;
    List<Integer> size;
    public List<Integer> parent;
    public int extraEdges;

    DisJointDS(int noOfNodes) {
        extraEdges = 0;
        rank = new ArrayList<>();
        size = new ArrayList<>();
        parent = new ArrayList<>();
        for (int i = 0; i < noOfNodes; i++) {
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
            extraEdges++;
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
            extraEdges++;
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

class NumberOfOperationstoMakeNetworkConnectedLC1319 {
    public int makeConnected(int n, int[][] connections) {

        DisJointDS ds = new DisJointDS(n);
        for (int i = 0; i < connections.length; i++) {
            int u = connections[i][0];
            int v = connections[i][1];
            ds.unionBySize(u, v);
        }

        int connectedComp = 0;

        for (int i = 0; i < n; i++) {
            if (ds.parent.get(i) == i) {
                connectedComp++;
            }
        }
        int ans = connectedComp - 1;

        if (ans <= ds.extraEdges) {
            return ans;
        } else {
            return -1;
        }

    }
}