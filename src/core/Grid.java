package core;

import visual.IConstants;
import visual.Tile;
import visual.TileGrid;

import java.util.Set;
import java.util.HashSet;

/**
 * Abstraction layer of the grid of nodes.
 */
public class Grid implements IConstants {
    private Node[][] grid;
    private Node startNode;
    private Node endNode;
    private final static int INFINITY = Integer.MAX_VALUE;
    private final static int[][] orthogonal = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private final static int[][] diagonal = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    private Tile[][] tiles;

    public Grid(TileGrid tileGrid) {
        this.tiles = tileGrid.getTileMatrix();
        int NUM_ROWS = tiles.length;
        int NUM_COLS = tiles[0].length;
        this.grid = new Node[NUM_ROWS][NUM_COLS];
        this.buildNodes(tiles);
    }

    /**
     * Populate grid with new nodes based on the grid of tiles.
     * @param tiles arrangement of tiles as made in the UI,
     *              recreate grid of nodes based on grid of tiles
     */
    private void buildNodes(Tile[][] tiles) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                switch (tiles[row][col].getStatus()) {
                    case BLOCKED:
                        grid[row][col] = new Node(row, col, false);
                        break;
                    case START:
                        grid[row][col] = new Node(row, col, true);
                        startNode = grid[row][col];
                        break;
                    case END:
                        grid[row][col] = new Node(row, col, true);
                        endNode = grid[row][col];
                        break;
                    case NORMAL:
                        grid[row][col] = new Node(row, col, true);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Set up grid for initial path finding.
     * Set all node's parent to null, gScore to INFINITY, and set their hScores appropriately.
     * @param hType Type of heuristic to be used for calculating hScores for each node.
     */
    public void init(Heuristic.Type hType) {
        double maxDistanceFromEnd = maxDistance();
        int endRow = endNode.getRow(), endCol = endNode.getCol();
        int dx, dy;
        for (Node[] row : grid) {
            for (Node node : row) {
                dx = Math.abs(node.getCol() - endCol);
                dy = Math.abs(node.getRow() - endRow);
                node.setG(0.5*INFINITY);
                node.setH(Heuristic.getHeuristic(hType, dx, dy));
                node.setParent(null);
                double distanceFromEnd = getDistanceBetween(node, endNode, 1);
                tiles[node.getRow()][node.getCol()].setColorOffset(distanceFromEnd/maxDistanceFromEnd);
                tiles[node.getRow()][node.getCol()].setInitialDelay((int)(200*distanceFromEnd));
            }
        }
        startNode.setG(0);
    }

    private double maxDistance() {
        double maxDistance = -1;
        int[][] corners = new int[][] {{0, 0}, {0, NUM_COLS-1}, {NUM_ROWS-1, 0}, {NUM_ROWS-1, NUM_COLS-1}};
        for (int[] pair : corners) {
            maxDistance = Math.max(maxDistance, getDistanceBetween(getNodeAt(pair[0], pair[1]), endNode, 1));
        }
        return maxDistance;
    }

    public Node getNodeAt(int row, int col) {
        return grid[row][col];
    }

    public boolean isInside(int row, int col) {
        return (0 <= row && row < grid.length && 0 <= col && col < grid[0].length);
    }

    public boolean isWalkable(int row, int col) {
        return isInside(row, col) && grid[row][col].isWalkable();
    }

    public void setWalkableAt(int row, int col, boolean walkable) {
        this.grid[row][col].setWalkable(walkable);
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    /**
     * Get the total distance between 2 nodes, take weight into consideration.
     * @param node1 Node 1
     * @param node2 Node 2
     * @param weight Amount per unit distance.
     * @return Distance between the 2 nodes.
     */
    public double getDistanceBetween(Node node1, Node node2, int weight) {
        int dx = Math.abs(node1.getCol() - node2.getCol());
        int dy = Math.abs(node1.getRow() - node2.getRow());
        return weight * Heuristic.getHeuristic(Heuristic.Type.Euclidean, dx, dy);
    }

    /**
     * Get a set of all walkable immediate neighbors in the orthogonal direction.
     * If diagonal nodes are desired, include those too.
     *
     *   Orthogonal            Diagonal
     * +---+---+---+        +---+---+---+
     * |   | 0 |   |        | 0 |   | 1 |
     * +---+---+---+        +---+---+---+
     * | 3 | N | 1 |        |   | N |   |
     * +---+---+---+        +---+---+---+
     * |   | 2 |   |        | 3 |   | 2 |
     * +---+---+---+        +---+---+---+
     *
     * @param node The node whose neighbors we are getting.
     * @param diagonalMovement Determines how diagonal neighbors are evaluated in the search.
     * @return Set of immediate open neighbors.
     */
    public Set<Node> getNeighbors(Node node, DiagonalMovement diagonalMovement) {
        Set<Node> neighbors = new HashSet<>();
        int adjRow, adjCol;
        int row = node.getRow(), col = node.getCol();
        int orthoIndex = 0, diagIndex = 0;
        boolean[] orthoArr = new boolean[4];
        boolean[] diagArr = new boolean[4];
        // Check immediate orthogonal neighbors...
        for (int[] direction : orthogonal) {
            adjRow = row + direction[0];
            adjCol = col + direction[1];
            if (this.isWalkable(adjRow, adjCol)) {
                neighbors.add(grid[adjRow][adjCol]);
                orthoArr[orthoIndex] = true;
            }
            orthoIndex++;
        }
        // If diagonal neighbors need to be taken into account...
        if (diagonalMovement != DiagonalMovement.NEVER) {
            // Determine if based on what Movement style the user specifies, that the diagonal neighbor is valid.
            switch (diagonalMovement) {
                case ALWAYS: {
                    diagArr[0] = true;
                    diagArr[1] = true;
                    diagArr[2] = true;
                    diagArr[3] = true;
                }
                break;
                case ONE_OR_NO_OBSTACLES: {
                    diagArr[0] = orthoArr[3] || orthoArr[0];
                    diagArr[1] = orthoArr[0] || orthoArr[1];
                    diagArr[2] = orthoArr[1] || orthoArr[2];
                    diagArr[3] = orthoArr[2] || orthoArr[3];
                }
                break;
                case NO_OBSTACLES: {
                    diagArr[0] = orthoArr[3] && orthoArr[0];
                    diagArr[1] = orthoArr[0] && orthoArr[1];
                    diagArr[2] = orthoArr[1] && orthoArr[2];
                    diagArr[3] = orthoArr[2] && orthoArr[3];
                }
                break;
                default:
                    break;
            }
            // Check immediate diagonal neighbors...
            for (int[] direction : diagonal) {
                adjRow = row + direction[0];
                adjCol = col + direction[1];
                if (diagArr[diagIndex] && this.isWalkable(adjRow, adjCol)) {
                    neighbors.add(grid[adjRow][adjCol]);
                }
                diagIndex++;
            }
        }
        return neighbors;
    }
}
