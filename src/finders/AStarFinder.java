package finders;

import core.*;

public class AStarFinder {
    private final Grid grid;
    private boolean allowDiagonal;
    private Heuristic.Type hType;
    private int weight;

    public AStarFinder(Grid grid, boolean allowDiagonal, Heuristic.Type hType, int weight) {
        this.grid = grid;
        this.allowDiagonal = allowDiagonal;
        this.hType = hType;
        this.weight = weight;
    }
}
