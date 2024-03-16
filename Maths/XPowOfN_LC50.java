package Maths;
public class XPowOfN_LC50 {
    //Pow(X, N)
    //Time: O(log N), Space: O(log N){Aux. Stack Space}
    private double getPower(double a, int b){

        //Base Case
        if(b == 0){
            return 1;
        }
        if(b == 1){
            return a;
        }

        double ans = getPower(a, b/2);

        if((b & 1) == 1){
            return (a * ans * ans);
        }else{
            return (ans * ans);
        }
    }
    private double getPowerItr(double a, int b){

        double ans = 1;

        while(b != 0){

            if((b & 1) != 0){
                ans *= a;
            }

            a *= a;

            b >>>= 1;
        }
        return ans;

    }
    public double myPow(double x, int n) {

       // double ans = getPower(x, Math.abs(n));

        double ans = getPowerItr(x, Math.abs(n));

        if(n < 0){
            return (1/ans);
        }else{
            return ans;
        }
        
    }
}