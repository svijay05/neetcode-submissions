class Solution {
    public void islandsAndTreasure(int[][] grid) {
        // Directions: up, down, left, right
        final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        final int INF = 2147483647;

        Queue<int[]> q = new LinkedList<>();

        int r = grid.length;
        int c = grid[0].length;

        boolean[][] visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0) {
                    //visited[i][j] = true; uncessary here
                    q.offer(new int[] {i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] ele = q.poll();
           // System.out.println("(" + ele[0] + "," + ele[1] + ")");
            // visited[ele[0]][ele[1]] = true;
            for (int i = 0; i < DIRECTIONS.length; i++) {
                int newRow = ele[0] + DIRECTIONS[i][0];
                int newCol = ele[1] + DIRECTIONS[i][1];
                if (newRow >= 0 && newRow < r && newCol >= 0 && newCol < c
                    && grid[newRow][newCol] == INF) {
                    //visited[newRow][newCol] = true;
                    //System.out.println(newRow + "  " + newCol);
                    grid[newRow][newCol] = grid[ele[0]][ele[1]] + 1;
                    q.offer(new int[] {newRow, newCol});
                }
            }
        }
    }
}
