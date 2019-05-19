package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PathPanel extends JPanel implements IConstants, KeyListener, MouseListener, MouseMotionListener {
    private TileGrid grid;
    private ControllerMenu menu;

    PathPanel() {
        super();
        this.setLayout(new OverlayLayout(this));
        grid = new TileGrid();
        menu = new ControllerMenu();
        menu.setOpaque(false);
        menu.setBackground(new Color(0, 0, 0, 0.58f));
        this.add(menu);
        this.add(grid);
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
            this.repaint();
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //this.repaint();
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
        //this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
