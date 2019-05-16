package core;

/**
 * Class used to store metadata pertinent to path finding algorithms.
 */
public class Node implements Comparable<Node> {
    public final int x, y;
    public Node parent;
    public double gScore;
    public double hScore;
    public double fScore;
    public final boolean walkable;

    public Node(final int x, final int y, final boolean walkable) {
        this.x = x;
        this.y = y;
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
            return this.x == other.x && this.y == other.y;
        }
        return false;
    }

    public String toString() {
        return String.format("Node @ (%d,%d)", this.x, this.y);
    }
}
