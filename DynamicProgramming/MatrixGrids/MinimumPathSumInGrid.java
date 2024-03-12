package DynamicProgramming.MatrixGrids;
class MinimumPathSumInGrid {

    //Time : O(2 ^ (m * n)), Space: O(path length) ~ O((m-1) + (n-1))
    private int solveByRecursion(int row,int col,int[][] grid){
        //Base case
        if(row == 0 && col == 0){
            return grid[row][col];
        }
        
        if(row < 0 || col < 0){
            return (int)(1e9);
        }
        
        int up = grid[row][col] + solveByRecursion(row-1,col,grid);
        int left = grid[row][col] + solveByRecursion(row,col-1,grid);
        
        int ans = Math.min(up,left);
        
        return ans;        
    }

    //Time : O(m * n), Space: O(Dp Array and path length) ~ O(n * m) + O((m-1) + (n-1))
    private int solveByTopDownDP(int row,int col,int[][] grid,int[][] dp){
        //Base case
        if(row == 0 && col == 0){
            return grid[row][col];
        }        
        if(row < 0 || col < 0){
            return (int)(1e9);
        }
         
        // Overlapping Sub-Problem
        if(dp[row][col] != -1){
            return dp[row][col];
        }
        
        int up = grid[row][col] + solveByTopDownDP(row-1,col,grid,dp);
        int left = grid[row][col] + solveByTopDownDP(row,col-1,grid,dp);
        
        dp[row][col] = Math.min(up,left);
        
        return dp[row][col];
        
    }

    //Time : O(m * n), Space: O(m * n) (For DP Array)
    private int solveByBottomUpDP(int rows,int cols,int[][] grid){

        int[][] dp = new int[rows][cols];
        dp[0][0] = grid[0][0];

        for(int row=0; row < rows; row++){
            for(int col=0;col < cols; col++){

                if(row == 0 && col == 0){
                    continue;
                }else{
                    int up = grid[row][col];
                    int left = grid[row][col];

                    if(row > 0){
                        up += dp[row-1][col];
                    }else{
                        up = (int)(1e9);
                    }
                    if(col > 0){
                        left += dp[row][col-1];
                    }else{
                        left = (int)(1e9);
                    }

                    dp[row][col] = Math.min(up,left);
                }
            }
        }
        return dp[rows-1][cols-1];
    }

    //Time : O(m * n), Space: O(n) (For 1D DP Array)
    private int solveBySpaceOptimise(int rows,int cols,int[][] grid){

        int[] prev = new int[cols];

        for(int row=0;row < rows; row++){
            int[] curr = new int[cols];
            for(int col=0; col < cols; col++){

                if(row == 0 && col == 0){
                    curr[col] = grid[row][col];
                }else{

                    int up = grid[row][col];
                    int left = grid[row][col];

                    if(row > 0){
                        up += prev[col];
                    }else{
                        up = (int)(1e9);
                    }

                    if(col > 0){
                        left += curr[col-1];
                    }else{
                        left = (int)(1e9);
                    }

                    curr[col] = Math.min(up,left);
                }
            }

            prev = curr;
        }

        return prev[cols-1];


    }


    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // return solveByRecursion(m-1,n-1,grid);

        // int[][] dp = new int[m][n];
        // for(int[] row : dp){
        //     Arrays.fill(row,-1);
        // }

        // return solveByTopDownDP(m-1,n-1,grid,dp);

        //  return solveByBottomUpDP(m,n,grid);

        return solveBySpaceOptimise(m,n,grid);      
        
        
    }
}