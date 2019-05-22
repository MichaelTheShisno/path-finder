package finders;

import core.*;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;


public class AStarFinder {
    private final Grid grid;
    private final DiagonalMovement diagonalMovement;
    private final Heuristic.Type hType;
    private final int weight;
    private final Node startNode, endNode;
    private PriorityQueue<Node> openSet;
    private Set<Node> closedSet;

    public AStarFinder(Grid grid, DiagonalMovement diagonalMovement, Heuristic.Type hType, int weight) {
        this.grid = grid;
        this.diagonalMovement = diagonalMovement;
        this.hType = hType;
        this.weight = weight;
        this.startNode = grid.getStartNode();
        this.endNode = grid.getEndNode();
        this.init();
    }

    public AStarFinder(Grid grid) {
        this(grid, DiagonalMovement.ONE_OR_NO_OBSTACLES, Heuristic.Type.Euclidean, 1);
    }

    /**
     * Perform the A* path finding algorithm.
     * @return List of nodes from start to end nodes.
     */
    public List<Node> findPath() {
        Node currentNode;
        double tentativeG;
        // While there are still unvisited nodes...
        while (!openSet.isEmpty()) {
            currentNode = openSet.peek();
            if (currentNode.equals(endNode)) {
                return Util.backtrace(currentNode);
            }
            // Move current node from openSet to closedSet.
            openSet.remove(currentNode);
            closedSet.add(currentNode);
            // Check the neighbors of the current node node
            for (Node neighbor : grid.getNeighbors(currentNode, diagonalMovement)) {
                if (!closedSet.contains(neighbor)) {
                    tentativeG = currentNode.getG() + grid.getDistanceBetween(currentNode, neighbor, weight);
                    if (!openSet.contains(neighbor)) {
                        neighbor.setParent(currentNode);
                        neighbor.setG(tentativeG);
                        openSet.add(neighbor);
                    } else if (tentativeG < neighbor.getG()) {
                        neighbor.setParent(currentNode);
                        neighbor.setG(tentativeG);
                        openSet = new PriorityQueue<>(openSet);
                    }
                }
            }
        }
        return null;
    }

    private void init() {
        grid.init(hType);
        closedSet = new HashSet<>();
        openSet = new PriorityQueue<>();
        openSet.add(startNode);
    }

    public Node[] getOpenSet() {
        Node[] nodes = new Node[openSet.size()];
        return openSet.toArray(nodes);
    }

    public Node[] getClosedSet() {
        Node[] nodes = new Node[closedSet.size()];
        return closedSet.toArray(nodes);
    }
}
