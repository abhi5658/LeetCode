class Solution {
  // base case 0,0=1
  // possible ways to reach a position = total(up) + total(left)
  // i | 0 | 1 | 2 | 3 | 4 | 5 | 6
  // 0 |(1)| 1 | 1 | 1 | 1 | 1 | 1
  // 1 | 1 | 2 | 3 | 4 | 5 | 6 | 7
  // 2 | 1 | 3 | 6 | 10| 15| 21| 28
  public int uniquePaths(int m, int n) {
    int[][] grid = new int[m][n];
    grid[0][0] = 1;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 && j == 0)
          continue;
        if (i == 0) {
          grid[i][j] = grid[i][j - 1];
        } else if (j == 0) {
          grid[i][j] = grid[i - 1][j];
        } else {
          grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
        }
      }
    }
    return grid[m - 1][n - 1];
  }
}