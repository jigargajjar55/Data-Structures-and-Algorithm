import java.util.*;


/*
Intuition
    If we can have k group,
    we need at least 1 + 2 + ... + k elements.


Explanation
    So we use A[i] to fill this pattern.

    If currently we have k group and x availble elements,
    we check if we can have the k + 1th group.
    If x >= k + 1, we can fullfill the pattern,
    and we can have the k + 1 group.
    If x < k + 1, we need to wait more element to come in.
 */

class Solution {
    public int maxIncreasingGroups(List<Integer> usageLimits) {

        Collections.sort(usageLimits);
        long total = 0, kGroup = 0;
        int n = usageLimits.size();

        for(int i=0; i<n; i++){

            total += usageLimits.get(i);

            if(total >= ((kGroup + 1) * (kGroup + 2)) / 2){
                kGroup++;
            }
        }

        return (int)kGroup;        
    }
}