import java.util.*;

class Pair {
    int row;
    int col;
    int key;

    Pair(int row, int col, int key) {
        this.row = row;
        this.col = col;
        this.key = key;
    }
}

public class ShortestPathtoGetAllKeysLC864 {

    private boolean isSafe(int row, int col, int m, int n, String[] grid) {
        if ((row >= 0 && row < m) && (col >= 0 && col < n)) {
            return true;
        } else {
            return false;
        }
    }

    // Time: O((M * N) + (M * N * (2 ^ K))), Space: O(2 * M * N)

    public int shortestPathAllKeys(String[] grid) {

        int m = grid.length;
        int n = grid[0].length();

        int totalKeys = 0;
        Set<String> visited = new HashSet<>();
        Queue<Pair> q = new LinkedList<>();
        int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int x = -1;
        int y = -1;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                char currCh = grid[i].charAt(j);
                if (currCh == '@') {
                    x = i;
                    y = j;

                }
                if (currCh >= 'a' && currCh <= 'f') {
                    totalKeys++;
                }
            }
        }

        q.offer(new Pair(x, y, 0));
        visited.add(0 + " " + x + " " + y);

        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Pair top = q.poll();

                int row = top.row;
                int col = top.col;

                if (top.key == (1 << totalKeys) - 1) {
                    return level;
                }

                for (int j = 0; j < 4; j++) {
                    int nRow = row + dirs[j][0];
                    int nCol = col + dirs[j][1];
                    int key = top.key;

                    if (isSafe(nRow, nCol, m, n, grid)) {
                        char currCh = grid[nRow].charAt(nCol);
                        if (currCh == '#') {
                            continue;
                        }
                        if (currCh >= 'a' && currCh <= 'f') {
                            key |= (1 << (currCh - 'a'));
                        }
                        if (currCh >= 'A' && currCh <= 'F' && (((key >> (currCh - 'A')) & 1) == 0)) {
                            continue;
                        }
                        if (!visited.contains(key + " " + nRow + " " + nCol)) {
                            visited.add(key + " " + nRow + " " + nCol);
                            q.offer(new Pair(nRow, nCol, key));
                        }

                    }
                }
            }
            level++;
        }

        return -1;

    }
}