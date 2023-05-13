class ShortestCommonSupersequence {

    // Time : O(M * N) + O(N + M), Space: O(M * N) + O(M + N){2D DP Array and Answer string}
    private String solveByBottomUpDP(String text1, String text2) {

        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n + 1][m + 1];
        

        for (int index1 = 1; index1 <= n; index1++) {
            for (int index2 = 1; index2 <= m; index2++) {

                if (text1.charAt(index1 - 1) == text2.charAt(index2 - 1)) {
                    dp[index1][index2] = 1 + dp[index1 - 1][index2 - 1];

                } else {
                    dp[index1][index2] = Math.max(dp[index1 - 1][index2], dp[index1][index2 - 1]);
                }

            }
        }

        StringBuilder result = new StringBuilder();
        int i=n, j=m;

        while(i > 0 && j > 0){

            if(text1.charAt(i-1) == text2.charAt(j-1)){
                result.append(text1.charAt(i-1));
                i--;
                j--;

            }else if(dp[i-1][j] > dp[i][j-1]){
                result.append(text1.charAt(i-1));
                i--;

            }else{
                result.append(text2.charAt(j-1));
                j--;
            }
        }

        while(i > 0){
            result.append(text1.charAt(i-1));
            i--;
        }
        while(j > 0){
            result.append(text2.charAt(j-1));
            j--;
        }

        String ans = result.reverse().toString();

        return ans;

    }

    public String shortestCommonSupersequence(String str1, String str2) {

        return solveByBottomUpDP(str1,str2);
        
    }
}