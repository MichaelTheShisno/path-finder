package core;

/**
 * Class used to store metadata pertinent to path finding algorithms.
 */
public class Node implements Comparable<Node> {
    public final int row, col;
    public Node parent;
    public double gScore;
    public double hScore;
    public double fScore;
    public final boolean walkable;

    public Node(final int row, final int col, final boolean walkable) {
        this.row = row;
        this.col = col;
        this.walkable = walkable;
    }

    public Node() {
        this(0, 0, false);
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.fScore, other.fScore);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node other = (Node)obj;
            return this.row == other.row && this.col == other.col;
        }
        return false;
    }

    public String toString() {
        return String.format("Node: Row %-3d Col %-3d", this.row, this.col);
    }
}
