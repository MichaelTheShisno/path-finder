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

        tileMatrix = new Tile[NUM_ROWS][NUM_COLS];
        initGrid();
    }

    /**
     * Populate and create a new grid of tile objects.
     */
    private void initGrid() {
        System.out.println("Init Grid");
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
     * Clear walls and previous run's results.
     */
    public void clearWalls() {
        for (Tile[] tileRow : tileMatrix) {
            for (Tile tile : tileRow) {
                Tile.Status status = tile.getStatus();
                if (status != Tile.Status.START && status != Tile.Status.END) {
                    tile.setStatus(Tile.Status.NORMAL);
                }
            }
        }
    }

    /**
     * Clear off open and closed nodes from previous run.
     */
    public void clearPath() {
        for (Tile[] tileRow : tileMatrix) {
            for (Tile tile : tileRow) {
                Tile.Status status = tile.getStatus();
                if (status != Tile.Status.START && status != Tile.Status.END && status != Tile.Status.BLOCKED) {
                    tile.setStatus(Tile.Status.NORMAL);
                }
            }
        }
    }

    /**
     * Indicate failed path search by setting tiles to failed color.
     */
    public void failPath() {
        for (Tile[] tileRow : tileMatrix) {
            for (Tile tile : tileRow) {
                if (tile.getStatus() == Tile.Status.NORMAL) {
                    tile.setStatus(Tile.Status.FAILED);
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
