import java.util.*;

public class InterleavingString_LC97 {
    

    
    //Time: O(Exponential), Space: O(Length of S3 string){Aux. Stack Space}
    private boolean solveByRecursion(int i,int j,int n,int m,int k,String s1,String s2,String s3){

        //Base Case
        if(i+j == k){
            return true;
        }

        boolean result = false;
        if(i<n && s1.charAt(i) == s3.charAt(i+j)){
            boolean ans1 = solveByRecursion(i+1,j,n,m,k,s1,s2,s3);
            result = result || ans1;
        }

        if(j<m && s2.charAt(j) == s3.charAt(i+j)){
            boolean ans2 = solveByRecursion(i,j+1,n,m,k,s1,s2,s3);
            result = result || ans2;
        }

        return result;

    }

    //Time: O(N * M), Space: O(Length of S3 string + (N * M)){Aux. Stack Space and 2D DP Array}
    private boolean solveByTopDownDP(int i,int j,int n,int m,int k,String s1,String s2,String s3,int[][] dp){

        //Base Case
        if(i+j == k){
            return true;
        }

        //Overlapping sub-problem
        if(dp[i][j] != -1){
            return dp[i][j] == 1;
        }

        boolean result = false;
        if(i<n && s1.charAt(i) == s3.charAt(i+j)){
            boolean ans1 = solveByTopDownDP(i+1,j,n,m,k,s1,s2,s3,dp);
            result = result || ans1;
        }

        if(j<m && s2.charAt(j) == s3.charAt(i+j)){
            boolean ans2 = solveByTopDownDP(i,j+1,n,m,k,s1,s2,s3,dp);
            result = result || ans2;
        }

        dp[i][j] = result ? 1 : 0;

        return result;
    }

     //Time: O(N * M), Space: O(N * M){2D DP Array}
    private boolean solveByBottomUpDP(int n,int m,int k,String s1,String s2,String s3){

        boolean[][] dp = new boolean[n+1][m+1];

        for(int i = n; i>=0; i--){
            for(int j = m; j>=0; j--){                

                if(i==n && j==m){
                    dp[i][j] = true;
                }else if(i<n && j<m && s1.charAt(i) == s3.charAt(i+j) && s2.charAt(j) == s3.charAt(i+j)){
                    dp[i][j] = dp[i+1][j] || dp[i][j+1];

                } else if(i<n && s1.charAt(i) == s3.charAt(i+j)){
                    dp[i][j] = dp[i+1][j];
                } else if(j<m && s2.charAt(j) == s3.charAt(i+j)){
                    dp[i][j] = dp[i][j+1];
                }else{
                    dp[i][j] = false;
                }               
            }
        }     

        return dp[0][0];
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int k = s3.length();

        if(n + m != k){
            return false;
        }

        // return solveByRecursion(0,0,n,m,k,s1,s2,s3);

        int[][] dp = new int[n+1][m+1];
        for(int[] row: dp){
            Arrays.fill(row, -1);
        }
        return solveByTopDownDP(0,0,n,m,k,s1,s2,s3,dp);


        //return solveByBottomUpDP(n,m,k,s1,s2,s3);
        
    }
}