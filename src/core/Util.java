package core;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class provides useful methods used across most path finding algorithms.
 */
public class Util {
    /**
     * Get the path from the first node to the node given.
     * @param node Node that we want a path traced back to the start from.
     * @return ArrayList of nodes from the start to given node.
     */
    public static ArrayList<Node> backtrace(Node node) {
        LinkedList<Node> path = new LinkedList<>();
        for(Node temp = node; temp != null; temp = temp.getParent()) {
            path.add(0, temp);
        }
        return new ArrayList<>(path);
    }

    /**
     * Get the length of the path from a given set of nodes.
     * @param nodes List of nodes.
     * @return Total distance of the path of these nodes.
     */
    public static double pathlength(ArrayList<Node> nodes) {
        double sum = 0.0;
        int dx, dy;
        for(int i = 1; i < nodes.size(); i++) {
            dx = Math.abs(nodes.get(i-1).getX() - nodes.get(i).getX());
            dy = Math.abs(nodes.get(i-1).getY() - nodes.get(i).getY());
            sum += Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        }
        return sum;
    }
}
