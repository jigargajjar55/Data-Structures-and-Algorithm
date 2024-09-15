package BitManipulation;

public class XORQueriesofaSubarray_LC1310 {

    // Time: O(N + Q)
    // Space: O(N)
    public int[] xorQueries(int[] arr, int[][] queries) {

        int n = arr.length;
        int[] prefix = new int[n];

        /*
         * Intuition:
         * 
         * arr = [1,3,4,8]
         * prefix = [0, 0 ^ 1, 0 ^ 1 ^ 3, 0^1^3^4]
         * Query: left = 1, right = 3
         * 
         * We know prefix[right] : prefix[3] = (0 ^ 1 ^ 3 ^ 4)
         * prefix[left]: prefix[1] = (0 ^ 1)
         * 
         * //Even number of occurence in XOR will cancel out each other
         * 
         * If i am doing this: prefix[1] ^ prefix[3] ^ nums[3]
         * = (0 ^ 1) ^ (0 ^ 1 ^ 3 ^ 4) ^ (8)
         * = (3 ^ 4 ^ 8)
         * 
         * Using prefix, We can execute query in O(1) time
         * 
         */

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] ^ arr[i - 1];
        }

        int m = queries.length;
        int[] result = new int[m];

        for (int i = 0; i < m; i++) {

            int left = queries[i][0];
            int right = queries[i][1];

            result[i] = (prefix[left] ^ prefix[right] ^ arr[right]);
        }

        return result;

    }

}
