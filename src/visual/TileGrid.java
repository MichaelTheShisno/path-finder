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

    public Tile[][] getTileMatrix() {
        return tileMatrix;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JComponent component = (JComponent)findComponentAt(e.getX(), e.getY());
        if (component instanceof Tile) {
            Tile tile = (Tile)component;
            currentTile = tile;
            switch (tile.getStatus()) {
                case NORMAL:
                    tile.setStatus(Tile.Status.BLOCKED);
                    break;
                case BLOCKED:
                    tile.setStatus(Tile.Status.NORMAL);
                    break;
            }
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

    @Override
    public void mouseDragged(MouseEvent e) {
        JComponent component = (JComponent)findComponentAt(e.getX(), e.getY());
        if (component instanceof Tile) {
            Tile tile = (Tile)component;
            if (currentTile.getStatus() == Tile.Status.START) {
                currentTile.revertStatus();
                tile.setStatus(Tile.Status.START);
            } else if (currentTile.getStatus() == Tile.Status.END) {
                currentTile.revertStatus();
                tile.setStatus(Tile.Status.END);
            } else if (!tile.equals(currentTile)) {
                switch (tile.getStatus()) {
                    case NORMAL:
                        tile.setStatus(Tile.Status.BLOCKED);
                        break;
                    case BLOCKED:
                        tile.setStatus(Tile.Status.NORMAL);
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
