import java.util.*;

public class printLCS {

    public static String printLongestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }

        for (int index1 = 1; index1 <= n; index1++) {
            for (int index2 = 1; index2 <= m; index2++) {

                if (text1.charAt(index1 - 1) == text2.charAt(index2 - 1)) {
                    dp[index1][index2] = 1 + dp[index1 - 1][index2 - 1];

                } else {
                    dp[index1][index2] = Math.max(dp[index1 - 1][index2], dp[index1][index2 - 1]);
                }

            }
        }

        int len = dp[n][m];
        StringBuilder result = new StringBuilder();
        int index = 0;
        while (index < len) {
            result.append('a');
            index++;
        }

        index = len - 1;
        int i = n, j = m;
        // 1- based indexing
        // Time : O(M + N)
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                result.setCharAt(index, text1.charAt(i - 1));
                index--;
                i--;
                j--;

            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;

            } else {
                j--;

            }
        }

        return result.toString();

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the 2 strings: ");
        String text1 = sc.nextLine();
        String text2 = sc.nextLine();
        sc.close();

        String ans = printLongestCommonSubsequence(text1, text2);

        System.out.print("LCS of string 1 and 2: " + ans);

    }

}
