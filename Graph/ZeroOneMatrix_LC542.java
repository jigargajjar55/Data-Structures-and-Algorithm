import java.util.*;

public class ZeroOneMatrix_LC542 { 
    
    private class Tuple{
        int row;
        int col;
        int step;
        Tuple(int row, int col,int step){
            this.row = row;
            this.col = col;
            this.step = step;
        }
    }

    private boolean isSafe(int row,int col,int m,int n,int[][] mat,boolean[][] visited){

        if((row>=0 && row<m) && (col>=0 && col<n) && (mat[row][col] == 1) && !visited[row][col]){
            return true;
        }else{
            return false;
        }

    }
    
    // https://leetcode.com/problems/01-matrix/solutions/1369741/C++JavaPython-BFS-DP-solutions-with-Picture-Clean-and-Concise-O(1)-Space/
    //Time: O((M * N) + (M * N * 4)), Space: O(3 * M * N)
    
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] del = {{-1,0},{0,1},{1,0},{0,-1}};

        int[][] result = new int[m][n];
        Queue<Tuple> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j] == 0){
                    q.offer(new Tuple(i,j,0));
                    visited[i][j] = true;
                }
            }
        }

        while(!q.isEmpty()){
            Tuple top = q.peek();
            q.poll();
            int topRow = top.row;
            int topCol = top.col;
            int step = top.step;

            result[topRow][topCol] = step;

            for(int i=0; i<4; i++){
                int nRow = topRow + del[i][0];
                int nCol = topCol + del[i][1];

                if(isSafe(nRow,nCol,m,n,mat,visited)){
                    q.offer(new Tuple(nRow,nCol,step + 1));
                    visited[nRow][nCol] = true;
                }
            }
        }

        return result;
        
    }
}