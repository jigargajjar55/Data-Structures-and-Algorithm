package Disjoint Data Structure;

public class CountServersthatCommunicate_LC1267 {
 
    
    class DisJointSet{
        int noOfNodes;
        int[] parent;
        int[] size;
        DisJointSet(int noOfNodes){
            this.noOfNodes = noOfNodes;
            this.parent = new int[noOfNodes+1];
            this.size = new int[noOfNodes+1];

            for(int i=0; i<=noOfNodes; i++){
                parent[i] = i;
                size[i] = 0;
            }
        }

        public int findParent(int node){

            //Base Case
            if(parent[node] == node){
                return node;
            }

            parent[node] = findParent(parent[node]);

            return parent[node];
        }

        public void unionbySize(int u, int v){

            int ulp_u = findParent(u);
            int ulp_v = findParent(v);

            if(ulp_u == ulp_v){
                size[ulp_v]++;
                return;
            }

            if(size[ulp_u] > size[ulp_v]){
                parent[ulp_v] = ulp_u;
                size[ulp_u] += (1 + size[ulp_v]);
            }else{
                parent[ulp_u] = ulp_v;
                size[ulp_v] += (1 + size[ulp_u]);
            }
        }

        public int getCompSize(int node){
            return size[node];
        }


    }
    //Time: O((M * N * (4a)))
    //Space: O(M + N)
    public int countServers1(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        DisJointSet ds = new DisJointSet(m + n + 1);
        Set<Integer> set = new HashSet<>();

        for(int row=0; row<m; row++){
            for(int col=0; col<n; col++){

                if(grid[row][col] == 1){
                    
                    int u = row;
                    int v = m + col + 1;

                    ds.unionbySize(u, v);
                    set.add(u);
                    set.add(v);

                    
                }
            }
        }

        int result = 0;

        for(int node : set){

            int ulp_Node = ds.findParent(node);

            if((node == ulp_Node)){
                if(ds.getCompSize(ulp_Node) > 1){
                    result += ds.getCompSize(ulp_Node);
                }
            }
        }

        return result;

        
    }



    //Time: O(M * N)
    //Space: O(M + N)
    public int countServers(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){
                    row[i]++;
                    col[j]++;
                }
            }
        }

        int result = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1 && (row[i] > 1 || col[j] > 1)){
                    result += 1;
                }
            }
        }
        return result;
    }

}
