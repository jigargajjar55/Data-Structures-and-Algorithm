package Strings;

public class RemoveColoredPiecesIfBothNeighborsAreTheSameColor_LC2038 {
    
    // https://leetcode.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/solutions/1524153/c-python-java-count-aaa-and-bbb/?envType=daily-question&envId=2023-10-02
    
    //Time: O(N),
    //Space: O(1)
    public boolean winnerOfGame(String colors) {

        int n = colors.length();

        int countA = 0;
        int countB = 0;

        for(int i=2; i<n; i++){

            if(colors.charAt(i-2) == colors.charAt(i-1) && colors.charAt(i-1) == colors.charAt(i)){

                if(colors.charAt(i) == 'A'){
                    countA++;
                }else{
                    countB++;
                }
            }
        }
        
        System.out.println("Count Of AAA: " + countA);
        System.out.println("Count Of BBB: " + countB);


        if(countA > countB){
            return true;
        }else{
            return false;
        }
        
    }
}