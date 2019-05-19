package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PathPanel extends JPanel implements IConstants, KeyListener {
    private TileGrid grid;

    PathPanel() {
        super();
        this.setLayout(new OverlayLayout(this));
        grid = new TileGrid();
        this.add(grid, BorderLayout.CENTER);
        this.setFocusable(true);
        this.requestFocus();
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
