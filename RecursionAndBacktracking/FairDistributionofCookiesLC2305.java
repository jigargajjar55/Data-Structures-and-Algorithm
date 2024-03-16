package RecursionAndBacktracking;
public class FairDistributionofCookiesLC2305 {

    // Time: O(K ^ N), Space: O(K + N){K: no. Of Child, N: Cookies array length}
    private int solveByRecursion(int index, int n, int[] cookies, int k, int[] child) {

        // Base Case
        if (index >= n) {
            int maxi = -(int) (1e9);
            for (int childCookie : child) {
                if (maxi < childCookie) {
                    maxi = childCookie;
                }
            }
            return maxi;
        }

        int mini = (int) (1e9);
        for (int i = 0; i < k; i++) {
            child[i] += cookies[index];
            int ans = solveByRecursion(index + 1, n, cookies, k, child);
            if (mini > ans) {
                mini = ans;
            }
            child[i] -= cookies[index];
        }

        return mini;

    }

    public int distributeCookies(int[] cookies, int k) {

        int[] child = new int[k];
        int n = cookies.length;

        return solveByRecursion(0, n, cookies, k, child);

    }
}