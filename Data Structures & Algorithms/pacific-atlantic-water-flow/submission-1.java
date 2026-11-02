class Solution {
    /**

Define Ocean Borders:Pacific Ocean: Touches the top row ($r = 0$) and left column ($c = 0$).

Atlantic Ocean: Touches the bottom row ($r = M - 1$) and right column ($c = N - 1$).

Run Traversal for Pacific:Create a pacific set to store all coordinates reachable from the
Pacific.Run DFS/BFS starting from all cells on the top row and left column.


Run Traversal for Atlantic:Create an atlantic set to store all coordinates reachable from the
Atlantic.Run DFS/BFS starting from all cells on the bottom row and right column.


Find the Intersection:Any cell that exists in both the pacific set and the atlantic set can flow to
both oceans. Return these coordinates.




    *
*/

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int r = heights.length;
        int c = heights[0].length;
        Set<List<Integer>> pacific = new HashSet<>();
        Set<List<Integer>> atlantic = new HashSet<>();

        for (int i = 0; i < r; i++) {
            dfs(i, 0, heights, pacific, 0);
        }
        for (int j = 0; j < c; j++) {
            dfs(0, j, heights, pacific, 0);
        }
        // atlantic
        for (int i = 0; i < r; i++) {
            dfs(i, c - 1, heights, atlantic, 0);
        }
        for (int j = 0; j < c; j++) {
            dfs(r - 1, j, heights, atlantic, 0);
        }

        Set<List<Integer>> intersection = new HashSet<>(pacific);
        intersection.retainAll(atlantic);

        List<List<Integer>> result = new ArrayList<>(intersection);

        return result;
    }

    void dfs(int row, int col, int[][] heights, Set<List<Integer>> visited, int prevHeight) {
        final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int r = heights.length;
        int c = heights[0].length;

        if (row >= 0 && row < r && col >= 0 && col < c && heights[row][col] >= prevHeight
            && !visited.contains(List.of(row, col))) {
            visited.add(List.of(row, col));
            for (int i = 0; i < DIRECTIONS.length; i++) {
                int newRow = row + DIRECTIONS[i][0];
                int newCol = col + DIRECTIONS[i][1];
                dfs(newRow, newCol, heights, visited, heights[row][col]);
            }
        }
        return;
    }
}
