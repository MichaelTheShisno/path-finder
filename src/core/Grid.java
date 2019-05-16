package core;

import java.util.Set;
import java.util.HashSet;

/**
 * Class that encapsulates the layout of the nodes it holds.
 */
public class Grid {
    private Node[][] grid;
    private final static int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private final static int[][] diagonals = {{1, -1}, {1, 1}, {-1, 1}, {-1, -1}};

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

    /**
     * Get a set of all walkable immediate neighbors in the orthogonal direction.
     * If diagonal nodes are desired, include those too.
     * @param node The node whose neighbors we are getting.
     * @param isDiagonal Determines whether diagonal neighbors are allowed in the search.
     * @return
     */
    private Set<Node> getNeighbors(Node node, boolean isDiagonal) {
        Set<Node> neighbors = new HashSet<>();
        int row = node.getRow(), col = node.getCol();
        int adjRow, adjCol;
        for (int[] direction : directions) {
            adjRow = row + direction[0];
            adjCol = col + direction[1];
            if (this.isWalkable(adjRow, adjCol)) {
                neighbors.add(grid[adjRow][adjCol]);
            }
        }
        if (isDiagonal) {
            for (int[] diagonal : diagonals) {
                adjRow = row + diagonal[0];
                adjCol = row + diagonal[1];
                if (this.isWalkable(adjRow, adjCol)) {
                    neighbors.add(grid[adjRow][adjCol]);
                }
            }
        }
        return neighbors;
    }
}
