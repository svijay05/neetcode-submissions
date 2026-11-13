class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < times.length; i++) {
            int f = times[i][0];
            int s = times[i][1];
            int t = times[i][2];

            graph.get(f).add(new int[] {s, t});
        }

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        pq.add(new int[] {k, 0});

        while (!pq.isEmpty()) {
            int[] u = pq.poll();

            if (u[1] > distance[u[0]]) {
                continue;
            }

            for (int[] neigh : graph.get(u[0])) {
                if (distance[neigh[0]] > neigh[1] + u[1]) {
                    distance[neigh[0]] = neigh[1] + u[1];

                    pq.add(new int[] {neigh[0], distance[neigh[0]]});
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            }

            max = Math.max(distance[i], max);
        }

        return max;
    }
}
