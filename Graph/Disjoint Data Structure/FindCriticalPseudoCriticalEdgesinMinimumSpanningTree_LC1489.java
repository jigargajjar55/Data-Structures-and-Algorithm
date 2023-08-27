import java.util.*;

public class FindCriticalPseudoCriticalEdgesinMinimumSpanningTree_LC1489 {

    // https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/solutions/3929720/ex-amazon-explains-a-solution-with-python-and-java/
    // https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/solutions/698311/python-unionfind-kruskal-solution-with-detail-explanation-from-o-e-2-to-o-eloge/


    private class DisJointDS{
        int[] parent;
        int count;
        DisJointDS(int n){
            parent = new int[n];
            count = n;
            for(int i=0; i<n; i++){
                parent[i] = i;
            }
        }
        public int findParent(int node){

            if(parent[node] == node){
                return node;
            }
            
            int ulp = findParent(parent[node]);
            parent[node] = ulp;

            return parent[node];
        }
        public boolean union(int u, int v){
            int ulp_u = findParent(u);
            int ulp_v = findParent(v);

            if(ulp_u != ulp_v){
                parent[ulp_u] = ulp_v;
                count--;
                return true;
            }else{
                return false;
            }
        }

    }
    
    private int buildMST(int n,int[][] edges,int[] include,int[] exclude){
        DisJointDS ds = new DisJointDS(n);
        int cost = 0;
        if(include != null){
            ds.union(include[0],include[1]);
            cost += include[2];
        }

        for(int[] edge : edges){

            if(edge != exclude && ds.union(edge[0], edge[1])){
                cost += edge[2];
            }
        }

        if(ds.count == 1){
            return cost;
        }else{
            return Integer.MAX_VALUE;
        }

    }
    
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudoCritical = new ArrayList<>();
        Map<int[], Integer> map = new HashMap<>();
        int e = edges.length;
        for(int i=0; i<e; i++){
            map.put(edges[i],i);
        }

        Arrays.sort(edges, (e1,e2) -> Integer.compare(e1[2], e2[2]));
        int mstCost = buildMST(n,edges,null,null);

        for(int i=0; i<e; i++){
            int[] edge = edges[i];
            int index = map.get(edge);

            int excludeCost = buildMST(n,edges,null,edge);

            if(excludeCost != mstCost){
                critical.add(index);
            }else{
                int includeCost = buildMST(n,edges,edge,null);
                if(includeCost == mstCost){
                    pseudoCritical.add(index);
                }
            }
        }

        return Arrays.asList(critical,pseudoCritical);
        
    }
}