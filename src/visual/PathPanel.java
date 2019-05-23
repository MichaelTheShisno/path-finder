package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PathPanel extends JPanel implements IConstants, KeyListener {
    private TileGrid tileGrid;
    private ControllerMenu menu;
    private boolean isRunning;

    PathPanel() {
        super();
        this.setLayout(new OverlayLayout(this));
        tileGrid = new TileGrid();
        menu = new ControllerMenu();
        menu.setOpaque(false);
        menu.setBackground(new Color(0, 0, 0, 0.58f));
        //this.add(menu);
        this.add(tileGrid);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
        isRunning = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (exitProgramKeysPressed(e)) {
            System.exit(0);
        } else if (resetKeysPressed(e)) {
            this.reset();
            this.repaint();
        } else if (startKeyPressed(e)) {
            if (!isRunning) {
                isRunning = true;
                List<Tile> tiles = tileGrid.run();
                List<Line> lines = this.getLines(tiles);
                this.drawPath(lines);
                isRunning = false;
            }
        } else if (pauseKeyPressed(e)) {
            isRunning = false;
        } else if (cancelKeyPressed(e)) {
            isRunning = false;
        }
    }

    private List<Line> getLines(List<Tile> tiles) {
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < tiles.size()-1; i++) {
            lines.add(new Line(tiles.get(i), tiles.get(i+1)));
        }
        System.out.println(lines);
        return lines;
    }

    private void drawPath(List<Line> lines) {
        this.remove(tileGrid);
        for (Line line : lines) {
            this.add(line);
            this.repaint();
        }
        this.add(tileGrid);
        this.updateUI();
    }

    private void reset() {
        this.removeAll();
        this.updateUI();
        this.add(tileGrid);
        tileGrid.reset();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Check if a key event is an exit event.
     * @param e KeyEvent
     * @return Returns if any of the escape program keys are hit.
     */
    private boolean exitProgramKeysPressed(KeyEvent e) {
        return e.getKeyChar() == KeyEvent.VK_ESCAPE || e.getKeyChar() == KeyEvent.VK_Q;
    }

    /**
     * Check if a key event is a reset event.
     * @param e KeyEvent
     * @return Returns if any of the reset keys are hit.
     */
    private boolean resetKeysPressed(KeyEvent e) {
        return !isRunning && (e.getKeyChar() == KeyEvent.VK_R || e.getKeyChar() == KeyEvent.VK_C);
    }

    /**
     * Check if key event is a start search event.
     * @param e KeyEvent
     * @return Returns if a start key is hit.
     */
    private boolean startKeyPressed(KeyEvent e) {
        return !isRunning && (e.getKeyChar() == KeyEvent.VK_S);
    }

    /**
     * Check if key event is a pause search event.
     * @param e KeyEvent
     * @return Returns if a pause key is hit.
     */
    private boolean pauseKeyPressed(KeyEvent e) {
        return isRunning && (e.getKeyChar() == KeyEvent.VK_P);
    }

    /**
     * Check if key event is a cancel search event.
     * @param e KeyEvent
     * @return Returns if a cancel key is hit.
     */
    private boolean cancelKeyPressed(KeyEvent e) {
        return isRunning && (e.getKeyChar() == KeyEvent.VK_C);
    }
}
