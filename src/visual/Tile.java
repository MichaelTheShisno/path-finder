package visual;

import javax.swing.JComponent;
import java.awt.*;

/**
 * Class that provides an abstraction of the tiles which are laid out on the grid.
 */
public class Tile extends JComponent implements IConstants {
    public enum Status {
        NORMAL, BLOCKED, START, END, OPEN, CLOSED, FAILED, TESTED
    }

    private final int size;
    private Status status, prevStatus;

    public Tile(int size) {
        super();
        this.size = size;
        this.status = Status.NORMAL;
        this.prevStatus = Status.NORMAL;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {
        this.prevStatus = this.status;
        this.status = newStatus;
        this.repaint();
    }

    public void revertStatus() {
        this.status = this.prevStatus;
        this.prevStatus = Status.NORMAL;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(getTileColor(this.status));
        Rectangle tile = new Rectangle(size, size);
        g2.fill(tile);
        g2.setColor(Color.BLACK);
        g2.draw(tile);
    }
}
