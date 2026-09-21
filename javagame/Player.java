package javagame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
    // Поля
    private Weapon weapon;
    private int coins;
    private boolean isAttacking = false;
    private int attackCooldown;
    private int direction = 1;
    private int attackTimer = 0;

    // Положение игрока
    private int x;
    private int y;

    // Размер игрока
    private int width = 50;
    private int height = 70;

    // Скорость движения
    private int speed = 5;

    // Жизни
    private int health = 100;

    // Цвет игрока
    private Color color;

    // Гравитация
    private double velocityY = 0;
    private double gravity = 0.5;

    // Сила прыжка
    private double jumpStrength = -12;

    // Находится ли игрок на земле
    private boolean onGround = false;

    public Player(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.weapon = new Weapon("Мотыга");
        this.coins = 0;
        this.isAttacking = false;
        this.attackCooldown = 0;
    }

    public void attack() {
        if (attackTimer <= 0 && onGround) {
            isAttacking = true;
            attackTimer = 10;
        }
    }

    // Рисуем игрока
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);

        if (isAttacking) {
            g.setColor(new Color(255, 255, 0, 100));
            Rectangle attackBounds = getAttackBounds();
            g.fillRect(attackBounds.x, attackBounds.y, attackBounds.width, attackBounds.height);
        }
    }

    // Движение влево
    public void moveLeft() {
        if (x > 0) {
            x -= speed;
            direction = -1;
        }
    }

    // Движение вправо
    public void moveRight() {
        if (x + width < 800) {
            x += speed;
            direction = 1;
        }
    }

    // Прыжок
    public void jump() {
        if (onGround) {
            velocityY = jumpStrength;
            onGround = false;
        }
    }

    // Гравитация и столкновение с платформами
    public void update(Platform[] platforms) {

        // Гравитация
        velocityY += gravity;

        // Двигаем игрока по вертикали
        y += (int) velocityY;

        onGround = false;

        // Столкновение с землёй
        if (y + height >= 500) {
            y = 500 - height;
            velocityY = 0;
            onGround = true;
        }

        // Столкновение с платформами
        for (Platform platform : platforms) {

            int platformX = platform.getBounds().x;
            int platformY = platform.getBounds().y;
            int platformWidth = platform.getBounds().width;
            int platformHeight = platform.getBounds().height;

            // Игрок падает сверху на платформу
            if (velocityY >= 0) {

                if (x + width > platformX &&
                    x < platformX + platformWidth &&
                    y + height >= platformY &&
                    y + height <= platformY + platformHeight + 10) {

                    y = platformY - height;

                    velocityY = 0;

                    onGround = true;
                }
            }
        }

        if (attackTimer > 0) {
            attackTimer--;
        } else {
            isAttacking = false;
        }
    }

    public void spendCoins(int amount) {
        coins -= amount;
    }

    public boolean upgradeWeapon() {
        if (coins >= 10) {
            coins -= 10;
            weapon.upgrade();
            System.out.println("Оружие улучшено! Урон: " + weapon.getDamage());
            return true;
        }
        return false;
    }

    public void addCoin() {
        this.coins++;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Область атаки (перед игроком)
    public Rectangle getAttackBounds() {
        if (direction == 1) {
            return new Rectangle(x + width, y + 10, 30, height - 20);
        } else {
            return new Rectangle(x - 30, y + 10, 30, height - 20);
        }
    }

    public int getCoins() {
        return coins;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }
}