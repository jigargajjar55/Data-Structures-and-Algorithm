package RecursionAndBacktracking;
class MaximumNumberOfAchievableTransferRequestsLC1601 {
    private boolean isNetChangeZero(int n, int[] transeferWindow) {
        boolean isCheck = true;
        for (int i = 0; i < n; i++) {
            if (transeferWindow[i] != 0) {
                isCheck = false;
                break;
            }
        }
        return isCheck;
    }

    // Time: O((2 ^ No. Of Requests) * N){N: no. Of Buildings}, Space:O(N + N){Aux.
    // Stack Space and 1D Array}
    private int solveByRecursion(int index, int count, int n, int[][] requests, int[] transeferWindow) {

        // Base Case
        if (index >= n) {
            if (isNetChangeZero(transeferWindow.length, transeferWindow)) {
                return count;
            } else {
                return 0;
            }
        }

        int exclude = solveByRecursion(index + 1, count, n, requests, transeferWindow);
        int from = requests[index][0];
        int to = requests[index][1];
        transeferWindow[from]--;
        transeferWindow[to]++;
        int include = solveByRecursion(index + 1, count + 1, n, requests, transeferWindow);
        transeferWindow[from]++;
        transeferWindow[to]--;

        int ans = Math.max(exclude, include);

        return ans;
    }

    public int maximumRequests(int n, int[][] requests) {

        int[] transeferWindow = new int[n];
        int reqLength = requests.length;

        int achievableReq = solveByRecursion(0, 0, reqLength, requests, transeferWindow);

        return achievableReq;
    }
}