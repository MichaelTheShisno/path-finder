package core;

/**
 * Class provides an enumeration of some various types of heuristic calculations
 * as well as the methods by which to calculate them.
 */
public class Heuristic {
    public enum Type {
        Manhattan, Euclidean, Octal, Chebyshev
    }

    /**
     * Calculate heuristic based on the type of heuristic method and given changes in x and y.
     * @param hType Type of heuristic to be used in calculations.
     * @param dx Change in the distance between two node's X field.
     * @param dy Change in the distance between two node's Y field.
     * @return Calculated heuristic based on specified type.
     */
    public static double getHeuristic(Type hType, int dx, int dy) {
        switch (hType) {
            case Manhattan:
                return dx + dy;
            case Euclidean:
                return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
            case Octal:
                double D = 1, D2 = Math.sqrt(2);
                return (dx > dy) ? (D * (dx-dy) + D2 * dy) : (D * (dy-dx) + D2 * dx);
            case Chebyshev:
                return Math.max(dx, dy);
        }
        return -1;
    }
}
