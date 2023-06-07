
//Time: O(N * M), Space: O(1)
public class WherewillTheBallFall {

    public int[] findBall(int[][] grid) {

        int rowSize = grid.length;
        int colSize = grid[0].length;
        int result[] = new int[colSize];

        for (int j = 0; j < colSize; j++) {

            int x = 0;
            int y = j;
            boolean isStuck = false;

            while (x < rowSize) {

                int current = grid[x][y];

                // For 1 :- Move right side
                if (current == 1) {
                    if (isSafe(grid, x, y + 1, rowSize, colSize, current)) {

                        y++;
                        x++;
                    } else {
                        // System.out.println(x + " " + y + " ball"+ j);
                        isStuck = true;
                        break;
                    }
                }
                // For -1 :- Move Left side
                else {

                    if (isSafe(grid, x, y - 1, rowSize, colSize, current)) {
                        y--;
                        x++;
                    } else {
                        isStuck = true;
                        break;
                    }

                }

            }

            if (isStuck) {
                result[j] = -1;
            } else {
                result[j] = y;
            }

        }

        return result;

    }

    public boolean isSafe(int[][] grid, int x, int y, int n, int m, int current) {

        if ((x >= 0 && x < n) && (y >= 0 && y < m) && (current == grid[x][y])) {
            return true;
        } else {
            return false;
        }

    }

}
