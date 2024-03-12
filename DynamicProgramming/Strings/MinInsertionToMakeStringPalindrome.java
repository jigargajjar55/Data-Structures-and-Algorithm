package DynamicProgramming.Strings;
class MinInsertionToMakeStringPalindrome {

    //Time: O(2^N * 2^M), Space: O(N + M){Aux. stack space}
    private int solveByRecursion(int index1,String str,int index2,String revstr){

        //Base case
        if(index1 < 0 || index2 < 0){
            return 0;
        }

        if(str.charAt(index1) == revstr.charAt(index2)){
            return 1 + solveByRecursion(index1-1,str,index2-1,revstr);

        }else{

            return Math.max(solveByRecursion(index1-1,str,index2,revstr), solveByRecursion(index1,str,index2-1,revstr));

        }

    }

    //Time: O(N * M), Space: O(N + M) + O(N * M){Aux. stack space and 2D DP Array}
    private int solveByTopDownDP(int index1,String str,int index2,String revstr,int[][] dp){

        //Base case
        if(index1 == 0 || index2 == 0){
            return dp[index1][index2] = 0;
        }

        //Overlapping sub-problem
        if(dp[index1][index2] != -1){
            return dp[index1][index2];
        }

        if(str.charAt(index1-1) == revstr.charAt(index2-1)){
            return dp[index1][index2] = 1 + solveByTopDownDP(index1-1,str,index2-1,revstr,dp);

        }else{

            return dp[index1][index2] = Math.max(solveByTopDownDP(index1-1,str,index2,revstr,dp), solveByTopDownDP(index1,str,index2-1,revstr,dp));

        }

    }

    //Time: O(N * M), Space:  O(N * M){2D DP Array}
    private int solveByBottomUpDP(int n,String str,int m,String revstr){

        int[][] dp = new int[n+1][m+1];
        //Analyse base case
        for(int i=0; i<=n; i++){
            dp[i][0] = 0;
        }
        for(int i=0; i<=m; i++){
            dp[0][i] = 0;
        }

        for(int index1 = 1; index1 <= n; index1++){
            for(int index2 = 1; index2 <= m; index2++){

                if(str.charAt(index1-1) == revstr.charAt(index2-1)){
                    dp[index1][index2] = 1 + dp[index1-1][index2-1];

                }else{
                    dp[index1][index2] = Math.max(dp[index1-1][index2], dp[index1][index2-1]);
                }

            }
        }

        return dp[n][m];

    }

    //Time: O(N * M), Space:  O(2 * M){1D DP Array}
    private int solveBySpaceOptimise(int n,String str,int m,String revstr){

        int[] prev = new int[m+1];
        //Analyse base case
        prev[0] = 0;

        for(int index1 = 1; index1 <= n; index1++){
            int[] curr = new int[m+1];
            for(int index2 = 1; index2 <= m; index2++){

                if(str.charAt(index1-1) == revstr.charAt(index2-1)){
                    curr[index2] = 1 + prev[index2-1];

                }else{
                    curr[index2] = Math.max(prev[index2], curr[index2-1]);
                }

            }
            prev = curr;
        }

        return prev[m];

    }

    private int LongestCommonPalindromeSubsequence(String s){

        StringBuilder str = new StringBuilder(s);
        int n = str.length();
        String revS = str.reverse().toString();
        int m = revS.length();

        //return solveByRecursion(n-1,s,m-1,revS); 

        // int[][] dp = new int[n+1][m+1];
        // for(int[] row : dp){
        //     Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(n,s,m,revS,dp);

        //return solveByBottomUpDP(n,s,m,revS);

        return solveBySpaceOptimise(n,s,m,revS);

    }


    public int minInsertions(String s) {

        int ans = s.length() - LongestCommonPalindromeSubsequence(s);

        return ans;
        
    }
}