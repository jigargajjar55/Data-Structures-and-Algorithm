import java.util.*;

public class TheKWeakestRowsinaMatrix_LC1337 {
    

    

    private class Pair{
        int soldiers;
        int index;
        Pair(int soldiers, int index){
            this.soldiers = soldiers;
            this.index = index;
        }
    }
    private int getSoldierCount(int n,int[] arr){

        int start = 0;
        int end = n;

        while(start < end){
            int mid = start + ((end-start)/2);

            if(arr[mid] == 1){
                start = mid + 1;
            }else{
                end = mid;  
            }  
        }


        return start;

    }
    //Time: O((M * ( N + log (Max(M,K)))) + K * log M), {Linear way + Min-Heap}
    //Time: O((M * (log N + log (Max(M,K)))) + K * log M), {Binary search + Min-Heap}
    //Space: O(M){Min-Heap}
    public int[] kWeakestRows(int[][] mat, int k) {

        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[k];
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1,p2) -> p1.soldiers == p2.soldiers ? Integer.compare(p1.index,p2.index) : Integer.compare(p1.soldiers, p2.soldiers));

        for(int i=0; i<m; i++){
            int soldiersCount = getSoldierCount(n,mat[i]);

            //System.out.println(i +" -> " + soldiersCount);

            // for(int j=0; j<n; j++){
            //     if(mat[i][j] == 1){
            //         soldiersCount++;
            //     }else{
            //         break;
            //     }
            // }

            pq.offer(new Pair(soldiersCount, i));
            
        }

        int index = 0;
        while(!pq.isEmpty() && k > 0){
            int topIndex = pq.peek().index;
            pq.poll();

            result[index] = topIndex;
            index++;
            k--;
        }

        return result;


        
    }
}