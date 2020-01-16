package visual;

import javax.swing.*;
import java.awt.*;

public class ControllerMenu extends JPanel implements IConstants {
    public ControllerMenu() {
        setOpaque(false);
        setSize(MENU_WIDTH, MENU_HEIGHT);
        setBackground(new Color(0, 0, 0, 0.58f));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(menuBackgroundColor);
        Rectangle rect = new Rectangle();
        g2.fill(rect);
        g2.setColor(Color.BLACK);
        g2.draw(rect);
    }
}
