package visual;

import core.*;
import finders.AStarFinder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TileGrid extends JPanel implements IConstants, MouseListener, MouseMotionListener {
    private Tile[][] tileMatrix;
    private Tile currentTile;
    private Grid nodeGrid;
    private AStarFinder finder;

    public TileGrid() {
        super();
        this.initGrid();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * Populate and create a new grid of tile objects.
     */
    private void initGrid() {
        System.out.println("Init Grid");
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

    public void run() {
        System.out.println("Run");
        nodeGrid = new Grid(this);
        finder = new AStarFinder(nodeGrid);
        finder.findPath();
    }

    public void refreshTiles() {
        for (Node node : finder.getClosedSet()) {
            if (!(node == nodeGrid.getStartNode() || node == nodeGrid.getEndNode())) {
                tileMatrix[node.getRow()][node.getCol()].setStatus(Tile.STATUS.CLOSED);
            }
        }
        for (Node node : finder.getOpenSet()) {
            if (!(node == nodeGrid.getStartNode() || node == nodeGrid.getEndNode())) {
                tileMatrix[node.getRow()][node.getCol()].setStatus(Tile.STATUS.OPEN);
            }
        }
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
