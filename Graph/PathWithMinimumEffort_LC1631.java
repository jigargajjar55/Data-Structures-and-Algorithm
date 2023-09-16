import java.util.*;

public class PathWithMinimumEffort_LC1631 {
    
    //Applying Dijkstra's Algorithm
    //Time: O((V) + (V * log V)) {V = M * N}
    //Space: O(2V)
    private class Tuple{
        int row;
        int col;
        int diff;
        Tuple(int row,int col,int diff){
            this.row = row;
            this.col = col;
            this.diff = diff;
        }
    }
    
    private boolean isSafe(int row,int col,int m,int n){

        if((row >= 0 && row < m) && (col >= 0 && col < n)){
            return true;
        }else{
            return false;
        }

    }
    
    public int minimumEffortPath(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;
        int[][] del = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

        int[][] efforts = new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                efforts[i][j] = (int)(1e9);
            }
        }

        PriorityQueue<Tuple> pq = new PriorityQueue<>((p1,p2) -> Integer.compare(p1.diff, p2.diff));
        pq.offer(new Tuple(0,0,0));

        int minEffort = 0;

        while(!pq.isEmpty()){
            Tuple top = pq.peek();
            pq.poll();

            int row = top.row;
            int col = top.col;
            int diff = top.diff;

            if(row == m-1 && col == n-1){
                minEffort = diff;
                break;
            }

            for(int i=0; i<4; i++){
                int nRow = row + del[i][0];
                int nCol = col + del[i][1];
                
                if(isSafe(nRow,nCol,m,n)){

                    int newDiff = Math.max(Math.abs(heights[nRow][nCol] - heights[row][col]),diff);

                    if(newDiff < efforts[nRow][nCol]){
                        efforts[nRow][nCol] = newDiff;
                        pq.offer(new Tuple(nRow,nCol,newDiff));
                    }
                }
            }
        }

        return minEffort;

        
    }
}