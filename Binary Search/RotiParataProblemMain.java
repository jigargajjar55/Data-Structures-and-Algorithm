import java.util.*;

public class RotiParataProblemMain
{
	public static void main(String[] args) {
		
		ArrayList<Integer> rankList = new ArrayList<Integer>();
		rankList.add(1);
		rankList.add(2);
		rankList.add(3);
		rankList.add(4);
		
		int numOfParata = 11;
		
		System.out.println("Minimum time to complete Parata: "+ minCookTime(rankList,numOfParata));
		
		
	}
	
    public static int minCookTime(ArrayList<Integer> rank, int m)
    {
        // Write your code here.
        int start = 0;
        int end = Integer.MAX_VALUE;
        int ans = -1;
        int mid = start + ((end-start)/2);
        
        while(start <= end){
            if(isPossible(rank,m,mid)){
                ans = mid;
                end = mid - 1;
            } else{
                start = mid + 1;
            }
            mid = start + ((end-start)/2);
        }
        return ans;
    }
    
    public static boolean isPossible(ArrayList<Integer> list,int m,int mid){
      int time = 0;
      int paratha = 0;
        for(int i = 0; i< list.size(); i++){
        	
             time = list.get(i);
             int j = 2;
             
            while(time <= mid){
            	
                paratha += 1;
                time = time + (list.get(i) * j);
                j++;
                
            }
            if(paratha >= m){
                return true;
            }
         }
       return false;
    
    }
}

