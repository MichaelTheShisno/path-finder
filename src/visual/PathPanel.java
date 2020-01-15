package visual;

import core.Grid;
import core.Node;
import core.SearchData;
import finders.AStarFinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that provides an overlay of JPanels and JComponents.
 * Handles key inputs.
 */
public class PathPanel extends JPanel implements IConstants, KeyListener, ActionListener {
    private TileGrid tileGrid;
    private Grid grid;
    private ControllerMenu menu;
    private List<Line> lines;
    private boolean isRunning;
    private boolean isAnimating;
    private SearchData results;
    private int iterationIndex;
    private Timer timer;

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
        isAnimating = false;
        iterationIndex = 0;
        results = null;
        timer = new Timer(1, this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!isRunning && results != null) {
            if (iterationIndex < results.getOpenSetList().size()) {
                // Paint the current iteration of open neighbor nodes.
                Node[] openSet = results.getOpenSetList().get(iterationIndex);
                Tile[][] tileMatrix = tileGrid.getTileMatrix();
                for (Node openNode : openSet) {
                    if (!(openNode.equals(grid.getStartNode()) || openNode.equals(grid.getEndNode()))) {
                        tileMatrix[openNode.getRow()][openNode.getCol()].setStatus(Tile.Status.OPEN);
                    }
                }
                // Paint the current iteration of closed nodes.
                Node closedNode = results.getClosedSetList().get(iterationIndex);
                if (!(closedNode.equals(grid.getStartNode()) || closedNode.equals(grid.getEndNode()))) {
                    tileMatrix[closedNode.getRow()][closedNode.getCol()].setStatus(Tile.Status.CLOSED);
                }
                iterationIndex++;
            } else {
                isAnimating = false;
                lines = this.getLines(tileGrid.getTiles(results.getPath()));
                drawPath(lines);
                timer.stop();
            }
        } else {
            isAnimating = false;
            timer.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (exitProgramKeysPressed(e)) {
            System.out.println("Exit");
            System.exit(0);
        } else if (resetKeysPressed(e)) {
            System.out.println("Reset");
            this.reset();
            this.repaint();
        }
        else if (clearKeysPressed(e)) {
            System.out.println("Clear");
            this.clear();
        } else if (startKeyPressed(e)) {
            if (!isRunning) {
                isRunning = true;
                System.out.println("Run");
                grid = new Grid(tileGrid);
                AStarFinder aStar = new AStarFinder(grid);
                results = aStar.findPath();
                isRunning = false;
                if (results != null) {
                    System.out.println("Path Found");
                    System.out.println(results.getPath());
                    timer.start();
                    isAnimating = true;
                } else {
                    System.out.println("No Path Exists");
                }
            }
        } else if (pauseKeyPressed(e)) {
            isRunning = false;
        } else if (cancelKeyPressed(e)) {
            isRunning = false;
        }
    }

    /**
     * Get the list of lines that correspond to the path between nodes.
     * @param tiles List of tiles that correspond to the path.
     * @return List of lines.
     */
    private List<Line> getLines(List<Tile> tiles) {
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < tiles.size()-1; i++) {
            lines.add(new Line(tiles.get(i), tiles.get(i+1)));
        }
        return lines;
    }

    /**
     * TODO: Move line drawing responsibility to TileGrid
     * Add lines from path starting from the start node to the end node.
     * @param lines List of lines to add to the canvas
     */
    private void drawPath(List<Line> lines) {
        this.remove(tileGrid);
        for (Line line : lines) {
            this.add(line);
        }
        this.repaint();
        this.add(tileGrid);
        this.updateUI();
    }

    /**
     * Reset the grid to its initial state.
     */
    private void reset() {
        this.removeAll();
        this.updateUI();
        this.add(tileGrid);
        tileGrid.reset();
        iterationIndex = 0;
        results = null;
    }

    /**
     * Clear path lines from the grid.
     */
    private void clear() {
        clearPath();
        this.repaint();
        this.updateUI();
    }

    /**
     * Clear all lines and reset all open and closed nodes in the tile grid.
     */
    private void clearPath() {
        // Clear lines from grid.
        if (lines != null) {
            for (Line line : lines) {
                this.remove(line);
            }
            lines.clear();
        }
        // Reset open and closed nodes back to unblocked nodes.
        tileGrid.clearPath();
        // Reset animation variables.
        iterationIndex = 0;
        results = null;
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
        return !isRunning && (e.getKeyChar() == KeyEvent.VK_R);
    }

    /**
     * Check if a key event is a clear event.
     * @param e KeyEvent
     * @return Returns if any of the clear keys are hit.
     */
    private boolean clearKeysPressed(KeyEvent e) {
        return !isRunning && (e.getKeyChar() == KeyEvent.VK_C);
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
