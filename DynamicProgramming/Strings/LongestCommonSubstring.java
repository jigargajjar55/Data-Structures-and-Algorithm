package DynamicProgramming.Strings;
class LongestCommonSubstring{
    
     // Time : O(M * N), Space: O(M * N){2D DP Array}
    private int solveByBottomUpDP(String text1, String text2,int n, int m) {

        int[][] dp = new int[n + 1][m + 1];
        
        int ans = 0;

        for (int index1 = 1; index1 <= n; index1++) {
            for (int index2 = 1; index2 <= m; index2++) {

                if (text1.charAt(index1 - 1) == text2.charAt(index2 - 1)) {
                    dp[index1][index2] = 1 + dp[index1 - 1][index2 - 1];
                    
                    ans = Math.max(ans,dp[index1][index2]);

                } else {
                    dp[index1][index2] = 0;
                }

            }
        }

        return ans;

    }
    
     // Time : O(M * N), Space: O(M * N){2D DP Array}
    private int solveBySpaceOptimise(String text1, String text2,int n, int m) {

        int[] prev = new int[m + 1];
        
        int ans = 0;

        for (int index1 = 1; index1 <= n; index1++) {
            int[] curr = new int[m+1];
            for (int index2 = 1; index2 <= m; index2++) {

                if (text1.charAt(index1 - 1) == text2.charAt(index2 - 1)) {
                    curr[index2] = 1 + prev[index2 - 1];
                    
                    ans = Math.max(ans, curr[index2]);

                } else {
                    curr[index2] = 0;
                }

            }
            prev = curr;
        }

        return ans;

    }
    
    
    int longestCommonSubstr(String S1, String S2, int n, int m){
        
       // return solveByBottomUpDP(S1,S2,n,m);
        
        return solveBySpaceOptimise(S1,S2,n,m);
        
    }
}