class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        max_area=0;
        row = len(grid)
        col = len(grid[0])

        def dfs(r,c):
            if r<0 or c <0 or r>=row or c>=col or grid[r][c]==0:
                return 0
            grid[r][c] = 0

            return (1 + dfs(r+1,c) + dfs(r-1,c) + dfs(r,c+1) + dfs(r,c-1))
            
        for r in range(row):
            for c in range(col):
                if grid[r][c] == 1:
                  max_area = max( max_area, dfs(r,c))
        return max_area

        