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
    private int tickCount;
    private double colorOffset;

    public Tile(int size) {
        super();
        this.size = size;
        this.status = Status.NORMAL;
        this.prevStatus = Status.NORMAL;
        this.color = getTileColor(this.status);
        this.timer = new Timer(300, this);
        this.tickCount = 0;
        this.colorOffset = 0;
        this.setOpaque(true);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {
        resetTimer();
        this.prevStatus = this.status;
        this.status = newStatus;
        this.color = getTileColor(this.status);
        this.repaint();
    }

    public void revertStatus() {
        resetTimer();
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

    public void setColorOffset(double colorOffset) {
        this.colorOffset = colorOffset;
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

    private void resetTimer() {
        timer.stop();
        tickCount = 0;
        colorOffset = 0;
    }

    private void progressiveRainbow() {
        if (tickCount == 0) {
            // Calculate rgb values
            int percentRGB = (int)(colorOffset*330);
            int red   = lights[(percentRGB+120)%330];
            int green = lights[percentRGB%330];
            int blue  = lights[(percentRGB+240)%330];
            color = new Color(red, green, blue);
            tickCount++;
            fastPaint();
        } else {
            setStatus(Status.NORMAL);
        }
    }

    private void fullRainbow() {
        if (tickCount < 330) {
            // Calculate rgb values
            int red   = lights[(tickCount+120)%360];
            int green = lights[tickCount];
            int blue  = lights[(tickCount+240)%360];
            color = new Color(red, green, blue);
            tickCount+=9;
            fastPaint();
        } else {
            setStatus(Status.NORMAL);
        }
    }

    /**
     * Low-Latency painting of tile component.
     */
    private void fastPaint() {
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
    }

    /**
     * Animates a sine-based rgb rainbow effect.
     * From red->purple.
     * @param e timer event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        progressiveRainbow();
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
