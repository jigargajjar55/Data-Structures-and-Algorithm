package DynamicProgramming.PartitionAndMCM;

import java.util.*;

public class BooleanExpEvalToTrue {
    private static int addMod(int a, int b) {
        return (((a % (int) (1e9 + 7)) + (b % (int) (1e9 + 7))) % (int) (1e9 + 7));
    }

    private static int multiplyMod(int a, int b) {
        return (((a % (int) (1e9 + 7)) * (b % (int) (1e9 + 7))) % (int) (1e9 + 7));
    }

    // Time: O(Exponential), Space:O(N){Aux. Stack Space}
    private static int solveByRecursion(int i, int j, int isTrue, String exp) {

        // Base Case
        if (i > j) {
            return 0;
        }
        if (i == j) {
            int ans = 0;
            if (isTrue == 1) {
                ans = exp.charAt(i) == 'T' ? 1 : 0;
            } else {
                ans = exp.charAt(i) == 'F' ? 1 : 0;
            }
            return ans;
        }

        int ways = 0;

        for (int index = i + 1; index <= j - 1; index += 2) {

            int leftPartTrue = solveByRecursion(i, index - 1, 1, exp);
            int leftPartFalse = solveByRecursion(i, index - 1, 0, exp);
            int rightPartTrue = solveByRecursion(index + 1, j, 1, exp);
            int rightPartFalse = solveByRecursion(index + 1, j, 0, exp);

            if (exp.charAt(index) == '&') {
                if (isTrue == 1) {
                    ways = addMod(ways, multiplyMod(leftPartTrue, rightPartTrue));
                } else {
                    ways = addMod(ways, addMod(addMod(multiplyMod(leftPartTrue, rightPartFalse),
                            multiplyMod(leftPartFalse, rightPartTrue)),
                            multiplyMod(leftPartFalse, rightPartFalse)));
                }
            } else if (exp.charAt(index) == '|') {
                if (isTrue == 1) {
                    ways = addMod(ways, addMod(addMod(multiplyMod(leftPartTrue, rightPartFalse),
                            multiplyMod(leftPartFalse, rightPartTrue)),
                            multiplyMod(leftPartTrue, rightPartTrue)));
                } else {
                    ways = addMod(ways, multiplyMod(leftPartFalse, rightPartFalse));
                }
            } else if (exp.charAt(index) == '^') {
                if (isTrue == 1) {
                    ways = addMod(ways, addMod(multiplyMod(leftPartTrue, rightPartFalse),
                            multiplyMod(leftPartFalse, rightPartTrue)));
                } else {
                    ways = addMod(ways, addMod(multiplyMod(leftPartTrue, rightPartTrue),
                            multiplyMod(leftPartFalse, rightPartFalse)));
                }
            }

        }

        return ways;

    }

    // Time: O(N * N * 2) * N ~ O(N ^ 3), Space:O(N + (N * N * 2)){Aux. Stack Space
    // and 3D DP Array}
    private static int solveByTopDownDP(int i, int j, int isTrue, String exp, int[][][] dp) {

        // Base Case
        if (i > j) {
            return 0;
        }
        if (i == j) {
            int ans = 0;
            if (isTrue == 1) {
                ans = exp.charAt(i) == 'T' ? 1 : 0;
            } else {
                ans = exp.charAt(i) == 'F' ? 1 : 0;
            }
            return dp[i][j][isTrue] = ans;
        }

        // Overlapping sub-problem
        if (dp[i][j][isTrue] != -1) {
            return dp[i][j][isTrue];
        }

        int ways = 0;

        for (int index = i + 1; index <= j - 1; index += 2) {

            int leftPartTrue = solveByTopDownDP(i, index - 1, 1, exp, dp);
            int leftPartFalse = solveByTopDownDP(i, index - 1, 0, exp, dp);
            int rightPartTrue = solveByTopDownDP(index + 1, j, 1, exp, dp);
            int rightPartFalse = solveByTopDownDP(index + 1, j, 0, exp, dp);

            if (exp.charAt(index) == '&') {
                if (isTrue == 1) {
                    ways = addMod(ways, multiplyMod(leftPartTrue, rightPartTrue));
                } else {
                    ways = addMod(ways, addMod(addMod(multiplyMod(leftPartTrue, rightPartFalse),
                            multiplyMod(leftPartFalse, rightPartTrue)),
                            multiplyMod(leftPartFalse, rightPartFalse)));
                }
            } else if (exp.charAt(index) == '|') {
                if (isTrue == 1) {
                    ways = addMod(ways, addMod(addMod(multiplyMod(leftPartTrue, rightPartFalse),
                            multiplyMod(leftPartFalse, rightPartTrue)),
                            multiplyMod(leftPartTrue, rightPartTrue)));
                } else {
                    ways = addMod(ways, multiplyMod(leftPartFalse, rightPartFalse));
                }
            } else {
                if (isTrue == 1) {
                    ways = addMod(ways, addMod(multiplyMod(leftPartTrue, rightPartFalse),
                            multiplyMod(leftPartFalse, rightPartTrue)));
                } else {
                    ways = addMod(ways, addMod(multiplyMod(leftPartTrue, rightPartTrue),
                            multiplyMod(leftPartFalse, rightPartFalse)));
                }
            }

        }

        return dp[i][j][isTrue] = ways;

    }

