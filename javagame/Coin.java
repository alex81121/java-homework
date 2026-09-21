package javagame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Coin {
    private int x, y;
    private int size = 20;
    private boolean collected = false;

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        if (!collected) {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, size, size);
            g.setColor(Color.ORANGE);
            g.drawOval(x, y, size, size);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}