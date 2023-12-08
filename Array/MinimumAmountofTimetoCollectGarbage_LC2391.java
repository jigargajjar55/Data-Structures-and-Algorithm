package Array;
import java.util.*;

public class MinimumAmountofTimetoCollectGarbage_LC2391 {
    


    //Time: O(5 * G)
    //Space: O(1)

    public int garbageCollection(String[] garbage, int[] travel) {

        int n = garbage.length;
        int result = 0;

        for(int i=0; i<travel.length; i++){
            result += (3 * travel[i]);
        }
        for(String str : garbage){
            result += str.length();
        }

        //For checking M
        for(int i=n-1; i>0; i--){

            if(garbage[i].indexOf('M') < 0){
                result -= travel[i-1];
            }else{
                break;
            }
        }

        //For checking G
        for(int i=n-1; i>0; i--){

            if(garbage[i].indexOf('G') < 0){
                result -= travel[i-1];
            }else{
                break;
            }
        }

        //For checking M
        for(int i=n-1; i>0; i--){

            if(garbage[i].indexOf('P') < 0){
                result -= travel[i-1];
            }else{
                break;
            }
        }

        return result;

    }






    //Bruteforce Approach
    //Time: O((N * G) + N)
    //Space: O(N * 3){For List of HashMaps}
    public int garbageCollection1(String[] garbage, int[] travel) {

        int n = garbage.length;
        List<Map<Character,Integer>> freq = new ArrayList<>();
        int totalM = 0;
        int totalP = 0;
        int totalG = 0;

        for(int i=0; i<n; i++){

            freq.add(new HashMap<>());

            for(int j=0; j<garbage[i].length(); j++){

                char currCh = garbage[i].charAt(j);
                if(currCh == 'M'){
                    totalM++;
                }else if(currCh == 'P'){
                    totalP++;
                }else{
                    totalG++;
                }

                freq.get(i).put(currCh, freq.get(i).getOrDefault(currCh, 0) + 1);
            }
        }

        int result = 0;

        for(int i=0; i<n; i++){   

            //System.out.println("Total M: " + totalM +", Total P: "+ totalP+", Total G:"+totalG);        

            if(totalM > 0 && freq.get(i).containsKey('M')){
                int count = freq.get(i).get('M');
                result += count;
                totalM -= count;
            }

            if(i < n-1 && totalM > 0){
                result += travel[i];
            }
            
            if(totalP > 0 && freq.get(i).containsKey('P')){
                int count = freq.get(i).get('P');
                result += count;
                totalP -= count;
            }
            if(i < n-1 && totalP > 0){
                result += travel[i];
            }
            
            if(totalG > 0 && freq.get(i).containsKey('G')){
                int count = freq.get(i).get('G');
                result += count;
                totalG -= count;
            }

            if(i < n-1 && totalG > 0){
                result += travel[i];
            }
        }

        return result;
        
    }
}