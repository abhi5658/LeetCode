class Solution {
  // dfs approach
  public void traverseIsland(char[][] grid, int row, int column, int maxRow, int maxColumn) {
    // out of bounds
    if (row > maxRow || row < 0 || column > maxColumn || column < 0) {
      return;
    }
    // already visited OR water detected
    if (grid[row][column] == '0') {
      return;
    }

    // marking traversed OR disappearing land :p
    grid[row][column] = '0';

    traverseIsland(grid, row, column - 1, maxRow, maxColumn); // left
    traverseIsland(grid, row, column + 1, maxRow, maxColumn); // right
    traverseIsland(grid, row - 1, column, maxRow, maxColumn); // top
    traverseIsland(grid, row + 1, column, maxRow, maxColumn); // bottom

    return;
  }

  public int numIslands(char[][] grid) {
    int islandCount = 0;
    for (int row = 0; row < grid.length; row++) {
      for (int column = 0; column < grid[row].length; column++) {
        if (grid[row][column] == '1') {
          islandCount += 1;
          traverseIsland(grid, row, column, grid.length - 1, grid[row].length - 1);
        }
      }
    }
    return islandCount;
  }
}