public class KthSymbolInGrammar_LC779 {
    

    
    //Time: O(N)
    //Space: O(N){Aux. Stack Space}
    private int solveByRescursion(int n,int k){

        //Base Case
        if(n == 1){
            return 0;
        }


        int parent = solveByRescursion(n-1,(k+1)/2);
        if((k & 1) == 1){

            return parent == 0 ? 0 : 1;

        }else{

            return parent == 0 ? 1 : 0;

        }

    }
    public int kthGrammar(int n, int k) {

        return solveByRescursion(n,k);
        
    }
}
