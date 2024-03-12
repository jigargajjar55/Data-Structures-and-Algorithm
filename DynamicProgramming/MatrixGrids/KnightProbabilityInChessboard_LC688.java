package DynamicProgramming.MatrixGrids;
import java.util.*;

public class KnightProbabilityInChessboard_LC688 {
  
    
    //Time: O((N * M) ^ K), Space: O(N + N){Aux. Stack Space}
    private double solveByRecursion(int row,int col,int k,int n,int[][] del){

        //Base Case
        if(row < 0 || row >= n || col < 0 || col >= n){
            return 0;
        }

        if(k == 0){
            return 1;
        }

        double ans = 0;
        for(int i=0; i<8; i++){
            int nRow = row + del[i][0];
            int nCol = col + del[i][1];

            ans += solveByRecursion(nRow,nCol,k-1,n,del)/8;
        }

        return ans;
    }

    //Time: O((N * M * K), Space: O(N + N + (N * N * K)){Aux. Stack Space and 3D DP Array}
    private double solveByTopDownDP(int row,int col,int k,int n,int[][] del,double[][][] dp){

        //Base Case
        if(row < 0 || row >= n || col < 0 || col >= n){
            return 0;
        }

        if(k == 0){
            return 1;
        }

        //Overlapping sub-problem
        if(dp[row][col][k] != -1){
            return dp[row][col][k];
        }

        double ans = 0;
        for(int i=0; i<8; i++){
            int nRow = row + del[i][0];
            int nCol = col + del[i][1];

            ans += solveByTopDownDP(nRow,nCol,k-1,n,del,dp)/8;
        }

        return dp[row][col][k] = ans;
    }

    public double knightProbability(int n, int k, int row, int column) {

        int[][] del = {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
        
        //return solveByRecursion(row,column,k,n,del);

        double[][][] dp = new double[n][n][k+1];
        for(int i=0; i<n; i++){
            for(double[] rows : dp[i]){
                Arrays.fill(rows,-1);
            }
        }

        return solveByTopDownDP(row,column,k,n,del,dp);
        
    }
}