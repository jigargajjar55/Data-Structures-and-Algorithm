public class MakeCostsofPathsEqualinBinaryTreeLC2673 {

    /*
     * Solution: Bottom Up
     * 
     * Bottom up iterate the whole tree.
     * For each node i, compare its two children left and right.
     * The smaller child needs to catch up the bigger child,
     * so we increment res += abs(A[left] - A[right]),
     * then we update A[i] += max(A[left], A[right],
     * and A[i] present minimum cost from node i to any leaf.
     * 
     * We continue iterate the whole tree and finally return result res
     */

    // Time: O(N), Space: O(N)
    public int minIncrements(int n, int[] cost) {

        int[] costArray = new int[n];
        for (int i = 0; i < n; i++) {
            costArray[i] = cost[i];
        }

        int result = 0;

        for (int i = (n / 2) - 1; i >= 0; i--) {
            int leftChild = (2 * i) + 1;
            int rightChild = (2 * i) + 2;

            result += Math.abs(costArray[leftChild] - costArray[rightChild]);

            costArray[i] += Math.max(costArray[leftChild], costArray[rightChild]);
        }

        return result;

    }
}
