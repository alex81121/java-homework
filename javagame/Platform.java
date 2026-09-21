package javagame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Platform {

    private int x;
    private int y;
    private int width;
    private int height;

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(100, 70, 40));
        g.fillRect(x, y, width, height);

        // Верх платформы
        g.setColor(new Color(60, 180, 60));
        g.fillRect(x, y, width, 8);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
