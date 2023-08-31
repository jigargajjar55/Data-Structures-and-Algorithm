public class MinimumPenaltyforShop_LC2483 {
    

    //Time: O(2 * N), Space:O(N){Suffix Array}
    public int bestClosingTime(String customers) {

        int n = customers.length();
        int[] suffix = new int[n+1];               

        int penalty = 0;
        for(int i=0; i<n; i++){

            if(customers.charAt(i) == 'N'){
                penalty++;
            }
        }
        suffix[n] = penalty;

        int ansHour = n;
        int minPenalty = penalty;

        for(int closedHour = n-1; closedHour >= 0; closedHour--){
            if(customers.charAt(closedHour) == 'Y'){
                suffix[closedHour] = suffix[closedHour+1] + 1;
            }else{
                suffix[closedHour] = suffix[closedHour+1] - 1;
            }
            if(suffix[closedHour] <= minPenalty){
                minPenalty = suffix[closedHour];
                ansHour = closedHour;
            }
        }

        return ansHour;
        
    }
}