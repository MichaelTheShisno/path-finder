package visual;

import core.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages and draws tiles onto the screen.
 */
public class TileGrid extends JPanel implements IConstants, MouseListener, MouseMotionListener {
    private Tile[][] tileMatrix;
    private Tile currentTile;

    public TileGrid() {
        super();
        this.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.initGrid();
    }

    /**
     * Populate and create a new grid of tile objects.
     */
    private void initGrid() {
        System.out.println("Init Grid");
        tileMatrix = new Tile[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                tileMatrix[row][col] = new Tile(TILE_SIZE);
                this.add(tileMatrix[row][col]);
            }
        }
        tileMatrix[START_ROW][START_COL].setStatus(Tile.Status.START);
        tileMatrix[END_ROW][END_COL].setStatus(Tile.Status.END);
    }

    /**
     * Convert from a list of nodes to its respective list of tiles.
     */
    public ArrayList<Tile> getTiles(List<Node> nodes) {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (Node node : nodes) {
            tiles.add(tileMatrix[node.getRow()][node.getCol()]);
        }
        return tiles;
    }

    /**
     * Clear off and restore grid to original look.
     */
    public void reset() {
        this.removeAll();
        this.updateUI();
        this.initGrid();
    }

    /**
     * Clear off open and closed nodes from previous run.
     */
    public void clearPath() {
        for (Tile[] tileRow : tileMatrix) {
            for (Tile tile : tileRow) {
                if (tile.getStatus() == Tile.Status.OPEN || tile.getStatus() == Tile.Status.CLOSED) {
                    tile.setStatus(Tile.Status.NORMAL);
                }
            }
        }
    }

    /**
     * Get the 2d matrix of tiles.
     * @return tileMatrix
     */
    public Tile[][] getTileMatrix() {
        return tileMatrix;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Track and reflect clicking changes made to the grid UI.
     * @param e mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        JComponent component = (JComponent)findComponentAt(e.getX(), e.getY());
        if (component instanceof Tile) {
            Tile tile = (Tile)component;
            switch (tile.getStatus()) {
                case NORMAL:
                case OPEN:
                case CLOSED:
                    tile.setStatus(Tile.Status.BLOCKED);
                    break;
                case BLOCKED:
                    tile.revertStatus();
                    break;
            }
            currentTile = tile;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Track and reflect dragging changes made to the grid UI.
     * @param e mouse event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        JComponent component = (JComponent)findComponentAt(e.getX(), e.getY());
        if (component instanceof Tile) {
            Tile tile = (Tile)component;
            if (currentTile.getStatus() == Tile.Status.START) {
                // Dragging around the start node.
                currentTile.revertStatus();
                tile.setStatus(Tile.Status.START);
            } else if (currentTile.getStatus() == Tile.Status.END) {
                // Dragging around the end node.
                currentTile.revertStatus();
                tile.setStatus(Tile.Status.END);
            } else if (!tile.equals(currentTile)) {
                switch (tile.getStatus()) {
                    // Creating walls (Normal -> Blocked).
                    case NORMAL:
                        if (tile.getStatus() != currentTile.getStatus()) {
                            tile.setStatus(Tile.Status.BLOCKED);
                        }
                        break;
                    // Destroying walls (Blocked -> Previous Status).
                    case BLOCKED:
                        if (tile.getStatus() != currentTile.getStatus()) {
                            tile.revertStatus();
                        }
                        break;
                    case OPEN:
                    case CLOSED:
                        if (tile.getStatus() != currentTile.getStatus()) {
                            tile.setStatus(currentTile.getStatus());
                        }
                        break;
                }
            }
            currentTile = tile;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
