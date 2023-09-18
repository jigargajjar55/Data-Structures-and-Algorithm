import java.util.*;

public class CountKSubsequencesOfStringWithMaximumBeauty_LC2842 {
    

    private long multiplyMod(long a,long b){
        return ((a % 1_000_000_007) * (b % 1_000_000_007)) % 1_000_000_007;
    }

    private long additionMod(long a,long b){
         return ((a % 1_000_000_007) + (b % 1_000_000_007)) % 1_000_000_007;
    }
    //Time: O(Exponential)
    //Space: O((2 * U) + U){For freq hashmap, array and Aux. stack space}
    private long solveByRecursion(int index,int n,int k,int maxi,Integer[] chars) {
        
        //Base Case
        if(k == 0){
            if(maxi == 0){
                return 1;
            }else{
                return 0;
            }
           
        }
        if(index >= n){
            return 0;
        }
        
        
        long exclude = solveByRecursion(index+1,n,k,maxi,chars);
        long include = multiplyMod((long)chars[index], solveByRecursion(index+1,n,k-1,maxi - chars[index],chars));  
        
        long ans = additionMod(exclude, include);
        return ans;        
        
    }
     
    //Time: O((N + U + (U * log U) + K + (U * k * maxi))){N: String length, U: Unique Charcter of string, maxi: maximum occurence of char}
    //Space: O((2 * U) + U + (U * k * maxi)){For freq hashmap, array and Aux. stack space}
    private long solveByTopDownDP(int index,int n,int k,int maxi,Integer[] chars,Map<String, Long> dp) {
        
        //Base Case
        if(k == 0){
            if(maxi == 0){
                return 1;
            }else{
                return 0;
            }
           
        }
        if(index >= n){
            return 0;
        }

        String curr = index +", "+ k + ", "+ maxi;
        if(dp.containsKey(curr)){
            return dp.get(curr);
        }
        
        
        long exclude = solveByTopDownDP(index+1,n,k,maxi,chars,dp);
        long include = multiplyMod((long)chars[index], solveByTopDownDP(index+1,n,k-1,maxi - chars[index],chars,dp));  
        
        long ans = additionMod(exclude, include);
        dp.put(curr,ans);

        return ans;        
        
    }
    
    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        
        Map<Character, Integer> freq = new HashMap<>();
        int n = s.length();
        
        for(int i=0; i<n; i++){
            char ch = s.charAt(i);  
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        int size = freq.size();
        Integer[] chars = new Integer[size];
        int index = 0;

        for(Character key : freq.keySet()){
            chars[index] = freq.get(key);
            index++;
        }

        Arrays.sort(chars, Collections.reverseOrder());

        int maxi = 0;
        for(int i=0; i<size; i++){
            if(i<k){
                maxi += chars[i];
            }else{
                break;
            }
        }
  
        //return solveByRecursion(0,size,k,maxi,chars);

        Map<String, Long> dp = new HashMap<>();
        return (int)solveByTopDownDP(0,size,k,maxi,chars,dp);
        
    }
}
