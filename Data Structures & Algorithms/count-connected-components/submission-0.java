class Solution {
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int f = edges[i][0];
            int s = edges[i][1];
            graph.get(f).add(s);
            graph.get(s).add(f); // we need to add both ways
        }

        boolean[] visited = new boolean[n];

        Queue<Integer> q = new LinkedList<>();

        int connComponent=0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                q.offer(i);
                visited[i] = true;
                while (!q.isEmpty()) {
                    Integer node = q.poll();

                    for (int neigh : graph.get(node)) {
                        if (!visited[neigh]) {
                            visited[neigh] = true;
                            q.offer(neigh);
                        }
                    }
                }

                connComponent++;
            }
        }

        return connComponent;
    }
}
