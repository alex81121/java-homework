package javagame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
    private int x, y;
    private int width = 40, height = 50;
    private int health = 30;
    private int speed = 2;
    private int direction = 1; // 1 - вправо, -1 - влево
    private Color color;

    public Enemy(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void update() {
        // Простое движение влево-вправо
        x += speed * direction;
        if (x < 50 || x > 750 - width) {
            direction *= -1;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        // Полоска здоровья
        g.setColor(Color.RED);
        g.fillRect(x, y - 10, width, 5);
        g.setColor(Color.GREEN);
        g.fillRect(x, y - 10, (int)((health / 30.0) * width), 5);
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}