    // Time: O(N * N * 2) * N ~ O(N ^ 3), Space:O(N * N * 2)){3D DP Array}
    private static int solveByBottomUpDP(int N, String exp) {

        int[][][] dp = new int[N][N][2];
        // Analyse the base case
        for (int i = 0; i < N; i++) {
            dp[i][i][1] = exp.charAt(i) == 'T' ? 1 : 0;
            dp[i][i][0] = exp.charAt(i) == 'F' ? 1 : 0;
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                for (int isTrue = 0; isTrue < 2; isTrue++) {

                    if (i > j) {
                        continue;
                    }

                    int ways = 0;

                    for (int index = i + 1; index <= j - 1; index += 2) {

                        int leftPartTrue = dp[i][index - 1][1];
                        int leftPartFalse = dp[i][index - 1][0];
                        int rightPartTrue = dp[index + 1][j][1];
                        int rightPartFalse = dp[index + 1][j][0];

                        if (exp.charAt(index) == '&') {
                            if (isTrue == 1) {
                                ways = addMod(ways, multiplyMod(leftPartTrue, rightPartTrue));
                            } else {
                                ways = addMod(ways, addMod(addMod(multiplyMod(leftPartTrue, rightPartFalse),
                                        multiplyMod(leftPartFalse, rightPartTrue)),
                                        multiplyMod(leftPartFalse, rightPartFalse)));
                            }
                        } else if (exp.charAt(index) == '|') {
                            if (isTrue == 1) {
                                ways = addMod(ways, addMod(addMod(multiplyMod(leftPartTrue, rightPartFalse),
                                        multiplyMod(leftPartFalse, rightPartTrue)),
                                        multiplyMod(leftPartTrue, rightPartTrue)));
                            } else {
                                ways = addMod(ways, multiplyMod(leftPartFalse, rightPartFalse));
                            }
                        } else {
                            if (isTrue == 1) {
                                ways = addMod(ways, addMod(multiplyMod(leftPartTrue, rightPartFalse),
                                        multiplyMod(leftPartFalse, rightPartTrue)));
                            } else {
                                ways = addMod(ways, addMod(multiplyMod(leftPartTrue, rightPartTrue),
                                        multiplyMod(leftPartFalse, rightPartFalse)));
                            }
                        }

                    }

                    dp[i][j][isTrue] = ways;

                }

            }
        }

        return dp[0][N - 1][1];

    }

    static int countWays(int N, String S) {

        // return solveByRecursion(0,N-1,1,S);

        // int[][][] dp = new int[N][N][2];
        // for(int i=0; i<N; i++){
        // for(int[] row : dp[i]){
        // Arrays.fill(row,-1);
        // }
        // }
        // return solveByTopDownDP(0,N-1,1,S,dp);

        return solveByBottomUpDP(N, S);
    }
}
