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
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int r = heights.length;
        int c = heights[0].length;
        boolean[][] pacific = new boolean[r][c];
        boolean[][] atlantic = new boolean[r][c];

        // 1. DFS from Pacific borders (Top row and Left column)
        for (int i = 0; i < r; i++) {
            dfs(i, 0, heights, pacific);
        }
        for (int j = 0; j < c; j++) {
            dfs(0, j, heights, pacific);
        }

        // 2. DFS from Atlantic borders (Bottom row and Right column)
        for (int i = 0; i < r; i++) {
            dfs(i, c - 1, heights, atlantic);
        }
        for (int j = 0; j < c; j++) {
            dfs(r - 1, j, heights, atlantic);
        }

        // 3. Find cells that can reach both oceans
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int row, int col, int[][] heights, boolean[][] visited) {
        visited[row][col] = true;
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int r = heights.length;
        int c = heights[0].length;

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check boundaries, whether already visited, and 
            // if water can flow from current cell to the neighbor (neighbor >= current)
            if (newRow >= 0 && newRow < r && newCol >= 0 && newCol < c 
                && !visited[newRow][newCol] 
                && heights[newRow][newCol] >= heights[row][col]) {
                dfs(newRow, newCol, heights, visited);
            }
        }
    }

}
