package DynamicProgramming.TwoDimensional;

public class FillingBookcaseShelves_LC1105 {
    

    
    //Time: O(Exponential)
    //Space: O(M)
    private int solveByRecursion(int index,int shelfWidth,int m,int[][] books){

        //Base Case
        if(index >= m){
            return 0;
        }

        int maxHeight = 0;
        int currWidth = shelfWidth;
        int result = Integer.MAX_VALUE;

        for(int j=index; j<m; j++){

            int currBookWidth = books[j][0];
            int currBookHeight = books[j][1];

            if(currWidth < currBookWidth){
                break;
            }

            maxHeight = Math.max(maxHeight, currBookHeight);
            currWidth -= currBookWidth;

            int ans = solveByRecursion(j+1,shelfWidth,m,books);

            result = Math.min(result, maxHeight + ans);
        }

        return result;        

    }

    //Time: O(M * shelfWidth)
    //Space: O(2 * M){Aux. Stack space and 1D DP Array}
    private int solveByTopDownDP(int index,int shelfWidth,int m,int[][] books, Integer[] dp){

        //Base Case
        if(index >= m){
            return 0;
        }

        //Overlapping sub-problem
        if(dp[index] != null){
            return dp[index];
        }

        int maxHeight = 0;
        int currWidth = shelfWidth;
        int result = Integer.MAX_VALUE;

        for(int j=index; j<m; j++){

            int currBookWidth = books[j][0];
            int currBookHeight = books[j][1];

            if(currWidth < currBookWidth){
                break;
            }

            maxHeight = Math.max(maxHeight, currBookHeight);
            currWidth -= currBookWidth;

            int ans = solveByTopDownDP(j+1,shelfWidth,m,books,dp);

            result = Math.min(result, maxHeight + ans);
        }

        return dp[index] = result;        

    }
    public int minHeightShelves(int[][] books, int shelfWidth) {

        int m = books.length;
        // int result = solveByRecursion(0,shelfWidth,m,books);

        Integer[] dp = new Integer[m];
        int result = solveByTopDownDP(0,shelfWidth,m,books,dp);

        return result;
        
    }
}
