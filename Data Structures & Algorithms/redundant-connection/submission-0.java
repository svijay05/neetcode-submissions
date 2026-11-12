class Solution {
    //Union-Find (Disjoint Set Union)
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        // Nodes are 1-indexed (1 to n), so allocate n + 1
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int rootU = find(parent, edge[0]);
            int rootV = find(parent, edge[1]);

            // Both nodes already share the same root -> this edge forms the cycle
            if (rootU == rootV) {
                return edge;
            }

            // Merge the two components
            parent[rootU] = rootV;
        }

        return new int[]{};
        
    }

    private int find(int[] parent, int node) {
        if (parent[node] == node) {
            return node;  // always return root node
        }
        // Path compression
        return parent[node] = find(parent, parent[node]);
    }
}
