class Solution {
    // backtracking dfs is another option
    private final Map<String, PriorityQueue<String>> graph = new HashMap<>();
    private final LinkedList<String> route = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // Build adjacency list using min-heaps to maintain lexicographical order
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).offer(ticket.get(1));
        }

        dfs("JFK");
        return route;
    }

    private void dfs(String airport) {
        PriorityQueue<String> destinations = graph.get(airport);
        while (destinations != null && !destinations.isEmpty()) {
            dfs(destinations.poll());
        }
        // Prepending during post-order unwind avoids reversing the list at the end
        route.addFirst(airport);
    }
}
