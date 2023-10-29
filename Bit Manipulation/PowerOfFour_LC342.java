
public class PowerOfFour_LC342 {


    //Time: O(log n){Base 2}
    //Space: O(1)
    public boolean isPowerOfFour(int n) {

        //Edge Case:
        if(n <= 0 || ((n & (n-1)) != 0)){
            return false;
        }

        //We will count no of division by 2,
        //If count is even, we can say it's power of 4
        //Otherwise not.
        int count = 0;

        while(n > 0){
            n >>>= 1;
            count++;
        }

        return (count & 1) != 0;

    }
    

    // Binary Search Approach
    // Time: O(log n)
    // Space: O(1)
    public boolean isPowerOfFour1(int n) {

        int start = 0;
        int end = n;

        while (start <= end) {

            int mid = start + ((end - start) / 2);
            long element = (long) Math.pow(4, mid);

            if (element == n) {
                return true;
            } else if (element > n) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return false;

    }
}
