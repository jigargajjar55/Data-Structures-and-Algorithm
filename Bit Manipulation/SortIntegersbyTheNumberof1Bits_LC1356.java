import java.util.*;

public class SortIntegersbyTheNumberof1Bits_LC1356 {
    

    
    //Time: O((N * log N) * log (num))
    //Space: O(N)
    private class Pair{
        int bitCount;
        int value;
        Pair(int bitCount,int value){
            this.bitCount = bitCount;
            this.value = value;
        }
    }
    private int getBitCount(int num){

        int count = 0;

        while(num > 0){

            if((num & 1) == 1){
                count++;
            }
            num >>= 1;
        }

        return count;

    }
    public int[] sortByBits(int[] arr) {

        int n = arr.length;
        int[] result = new int[n];

        PriorityQueue<Pair> pq = new PriorityQueue<>( (p1,p2) -> (p1.bitCount == p2.bitCount) ? 
                    Integer.compare(p1.value,p2.value) : Integer.compare(p1.bitCount, p2.bitCount)  );
        

        for(int i=0; i<n; i++){
            int currValue = arr[i];
            int bitCount = getBitCount(currValue);

            pq.offer(new Pair(bitCount,currValue));
        }

        int index = 0;
        while(!pq.isEmpty()){
            Pair top = pq.peek();
            pq.poll();

            int num = top.value;
            result[index++] = num;
        }

        return result;

        
    }
}