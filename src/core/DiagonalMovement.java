package core;

/**
 * Enumeration of the possible modes of choosing a diagonal neighbor.
 * ALWAYS - always move diagonally
 * ONE_OR_NO_OBSTACLES - can move diagonally if there are at most 1 obstacles
 * NO_OBSTACLES - can move diagonally only if there are no obstacles
 * NEVER - diagonal movement is not allowed
 */
public enum DiagonalMovement {
    ALWAYS, ONE_OR_NO_OBSTACLES, NO_OBSTACLES, NEVER
}
