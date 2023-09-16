import java.util.*;

public class ReconstructItinerary_LC332 {

    /*
     * 
     * 
     * 1. DFS Recursive
     * Steps
     * Initialize a flightGraph as a dictionary (map) to represent flights and an
     * itinerary list to store the final travel sequence.
     * Iterate through each ticket and populate the flightGraph dictionary.
     * Sort the destinations for each airport in reverse order to visit lexical
     * smaller destinations first.
     * Start the DFS traversal from the JFK airport.
     * Using the depth-first search (DFS) method called dfs that takes an airport as
     * input and recursively explores its destinations while maintaining lexical
     * order. It adds the visited airports to the itinerary list.
     * Reverse the itinerary list to get the correct travel order.
     * Return the itinerary list as the final result.
     * 
     * Complexity
     * 
     * Time complexity: O(N^2log(N))
     * Since we loop over lists of destinations in the flight graph and sorts them.
     * Sorting each list has a time complexity of O(E * log(E)), where E is the
     * total number of edges (tickets). Since E can be at most N, this step has a
     * time complexity of O(N * log(N)). and since we loop over N city then the
     * total time complexity is O(N^2log(N)) where N is the number of airports.
     * 
     * Space complexity: O(N+E)O(N+E)O(N+E)
     * We are storing the Flight Graph which is represented using map of lists,
     * which will have at most N keys (airports) and a total of E values
     * (destinations). Therefore, the space complexity is O(N + E).
     * 
     * 
     * 
     * 
     */

    private void dfs(String place, Map<String, PriorityQueue<String>> map, LinkedList<String> result) {

        PriorityQueue<String> curr = map.get(place);

        if (curr != null && !curr.isEmpty()) {

            while (!curr.isEmpty()) {

                String nextPlace = curr.peek();
                curr.poll();
                dfs(nextPlace, map, result);
            }
        }
        result.addFirst(place);

    }

    public List<String> findItinerary(List<List<String>> tickets) {

        int n = tickets.size();
        Map<String, PriorityQueue<String>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String src = tickets.get(i).get(0);
            String dest = tickets.get(i).get(1);

            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<String>());
            }
            map.get(src).offer(dest);
        }

        LinkedList<String> result = new LinkedList<>();
        dfs("JFK", map, result);

        return result;
    }
}
