class Solution {
    /**
Start at the 4 borders of the matrix and find all 'O's.

Use DFS or BFS to traverse and mark every 'O' connected to those border 'O's as "safe" (e.g.,
temporarily rename them to 'E' for escaped).

Once you've marked all border-connected 'O's, do a single pass over the whole matrix:

Any remaining 'O's (the ones that never touched the border) are truly surrounded
→ flip them to 'X'.

Any 'E's (the safe ones)
→ flip them back to 'O'.


    *
*/
    public void solve(char[][] board) {
        int r = board.length;
        int c = board[0].length;

        boolean[][] visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0, visited);
            }
            if (board[i][c - 1] == 'O') {
                dfs(board, i, c-1, visited);
            }
        }

        for (int j = 0; j < c; j++) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j, visited);
            }
            if (board[r - 1][j] == 'O') {
                dfs(board, r-1, j, visited);
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
               // System.out.print(board[i][j] + " ");
                if (board[i][j] == 'E') {
                    board[i][j] = 'O';
                }
                else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
           // System.out.println();
        }
    }

    void dfs(char[][] board, int i, int j, boolean[][] visited) {
        int r = board.length;
        int c = board[0].length;
        int[][] DIRECTIONS = {{1, 0}, {0,1}, {-1,0}, {0,-1}};

        if (i >= 0 && j >= 0 && i < r && j < c && !visited[i][j] && board[i][j] == 'O') {
            System.out.println("matched");
            visited[i][j] = true;
            board[i][j] = 'E';
            for (int[] dir : DIRECTIONS) {
                dfs(board, i + dir[0], j + dir[1], visited);
            }
        }

        return;
    }
}
