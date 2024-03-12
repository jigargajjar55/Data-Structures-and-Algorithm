package BinarySearch;

class LastDayWhereYouCanStillCrossLC1970 {

    private class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private boolean isSafe(int row, int col, int[][] matrix, boolean[][] visited) {
        if ((row >= 0 && row < matrix.length) && (col >= 0 && col < matrix[0].length) && matrix[row][col] == 0
                && !visited[row][col]) {
            return true;
        } else {
            return false;
        }
    }

    private boolean dfs(int row, int col, int[][] matrix, boolean[][] visited, int[][] dirs) {

        // Base Case
        if (row == matrix.length - 1) {
            return true;
        }

        visited[row][col] = true;
        boolean ans = false;

        for (int[] dir : dirs) {
            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if (isSafe(nRow, nCol, matrix, visited)) {
                ans = dfs(nRow, nCol, matrix, visited, dirs);

                if (ans) {
                    return true;
                }
            }
        }
        return ans;
    }

    private boolean isPossible(int day, int rows, int cols, int[][] cells) {

        int[][] matrix = new int[rows][cols];
        int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < day; i++) {
            int x = cells[i][0] - 1;
            int y = cells[i][1] - 1;
            matrix[x][y] = 1;
        }

        /*
         * //BFS to check if we can reach from top row to bottom roa
         * Queue<Pair> q = new LinkedList<>();
         * for(int i=0; i<cols; i++){
         * if(matrix[0][i] == 0){
         * q.offer(new Pair(0,i));
         * visited[0][i] = true;
         * }
         * }
         * 
         * while(!q.isEmpty()){
         * Pair top = q.poll();
         * int topRow = top.row;
         * int topCol = top.col;
         * 
         * if(topRow == rows - 1){
         * return true;
         * }
         * 
         * for(int[] dir : dirs){
         * int nRow = topRow + dir[0];
         * int nCol = topCol + dir[1];
         * 
         * if(isSafe(nRow,nCol,matrix,visited)){
         * q.offer(new Pair(nRow,nCol));
         * visited[nRow][nCol] = true;
         * }
         * }
         * }
         * return false;
         */

        // DFS to check if we can reach from Top Row to Bottom Row
        for (int i = 0; i < cols; i++) {

            if (matrix[0][i] == 0) {
                if (dfs(0, i, matrix, visited, dirs)) {
                    return true;
                }
            }
        }

        return false;

    }

    // BruteForce Approach
    // Time:- O((N * M) * (N * M * 4)), Space:- O(3 * N * M) {N: no. Of Rows, M: no.
    // Of Cols}
    public int latestDayToCrossBruteForce(int row, int col, int[][] cells) {

        int n = cells.length;
        int[][] matrix = new int[row][col];
        int[][] dirs = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };

        int day = 0;

        for (int i = 0; i < n; i++) {
            int x = cells[i][0] - 1;
            int y = cells[i][1] - 1;
            matrix[x][y] = 1;

            boolean lastDay = false;

            for (int j = 0; j < col; j++) {
                if (matrix[0][j] == 0 && dfs(0, j, matrix, new boolean[row][col], dirs)) {
                    lastDay = true;
                    day++;
                    break;
                }
            }

            if (!lastDay) {
                break;
            }
        }

        return day;

    }

    // Time: O((rows * cols) * log(rows * cols)), Space: O(2 * rows * cols)
    public int latestDayToCross(int row, int col, int[][] cells) {

        int lastDay = 0;
        int start = 1;
        int end = cells.length;

        while (start <= end) {
            int mid = start + ((end - start) / 2);

            if (isPossible(mid, row, col, cells)) {
                lastDay = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return lastDay;
    }
}