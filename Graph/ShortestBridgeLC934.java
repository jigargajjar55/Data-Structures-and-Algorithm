package Graph;

import java.util.*;

class Pair {
    int row;
    int col;

    Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
    private boolean isSafe(int row, int col, int n) {
        if ((row >= 0 && row < n) && (col >= 0 && col < n)) {
            return true;
        } else {
            return false;
        }
    }

    // Time: O(N * M * 4), Space:(N + M){Aux. Stack space}
    private void dfs(int row, int col, int n, int[][] grid, boolean[][] visited, int[] delRow, int[] delCol,
            Queue<Pair> q) {
        visited[row][col] = true;

        q.offer(new Pair(row, col));

        for (int i = 0; i < 4; i++) {
            int nRow = row + delRow[i];
            int nCol = col + delCol[i];

            if (isSafe(nRow, nCol, n) && grid[nRow][nCol] == 1 && !visited[nRow][nCol]) {

                dfs(nRow, nCol, n, grid, visited, delRow, delCol, q);

            }
        }

    }

    // Time: O(N * N * 4) + O(N * N * 4), Space: O((N ^ 2) + (N ^ 2) + (2 * 4)
    public int shortestBridge(int[][] grid) {

        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        Queue<Pair> q = new LinkedList<>();

        int[] delRow = { -1, 0, 1, 0 };
        int[] delCol = { 0, 1, 0, -1 };

        boolean isFound = false;

        for (int i = 0; i < n; i++) {
            if (isFound) {
                break;
            }
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j, n, grid, visited, delRow, delCol, q);
                    isFound = true;
                    break;
                }
            }
        }

        int steps = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int k = 0; k < size; k++) {

                Pair top = q.peek();
                q.poll();
                int row = top.row;
                int col = top.col;

                for (int i = 0; i < 4; i++) {
                    int nRow = row + delRow[i];
                    int nCol = col + delCol[i];

                    if (isSafe(nRow, nCol, n) && !visited[nRow][nCol]) {
                        if (grid[nRow][nCol] == 1) {
                            return steps;
                        }
                        q.offer(new Pair(nRow, nCol));
                        visited[nRow][nCol] = true;
                    }

                }

            }

            steps++;

        }

        return -1;

    }
}