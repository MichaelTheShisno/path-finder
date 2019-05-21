package finders;

import core.*;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;


public class AStarFinder {
    private final Grid grid;
    private final boolean allowDiagonal;
    private final Heuristic.Type hType;
    private final int weight;
    private final Node startNode, endNode;

    public AStarFinder(Grid grid, boolean allowDiagonal, Heuristic.Type hType, int weight) {
        this.grid = grid;
        this.allowDiagonal = allowDiagonal;
        this.hType = hType;
        this.weight = weight;
        this.startNode = grid.getStartNode();
        this.endNode = grid.getEndNode();
    }

    /**
     * Perform the A* path finding algorithm.
     * @return List of nodes from start to end nodes.
     */
    public List<Node> findPath() {
        PriorityQueue<Node> openSet = AStarPrep();
        Set<Node> closedSet = new HashSet<>();
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
            for (Node neighbor : grid.getNeighbors(currentNode, allowDiagonal)) {
                if (!closedSet.contains(neighbor)) {
                    tentativeG = currentNode.getG() + grid.getDistanceBetween(currentNode, neighbor, weight);
                    neighbor.setParent(currentNode);
                    neighbor.setG(tentativeG);
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    } else if (tentativeG < neighbor.getG()) {
                        openSet = new PriorityQueue<>(openSet);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Initialize the grid and the open set.
     * @return PriorityQueue of the initial open set
     */
    private PriorityQueue<Node> AStarPrep() {
        grid.init(hType);
        PriorityQueue<Node> initialQueue = new PriorityQueue<>();
        initialQueue.add(startNode);
        return initialQueue;
    }
}
