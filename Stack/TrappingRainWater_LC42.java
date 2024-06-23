package Stack;
class Solution {

    private void getPrevMostGreaterValues(int n, int[] height, int[] prevMostGreater) {

        Stack<Integer> st = new Stack<>();
        // For edge case
        st.push(-1);

        for (int i = 0; i < n; i++) {

            int currHeight = height[i];

            while (st.size() > 1 && height[st.peek()] <= currHeight) {
                st.pop();
            }

            int index = -1;

            if (st.size() > 1) {

                index = st.peek();
            } else {
                st.push(i);
            }

            prevMostGreater[i] = index;
        }
    }

    private void getNextMostGreaterValues(int n, int[] height, int[] nextMostGreater) {

        Stack<Integer> st = new Stack<>();
        // For edge case
        st.push(-1);

        for (int i = n - 1; i >= 0; i--) {

            int currHeight = height[i];

            while (st.size() > 1 && height[st.peek()] <= currHeight) {
                st.pop();
            }

            int index = -1;

            if (st.size() > 1) {

                index = st.peek();
            } else {
                st.push(i);
            }

            nextMostGreater[i] = index;
        }
    }

    //Stack approach
    // Time: O(3 * N)
    // Space: O(2 * N)
    public int trap(int[] height) {

        int n = height.length;
        int[] prevMostGreater = new int[n];
        int[] nextMostGreater = new int[n];
        int result = 0;

        getPrevMostGreaterValues(n, height, prevMostGreater);
        getNextMostGreaterValues(n, height, nextMostGreater);

        for (int i = 0; i < n; i++) {

            int leftBarIndex = prevMostGreater[i];
            int rightBarIndex = nextMostGreater[i];

            if (leftBarIndex == -1 || rightBarIndex == -1) {
                continue;
            }

            int leftHeightBar = height[leftBarIndex];
            int rightHeightBar = height[rightBarIndex];

            result += Math.min(leftHeightBar, rightHeightBar) - height[i];

        }

        return result;
    }

    // Two Pointer's Approach
    // Time: O(N)
    // Space: O(1)
    public int trap(int[] height) {

        int n = height.length;
        int result = 0;

        int leftMaxHeight = 0;
        int rightMaxHeight = 0;

        int left = 0;
        int right = n - 1;

        while (left < right) {

            leftMaxHeight = Math.max(leftMaxHeight, height[left]);
            rightMaxHeight = Math.max(rightMaxHeight, height[right]);

            if (leftMaxHeight < rightMaxHeight) {
                result += leftMaxHeight - height[left];
                left++;
            } else {
                result += rightMaxHeight - height[right];
                right--;
            }
        }

        return result;

    }
}