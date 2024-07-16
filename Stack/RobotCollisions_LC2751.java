package Stack;

public class RobotCollisions_LC2751 {

    class Pair {
        int position;
        int index;

        Pair(int position, int index) {
            this.position = position;
            this.index = index;
        }
    }

    // https://www.youtube.com/watch?v=FMV5Pp0tdXY
    // Time: O(N * log N)
    // Space: O(2 * N)
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {

        int n = positions.length;
        List<Pair> robots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            robots.add(new Pair(positions[i], i));
        }

        // Sort it based on position
        Collections.sort(robots, (r1, r2) -> Integer.compare(r1.position, r2.position));
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            Pair currRobot = robots.get(i);
            char currDir = directions.charAt(currRobot.index);

            if (currDir == 'R') {
                st.push(currRobot.index);
            } else {

                while (!st.isEmpty() && healths[currRobot.index] > 0) {

                    int topIndex = st.pop();

                    if (healths[currRobot.index] > healths[topIndex]) {
                        healths[topIndex] = 0;
                        healths[currRobot.index] -= 1;
                    } else if (healths[currRobot.index] < healths[topIndex]) {
                        healths[topIndex] -= 1;
                        healths[currRobot.index] = 0;
                        st.push(topIndex);
                    } else {
                        healths[topIndex] = 0;
                        healths[currRobot.index] = 0;
                    }

                }
            }

        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (healths[i] > 0) {
                result.add(healths[i]);
            }
        }

        return result;

    }
}
