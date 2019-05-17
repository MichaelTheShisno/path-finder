package visual;

import javax.swing.JComponent;
import java.awt.*;

class Tile extends JComponent implements IConstants {
    enum STATUS {
        NORMAL, BLOCKED, START, END, OPEN, CLOSED, FAILED, TESTED
    }

    private final int size;
    private STATUS status;

    Tile(int size) {
        super();
        this.size = size;
        this.status = STATUS.CLOSED;
    }

    private static Color getTileColor(STATUS status) {
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

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
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
