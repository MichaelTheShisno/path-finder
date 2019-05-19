package visual;

import javax.swing.*;
import java.awt.*;

public class ControllerMenu extends JPanel implements IConstants {
    public ControllerMenu() {
        super();
        this.setSize(MENU_WIDTH, MENU_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(menuBackgroundColor);
        Rectangle rect = new Rectangle(MENU_X, MENU_Y, MENU_WIDTH, MENU_HEIGHT);
        g2.fill(rect);
    }
}
