package visual;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PathFrame extends JFrame implements IConstants, KeyListener {
    private TileGrid grid;
    public PathFrame() {
        super();
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grid = new TileGrid();
        this.add(grid);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (exitProgramKeysPressed(e)) {
            System.exit(0);
        } else if (resetKeysPressed(e)) {
            grid.resetGrid();
        }
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
        return e.getKeyChar() == KeyEvent.VK_R || e.getKeyChar() == KeyEvent.VK_C;
    }
}
