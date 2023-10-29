class FindLongestValidObstacleCourseatEachPositionLC1964 {

    // https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position/solutions/3494494/image-explanation-step-by-step-explanation-easy-c-java-solutions-easy-to-understand/

    // Time : O(N * log N), Space: O(N)
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {

        int n = obstacles.length;
        // stores the smallest ending number of an increasing subsequence
        int[] dp = new int[n];
        // stores the length of the longest increasing subsequence that includes each
        // obstacle
        int[] ans = new int[n];

        // length of the longest increasing subsequence seen so far
        int len = 0;

        for (int i = 0; i < n; i++) {

            // find the position where we can add the current obstacle
            int idx = binarySearch(dp, 0, len - 1, obstacles[i]);

            // add the current obstacle to the dp array at the correct position
            dp[idx] = obstacles[i];

            // if we added the current obstacle to the end of the dp array
            if (idx == len) {
                // update the length of the longest increasing subsequence seen so far
                len++;
            }

            // update the length of the longest increasing subsequence that includes the
            // i-th obstacle
            ans[i] = idx + 1;
        }

        return ans;
    }

    // binary search to find the position where we can add the current obstacle in
    // the dp array
    private int binarySearch(int[] dp, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (dp[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}