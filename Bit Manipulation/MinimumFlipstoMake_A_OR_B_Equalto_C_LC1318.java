class Solution {
    public int minFlips(int a, int b, int c) {

        int count = 0;

        for(int i=1; i<=32; i++){
            int bitA = (a >> i) & 1;
            int bitB = (b >> i) & 1;
            int bitC = (c >> i) & 1;

            if(bitC == 1){

                if(bitA == 0 && bitB == 0){
                    count += 1;
                }

            }else{

                if(bitA == 1 && bitB == 1){
                    count += 2;
                }else if(bitA == 1 && bitB == 0){
                    count += 1;
                }else if(bitA == 0 && bitB == 1){
                    count += 1;
                }

            }
        }

        return count;
        
    }
}