package finders;

import core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.PriorityQueue;


/**
 * Class that performs the A* Path Finding Algorithm.
 */
public class AStarFinder {
    private final Grid grid;
    private final DiagonalMovement diagonalMovement;
    private final Heuristic.Type hType;
    private final int weight;
    private final Node startNode, endNode;
    private PriorityQueue<Node> openSet;
    private Set<Node> closedSet;

    /**
     * Loaded Constructor that takes in necessary data for the search algorithm. Initializes the search algorithm.
     * @param grid Grid of nodes that will be used to path find.
     * @param diagonalMovement Mode in which neighboring nodes in the diagonal directions will be chosen.
     * @param hType Mode in which the hScore heuristics of each node will be calculated.
     * @param weight Amount per unit distance.
     */
    public AStarFinder(Grid grid, DiagonalMovement diagonalMovement, Heuristic.Type hType, int weight) {
        this.grid = grid;
        this.diagonalMovement = diagonalMovement;
        this.hType = hType;
        this.weight = weight;
        this.startNode = grid.getStartNode();
        this.endNode = grid.getEndNode();
        this.init();
    }

    /**
     * Default Constructor that takes in necessary data for the search algorithm. Initializes the search algorithm.
     * @param grid Grid of nodes that will be used to path find.
     */
    public AStarFinder(Grid grid) {
        this(grid, DiagonalMovement.ONE_OR_NO_OBSTACLES, Heuristic.Type.Euclidean, 1);
    }

    /**
     * Perform the A* path finding algorithm.
     * @return List of nodes from start to end nodes.
     */
    public SearchData findPath() {
        SearchData searchData = new SearchData();
        List<Node> openNeighborList = new ArrayList<>();
        Node currentNode;
        double tentativeG;
        // While there are still unvisited nodes...
        while (!openSet.isEmpty()) {
            currentNode = openSet.peek();
            if (currentNode.equals(endNode)) {
                searchData.setPath(Util.backtrace(currentNode));
                return searchData;
            }
            // Move current node from openSet to closedSet.
            openSet.remove(currentNode);
            closedSet.add(currentNode);
            searchData.addClosedIteration(currentNode);
            // Check the neighbors of the current node node
            for (Node neighbor : grid.getNeighbors(currentNode, diagonalMovement)) {
                if (!closedSet.contains(neighbor)) {
                    tentativeG = currentNode.getG() + grid.getDistanceBetween(currentNode, neighbor, weight);
                    if (!openSet.contains(neighbor)) {
                        neighbor.setParent(currentNode);
                        neighbor.setG(tentativeG);
                        openSet.add(neighbor);
                        openNeighborList.add(neighbor);
                    } else if (tentativeG < neighbor.getG()) {
                        neighbor.setParent(currentNode);
                        neighbor.setG(tentativeG);
                        openSet = new PriorityQueue<>(openSet);
                    }
                }
            }
            searchData.addOpenIteration(getOpenIteration(openNeighborList));
            openNeighborList.clear();
        }
        return null;
    }

    /**
     * Helper method used to set up the path finding process.
     */
    private void init() {
        grid.init(hType);
        closedSet = new HashSet<>();
        openSet = new PriorityQueue<>();
        openSet.add(startNode);
    }

    /**
     * Get the current set of nodes to be evaluated.
     * @return Array of nodes
     */
    public Node[] getOpenIteration(List<Node> openIteration) {
        Node[] nodes = new Node[openIteration.size()];
        return openIteration.toArray(nodes);
    }
}
