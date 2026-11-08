class Solution {
    // if the graph is DAG - all nodes we can visit - then we can finish
    // 1: Kahn's Algorithm (BFS Topological Sort)
    //  1. Build the adjacency list and in-degree array
    // prerequisites[i] = [a, b] means b -> a (must take b before a) inDegree of a is one here
    // 2. Enqueue all courses with an in-degree of 0 (no prerequisites)
    // 3. Process courses in topological order
    // If all prerequisites for 'neighbor' are satisfied, add to queue

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.put(i, new ArrayList<>());
        }

        int[] indegree = new int[numCourses];
        int visited = 0;

        for (int[] p : prerequisites) {
            int course = p[0];
            int pre = p[1];
            // pre -> course
            adjList.get(pre).add(course);
            indegree[course]++;
        }

        Queue<List<Integer>> q = new LinkedList<>();

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.offer(adjList.get(i));
            }
        }

        while (!q.isEmpty()) {
            List<Integer> neighbour = q.poll();
            visited ++;

            for (int n : neighbour) {
                indegree[n]--;
                if(indegree[n]==0){
                    q.offer(adjList.get(n));
                }

            }
        }

        return visited == numCourses ;
    }
}
