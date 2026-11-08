class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.put(i, new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        for (int[] p : prerequisites) {
            int course = p[0];
            int pre = p[1];
            // pre -> course
            adjList.get(pre).add(course);
            indegree[course]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        List<Integer> order = new LinkedList<>();

        while (!q.isEmpty()) {
            Integer course = q.poll();
            order.add(course);

            for (int n : adjList.get(course)) {
                indegree[n]--;
                if (indegree[n] == 0) {
                    q.offer(n);
                }
            }
        }

        return order.size() == numCourses?order.stream().mapToInt(Integer::intValue).toArray() : new int[]{};
    }
}

