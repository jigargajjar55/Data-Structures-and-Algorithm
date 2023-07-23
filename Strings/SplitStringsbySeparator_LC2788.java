package Strings;
import java.util.*;

public class SplitStringsbySeparator_LC2788 {
    
    //Time: O(N * M), Space: O(M)
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        
        int n = words.size();
        
        List<String> result = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            
            String curr = words.get(i);            
            
            String[] splitArray = curr.split("\\Q" + separator + "\\E");
            
            
            for(String str : splitArray){
                
                //System.out.println(str);
                
                if(!str.isEmpty()){
                    
                    result.add(str);                

                }

            }
        }
        
        
        return result;
        
        
        
    }
}