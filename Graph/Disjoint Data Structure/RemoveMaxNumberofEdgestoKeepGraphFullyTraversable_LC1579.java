public class RemoveMaxNumberofEdgestoKeepGraphFullyTraversable_LC1579 {
    

    

    class UnionFind{
        int noOfComponents;
        int[] size;
        int[] parent;
        UnionFind(int nodes){
            this.noOfComponents = nodes;
            this.size = new int[nodes];
            this.parent = new int[nodes];
            for(int i=0; i<nodes; i++){
                size[i] = 1;
                this.parent[i] = i;
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

        public boolean unionBySize(int u, int v){
            int ulp_u = findParent(u);
            int ulp_v = findParent(v);

            if(ulp_u == ulp_v){
                return false;
            }

            if(size[ulp_u] > size[ulp_v]){
                parent[ulp_v] = ulp_u;
                size[ulp_u] += size[ulp_v];
            }else{
                parent[ulp_u] = ulp_v;
                size[ulp_v] += size[ulp_u];
            }

            this.noOfComponents -= 1;

            return true;
        }

        public boolean isConnected(){
            return (this.noOfComponents == 1);
        }        

    }
    // https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/solutions/3468066/image-explanation-easiest-complete-intuition-c-java-python/?envType=daily-question&envId=2024-06-30
    //Time: O(2 * E)
    //Space: O(4 * N)
    public int maxNumEdgesToRemove(int n, int[][] edges) {

        UnionFind alice = new UnionFind(n);
        UnionFind bob = new UnionFind(n);

        int totalEdges = edges.length;
        int requiredEdges = 0;

        for(int i=0; i<totalEdges; i++){

            int u = edges[i][1]-1;
            int v = edges[i][2]-1;

            if(edges[i][0] == 3){
                if(alice.unionBySize(u,v) && bob.unionBySize(u,v)){
                    requiredEdges += 1;
                }
            }

        }

        //System.out.println(requiredEdges);

        for(int i=0; i<totalEdges; i++){

            int u = edges[i][1]-1;
            int v = edges[i][2]-1;

            if(edges[i][0] == 1){
                if(alice.unionBySize(u,v)){
                    requiredEdges += 1;
                }               
            }else if(edges[i][0] == 2){
                if(bob.unionBySize(u,v)){
                    requiredEdges += 1;
                }            
            }
            
        }

        if(alice.isConnected() && bob.isConnected()){
            //System.out.println(totalEdges + " " +requiredEdges);
            return (totalEdges - requiredEdges);
        }

        return -1;
        
    }
}