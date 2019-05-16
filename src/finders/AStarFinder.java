package finders;

import core.*;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarFinder {
    private final Grid grid;
    private boolean allowDiagonal;
    private Heuristic.Type hType;
    private int weight;
    private Node startNode, endNode;

    public AStarFinder(Grid grid, boolean allowDiagonal, Heuristic.Type hType, int weight) {
        this.grid = grid;
        this.allowDiagonal = allowDiagonal;
        this.hType = hType;
        this.weight = weight;
        startNode = grid.getStartNode();
        endNode = grid.getEndNode();
    }

    public List<Node> findPath() {
        PriorityQueue<Node> openSet = AStarPrep();
        Set<Node> closedSet = new HashSet<>();
        Node currentNode;
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
                    double tentativeG = currentNode.getG() + grid.getDistanceBetween(currentNode, neighbor, weight);
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

    private PriorityQueue<Node> AStarPrep() {
        grid.init(hType);
        PriorityQueue<Node> initialQueue = new PriorityQueue<>();
        initialQueue.add(startNode);
        return initialQueue;
    }
}
