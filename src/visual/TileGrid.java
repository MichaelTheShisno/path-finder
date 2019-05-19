package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TileGrid extends JPanel implements IConstants, MouseListener, MouseMotionListener {
    private Tile[][] tileMatrix;
    private Tile currentTile;

    TileGrid() {
        super();
        this.initGrid();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * Populate and create a new grid of tile objects.
     */
    private void initGrid() {
        tileMatrix = new Tile[NUM_ROWS][NUM_COLS];
        this.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                tileMatrix[row][col] = new Tile(TILE_SIZE);
                this.add(tileMatrix[row][col]);
            }
        }
        tileMatrix[START_ROW][START_COL].setStatus(Tile.STATUS.START);
        tileMatrix[END_ROW][END_COL].setStatus(Tile.STATUS.END);
    }

    /**
     * Clear off and restore grid to original look.
     */
    public void resetGrid() {
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
                    tile.setStatus(Tile.STATUS.BLOCKED);
                    break;
                case BLOCKED:
                    tile.setStatus(Tile.STATUS.NORMAL);
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
            if (currentTile.getStatus() == Tile.STATUS.START) {
                currentTile.revertStatus();
                tile.setStatus(Tile.STATUS.START);
            } else if (currentTile.getStatus() == Tile.STATUS.END) {
                currentTile.revertStatus();
                tile.setStatus(Tile.STATUS.END);
            } else if (!tile.equals(currentTile)) {
                switch (tile.getStatus()) {
                    case NORMAL:
                        tile.setStatus(Tile.STATUS.BLOCKED);
                        break;
                    case BLOCKED:
                        tile.setStatus(Tile.STATUS.NORMAL);
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
