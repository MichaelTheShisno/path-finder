package visual;

import core.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TileGrid extends JPanel implements IConstants, MouseListener, MouseMotionListener {
    public Tile[][] tileMatrix;
    TileGrid() {
        super();
        tileMatrix = new Tile[NUM_ROWS][NUM_COLS];
        this.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                tileMatrix[row][col] = new Tile(TILE_SIZE);
                this.add(tileMatrix[row][col]);
            }
        }
        //addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JComponent component = (JComponent)findComponentAt(e.getX(), e.getY());
        if (component instanceof Tile) {
            Tile tile = (Tile)component;
            switch (tile.getStatus()) {
                case NORMAL:
                    tile.setStatus(Tile.STATUS.BLOCKED);
                    break;
                case BLOCKED:
                    tile.setStatus(Tile.STATUS.NORMAL);
                    break;
            }
        }
        this.repaint();
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
            switch (tile.getStatus()) {
                case NORMAL:
                    tile.setStatus(Tile.STATUS.BLOCKED);
                    break;
                case BLOCKED:
                    tile.setStatus(Tile.STATUS.NORMAL);
                    break;
            }
        }
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
