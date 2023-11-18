import java.util.*;

class AccountsMergeLC721 {

    private class DisJointDS {
        List<Integer> rank;
        List<Integer> size;
        List<Integer> parent;

        DisJointDS(int noOfNodes) {
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

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> mailToNode = new HashMap<>();
        int n = accounts.size();
        DisJointDS ds = new DisJointDS(n);

        for (int i = 0; i < n; i++) {

            for (int j = 1; j < accounts.get(i).size(); j++) {

                String str = accounts.get(i).get(j);

                if (mailToNode.containsKey(str)) {

                    ds.unionBySize(i, mailToNode.get(str));

                } else {
                    mailToNode.put(str, i);
                }
            }
        }

        ArrayList<String> mergeAccount[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            mergeAccount[i] = new ArrayList<String>();
        }

        for (String key : mailToNode.keySet()) {

            int u = mailToNode.get(key);
            int ulp_u = ds.findParent(u);
            mergeAccount[ulp_u].add(key);

        }

        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (mergeAccount[i].size() == 0) {
                continue;
            }

            Collections.sort(mergeAccount[i]);

            List<String> ans = new ArrayList<>();
            ans.add(accounts.get(i).get(0));

            for (String str : mergeAccount[i]) {
                ans.add(str);
            }
            result.add(ans);
        }

        return result;

    }
}