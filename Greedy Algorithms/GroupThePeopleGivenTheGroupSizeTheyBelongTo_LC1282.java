import java.util.*;

class Solution {
    //Time: O(N + (N * log N) + N), Space: O(N)
    public List<List<Integer>> groupThePeople(int[] groupSizes) {

        int n = groupSizes.length;
        List<List<Integer>> result = new ArrayList<>();

        int[][] groupSizeWithIndex = new int[n][2];
        for(int i=0; i<n; i++){
            groupSizeWithIndex[i][0] = groupSizes[i];
            groupSizeWithIndex[i][1] = i;
        }

        //Sort based on size of the group
        Arrays.sort(groupSizeWithIndex, (g1,g2) -> Integer.compare(g1[0], g2[0]));       

        
        int prevGroupSize = 0;
        for(int i=0; i<n; i++){
            int currGroupSize = groupSizeWithIndex[i][0];
            int peopleID = groupSizeWithIndex[i][1];
            
            int listSize = result.size();
            if(listSize == 0){

                List<Integer> list1 = new ArrayList<>();
                result.add(list1);
                prevGroupSize = currGroupSize;
                listSize++;


            }else{

                if(prevGroupSize != currGroupSize){

                    List<Integer> list1 = new ArrayList<>();
                    result.add(list1);
                    prevGroupSize = currGroupSize;
                    listSize++;
                }else{

                    if(result.get(listSize-1).size() == currGroupSize){

                        List<Integer> list1 = new ArrayList<>();
                        result.add(list1);
                        prevGroupSize = currGroupSize;
                        listSize++;

                    }
                }
            }

            result.get(listSize - 1).add(peopleID);
        }
        

        return result;
    }
}