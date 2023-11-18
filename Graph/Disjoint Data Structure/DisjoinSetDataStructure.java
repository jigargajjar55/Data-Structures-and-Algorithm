
import java.util.*;


   class DisJointDS {
        List<Integer> rank;
        List<Integer> size;
        List<Integer> parent;

        public DisJointDS(int noOfNodes) {
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


public class DisjoinSetDataStructure {

    public static void main(String[] args) {
        DisJointDS d1 = new DisJointDS(7);

        d1.unionBySize(1, 2);
        d1.unionBySize(2, 3);
        d1.unionBySize(4, 5);
        d1.unionBySize(6, 7);
        d1.unionBySize(5, 6);
        // If 3 & 7 present in same component or not
        if (d1.findParent(3) == d1.findParent(7)) {
            System.out.println("Same Component");
        } else {
            System.out.println("Not Same Component");
        }
        d1.unionBySize(3, 7);

        if (d1.findParent(3) == d1.findParent(7)) {
            System.out.println("Same Component");
        } else {
            System.out.println("Not Same Component");
        }
        String curr = "011";
        System.out.println(Integer.parseInt(curr, 2));

    }

}
