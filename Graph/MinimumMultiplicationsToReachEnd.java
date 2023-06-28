
import java.util.*;

class Pair23 {
    int step;
    int node;

    Pair23(int step, int node) {
        this.step = step;
        this.node = node;
    }
}

public class MinimumMultiplicationsToReachEnd {

    int minimumMultiplications(int[] arr, int start, int end) {

        int[] dist = new int[100000];
        for (int i = 0; i < 100000; i++) {
            dist[i] = (int) (1e9);
        }
        Queue<Pair23> q = new LinkedList<>();
        q.offer(new Pair23(0, start));
        dist[start] = 0;
        int mod = 100000;

        // Time : O(100000 * N)
        // space : O(100000)
        while (!q.isEmpty()) {
            Pair23 top = q.peek();
            q.poll();

            int step = top.step;
            int node = top.node;

            for (int i = 0; i < arr.length; i++) {
                int num = (arr[i] * node) % mod;
                if (step + 1 < dist[num]) {
                    dist[num] = step + 1;

                    if (num == end) {
                        return (step + 1);
                    }

                    q.offer(new Pair23(step + 1, num));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
