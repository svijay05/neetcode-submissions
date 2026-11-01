class Solution {
    public int orangesRotting(int[][] grid) {
        // Directions: up, down, left, right
        final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        final int ROT = 2;
        final int FRESH = 1;
        final int EMPTY = 0;

        Queue<int[]> q = new LinkedList<>();

        int r = grid.length;
        int c = grid[0].length;

        boolean[][] visited = new boolean[r][c];
        int freshCount = 0;
        int minutes = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == ROT) {
                    // visited[i][j] = true; uncessary here
                    q.offer(new int[] {i, j});
                }
                if (grid[i][j] == FRESH) {
                    // visited[i][j] = true; uncessary here
                    freshCount++;
                }
            }
        }
    if (freshCount == 0) return 0;

        while (!q.isEmpty()) {
            int size = q.size(); // do bfs in batches
          boolean rottedThisMinute = false;
            for (int k = 0; k < size; k++) {
                int[] ele = q.poll();
                for (int i = 0; i < DIRECTIONS.length; i++) {
                    int newRow = ele[0] + DIRECTIONS[i][0];
                    int newCol = ele[1] + DIRECTIONS[i][1];
                    if (newRow >= 0 && newRow < r && newCol >= 0 && newCol < c
                        && grid[newRow][newCol] == FRESH) {
                        grid[newRow][newCol] = ROT;
                        freshCount--;
                        q.offer(new int[] {newRow, newCol});
                        rottedThisMinute=true;
                    }
                }
            }
            if(rottedThisMinute)
                 minutes++;
        }

        if (freshCount == 0) {
            return minutes;
        } else {
            return -1;
        }
    }
}
