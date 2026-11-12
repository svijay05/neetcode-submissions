class Solution {
    // Union-Find (Disjoint Set Union)
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        int[] parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] e : edges) {
            int rootU = find(parent, e[0]);
            int rootV = find(parent, e[1]);

            if (rootU == rootV) {
                return e;
            }

            parent[rootU] = rootV;
        }

        return new int[] {};
    }
    int find(int[] parent, int node) {
        if (parent[node] == node) {
            return node;
        }

        parent[node] = find(parent, parent[node]);

        return parent[node];
    }
}
