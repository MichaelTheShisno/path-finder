package core;

/**
 * Class that encapsulates the layout of the nodes it holds.
 */
public class Grid {
    private Node[][] grid;

    public Grid(final int ROW_COUNT, final int COL_COUNT) {
        grid = new Node[ROW_COUNT][COL_COUNT];
        this.buildNodes();
    }

    private void buildNodes() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = new Node(row, col, true);
            }
        }
    }

    public Node getNodeAt(int row, int col) {
        return grid[row][col];
    }

    private boolean isInside(int row, int col) {
        return (0 <= row && row < grid.length && 0 <= col && col < grid[0].length);
    }

    private boolean isWalkable(int row, int col) {
        return isInside(row, col) && grid[row][col].isWalkable();
    }

    private void setWalkableAt(int row, int col, boolean walkable) {
        this.grid[row][col].setWalkable(walkable);
    }
}
