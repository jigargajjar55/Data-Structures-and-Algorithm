package MatrixAndGrid;

public class SumofMatrixAfterQueriesLC2718 {

    /*
     * Approach:
     * 
     * We Start executing queries from right to left. Why?
     * Because if we start executing from left to right we have to change values for
     * cell, but if we start in opposite direction then it doesn't required to
     * change it.
     *
     * When there is query for row, we will check if it already marked as visited or
     * not.
     * If it's visited, we won't do any calculation as it's going to overwrite by
     * upcoming query from right side.
     * If it's not visited, we will add (value * no. of Columns) and maked row
     * visited and decrease rowCount.
     * 
     * 
     * When there is query for column, we will check if it already marked as visited
     * or not.
     * If it's visited, we won't do any calculation as it's going to overwrite by
     * upcoming query from right side.
     * If it's not visited, we will add (value * no. of rows) and maked row visited
     * and decrease colCount.
     * 
     * That's how we can optimize the solution.
     *
     * Time: O(Length of Queries array), Space: O(2 * N)
     * 
     * 
     */
    public long matrixSumQueries(int n, int[][] queries) {

        long result = 0;

        boolean[] rowVisited = new boolean[n];
        boolean[] colVisited = new boolean[n];

        int rowCount = n;
        int colCount = n;

        int qLength = queries.length;

        for (int i = qLength - 1; i >= 0; i--) {
            int type = queries[i][0];
            int index = queries[i][1];
            int value = queries[i][2];

            if (type == 0) {
                if (rowVisited[index]) {
                    continue;
                }

                result = result + (value * colCount);
                rowVisited[index] = true;
                rowCount--;
            } else {

                if (colVisited[index]) {
                    continue;
                }

                result = result + (value * rowCount);
                colVisited[index] = true;
                colCount--;

            }

        }

        return result;
    }
}
