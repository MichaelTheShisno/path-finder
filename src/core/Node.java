package core;

/**
 * Class used to store metadata pertinent to path finding algorithms.
 */
public class Node implements Comparable<Node> {
    private final int x, y;
    private Node parent;
    private double gScore;
    private double hScore;
    private double fScore;
    private boolean walkable;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public double getG() {
        return gScore;
    }

    public void setG(double gScore) {
        this.gScore = gScore;
        updateScores();
    }

    public double getH() {
        return hScore;
    }

    public void setH(double hScore) {
        this.hScore = hScore;
        updateScores();
    }

    public double getF() {
        return fScore;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    private void updateScores() {
        fScore = gScore + hScore;
    }
 }
