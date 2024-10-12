package Patterns.Intervals;

public class RemoveCoveredIntervals_LC1288 {


    //Using O(1) Constant space approach
    //Time: O((M * log M) + M)
    //Space: O(1)
    public int removeCoveredIntervals(int[][] intervals) {
        
        int m = intervals.length;
        Arrays.sort(intervals, (i1,i2) -> (i1[0] == i2[0])
                    ? Integer.compare(i2[1],i1[1])
                    : Integer.compare(i1[0], i2[0]));

        
        int[] top = intervals[0];
        int count = 1;

        for(int i=1; i<m; i++){

            int[] curr = intervals[i];           

            if(top[1] < curr[1]){
                count++;
                top = curr;
            }
        }

        return count;
        
    }
    

    //Using Stack Approach
    //Time: O((M * log M) + M)
    //Space: O(M)
    public int removeCoveredIntervals1(int[][] intervals) {
        
        int m = intervals.length;
        Arrays.sort(intervals, (i1,i2) -> (i1[0] == i2[0])
                    ? Integer.compare(i2[1],i1[1])
                    : Integer.compare(i1[0], i2[0]));

        Stack<int[]> st = new Stack<>();
        st.push(intervals[0]);

        for(int i=1; i<m; i++){

            int[] curr = intervals[i];
            int[] top = st.peek();

            if(top[1] < curr[1]){
                st.push(curr);
            }
        }

        return st.size();
        
    }
}
