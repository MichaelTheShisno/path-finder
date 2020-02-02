package visual;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that provides an abstraction of the tiles which are laid out on the grid.
 */
public class Tile extends JComponent implements IConstants, ActionListener {
    public enum Status {
        NORMAL, BLOCKED, START, END, OPEN, CLOSED, FAILED, TESTED
    }

    private final int size;
    private Status status, prevStatus;
    private Color color;
    private Timer timer;
    private int angle;
    private int delay;

    public Tile(int size) {
        super();
        this.size = size;
        this.status = Status.NORMAL;
        this.prevStatus = Status.NORMAL;
        this.color = getTileColor(this.status);
        this.timer = new Timer(9, this);
        this.angle = 0;
        this.delay = 0;
        this.setOpaque(true);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {
        timer.stop();
        angle = 0;
        this.prevStatus = this.status;
        this.status = newStatus;
        this.color = getTileColor(this.status);
        this.repaint();
    }

    public void revertStatus() {
        timer.stop();
        angle = 0;
        this.status = this.prevStatus;
        this.prevStatus = Status.NORMAL;
        this.color = getTileColor(this.status);
        this.repaint();
    }

    /**
     * Invokes the timer used to create a rainbow effect
     * on this tile.
     */
    public void rainbow() {
        if (this.status == Status.NORMAL) {
            timer.start();
        }
    }

    /**
     * Used to animate a ripple effect on the board
     * @param initDelay Timer's initial delay
     */
    public void setInitialDelay(int initDelay) {
        timer.setInitialDelay(initDelay);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(color);
        Rectangle tile = new Rectangle(size, size);
        g2.fill(tile);
        g2.setColor(Color.BLACK);
        g2.draw(tile);
    }

    /**
     * Animates a sine-based rgb rainbow effect.
     * From red->purple.
     * @param e timer event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (angle < 330) {
            // Calculate rgb values
            int red   = lights[(angle+120)%360];
            int green = lights[angle];
            int blue  = lights[(angle+240)%360];
            color = new Color(red, green, blue);
            angle+=9;
            // Fill in colors
            Graphics2D g = (Graphics2D)this.getGraphics();
            g.setColor(color);
            RepaintManager rm = RepaintManager.currentManager(this);
            boolean b = rm.isDoubleBufferingEnabled();
            rm.setDoubleBufferingEnabled(false);
            Rectangle tile = new Rectangle(size, size);
            g.fill(tile);
            g.setColor(Color.BLACK);
            g.draw(tile);
            paintImmediately(this.getX(), this.getY(), size, size);
            g.dispose();
            rm.setDoubleBufferingEnabled(b);
        } else {
            timer.stop();
            angle = 0;
            this.setStatus(Status.NORMAL);
        }
    }

    /**
     * Return the respective color for a tile's status.
     * @param status Tile.Status enum
     * @return Color corresponding to the given status.
     */
    private static Color getTileColor(Status status) {
        switch (status) {
            case START:
                return startColor;
            case END:
                return endColor;
            case BLOCKED:
                return blockedColor;
            case NORMAL:
                return normalColor;
            case OPEN:
                return openColor;
            case CLOSED:
                return closedColor;
            case FAILED:
                return failedColor;
            default:
                return testedColor;
        }
    }
}
