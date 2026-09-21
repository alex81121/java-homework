package javagame;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {

    private Player player1;
    private Player player2;

    private Platform platform1;
    private Platform platform2;

    // Клавиши игрока 1
    private boolean aPressed;
    private boolean dPressed;

    // Клавиши игрока 2
    private boolean leftPressed;
    private boolean rightPressed;

    private ArrayList<Coin> coins;
    private ArrayList<Enemy> enemies;
    private Random random;
    private int enemyRespawnTimer = 0;

    public GamePanel() {

        setFocusable(true);
        addKeyListener(this);

        // Создаём игроков
        player1 = new Player(100, 400, Color.RED);
        player2 = new Player(650, 400, Color.BLUE);

        // Создаём платформы
        platform1 = new Platform(200, 350, 250, 30);
        platform2 = new Platform(500, 250, 200, 30);

        coins = new ArrayList<>();
        enemies = new ArrayList<>();
        random = new Random();

        for (int i = 0; i < 10; i++) {
            int x = 50 + random.nextInt(700);
            int y = 100 + random.nextInt(300);
            coins.add(new Coin(x, y));
        }

        enemies.add(new Enemy(400, 200, Color.MAGENTA));
        enemies.add(new Enemy(200, 200, Color.ORANGE));
        enemies.add(new Enemy(500, 300, Color.BLUE));
        enemies.add(new Enemy(350, 100, Color.PINK));

        requestFocusInWindow();

        // Игровой цикл
        Thread gameThread = new Thread(() -> gameLoop());
        gameThread.start();
    }

    private void gameLoop() {

        while (true) {

            update();

            repaint();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        Platform[] platforms = {platform1, platform2};

        player1.update(platforms);
        player2.update(platforms);

        if (aPressed) player1.moveLeft();
        if (dPressed) player1.moveRight();
        if (leftPressed) player2.moveLeft();
        if (rightPressed) player2.moveRight();

        // Сбор монет
        Iterator<Coin> coinIterator = coins.iterator();
        while (coinIterator.hasNext()) {
            Coin coin = coinIterator.next();
            if (!coin.isCollected()) {
                if (player1.getBounds().intersects(coin.getBounds())) {
                    coin.setCollected(true);
                    player1.addCoin();
                    coinIterator.remove();
                    System.out.println("Игрок 1 подобрал монету");
                } else if (player2.getBounds().intersects(coin.getBounds())) {
                    coin.setCollected(true);
                    player2.addCoin();
                    coinIterator.remove();
                    System.out.println("Игрок 2 подобрал монету");
                }
            }
        }

        // Обновление врагов
        for (Enemy enemy : enemies) {
            enemy.update();
        }

        // Респавн врагов
        if (enemies.size() < 3) {
            enemyRespawnTimer++;
            if (enemyRespawnTimer > 120) {
                int x = 50 + random.nextInt(700);
                int y = 100 + random.nextInt(300);
                enemies.add(new Enemy(x, y, Color.MAGENTA));
                enemyRespawnTimer = 0;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Небо
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, 800, 600);

        // Земля
        g.setColor(new Color(80, 180, 80));
        g.fillRect(0, 500, 800, 100);

        // Платформы
        platform1.draw(g);
        platform2.draw(g);

        // Игроки
        player1.draw(g);
        player2.draw(g);

        // Монеты
        for (Coin coin : coins) {
            coin.draw(g);
        }

        // Враги
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        // Отображение монет и уровня оружия (HUD)
        g.setColor(Color.BLACK);
        g.drawString("Игрок 1 (Красный) - Монет: " + player1.getCoins() +
                " | Оружие: " + player1.getWeapon().getName() +
                " Ур." + player1.getWeapon().getLevel(), 10, 20);
        g.drawString("Игрок 2 (Синий) - Монет: " + player2.getCoins() +
                " | Оружие: " + player2.getWeapon().getName() +
                " Ур." + player2.getWeapon().getLevel(), 10, 40);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        // Игрок 1 - движение
        if (key == KeyEvent.VK_A) {
            aPressed = true;
        }
        if (key == KeyEvent.VK_D) {
            dPressed = true;
        }
        if (key == KeyEvent.VK_W) {
            player1.jump();
        }

        // Игрок 2 - движение
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_UP) {
            player2.jump();
        }

        // Игрок 1 - атака
        if (key == KeyEvent.VK_F) {
            player1.attack();
            performAttack(player1);
        }

        // Игрок 2 - атака
        if (key == KeyEvent.VK_SLASH) {
            player2.attack();
            performAttack(player2);
        }

        // Игрок 1 - улучшение оружия
        if (key == KeyEvent.VK_E) {
            if (player1.getCoins() >= GameState.upgradeCost) {
                player1.spendCoins(GameState.upgradeCost);
                GameState.sharedWeapon.upgrade();
                GameState.upgradeCost += 5;
                System.out.println("Оружие улучшено для обоих игроков! Уровень: " +
                        GameState.sharedWeapon.getLevel());
            }
        }

        // Игрок 2 - улучшение оружия
        if (key == KeyEvent.VK_R) {
            if (player2.getCoins() >= GameState.upgradeCost) {
                player2.spendCoins(GameState.upgradeCost);
                GameState.sharedWeapon.upgrade();
                GameState.upgradeCost += 5;
                System.out.println("Оружие улучшено для обоих игроков! Уровень: " +
                        GameState.sharedWeapon.getLevel());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        // Игрок 1
        if (key == KeyEvent.VK_A) {
            aPressed = false;
        }
        if (key == KeyEvent.VK_D) {
            dPressed = false;
        }

        // Игрок 2
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void performAttack(Player player) {
        Rectangle attackBounds = player.getAttackBounds();

        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            if (attackBounds.intersects(enemy.getBounds())) {
                enemy.takeDamage(player.getWeapon().getDamage());
                System.out.println("Нанесен урон! Осталось HP: " + enemy.getHealth());

                if (!enemy.isAlive()) {
                    enemyIterator.remove();
                    // Враг умер - даем монету
                    player.addCoin();
                    System.out.println("Враг убит! +1 монета");

                    // Спавним новую монету на месте врага
                    coins.add(new Coin(enemy.getX(), enemy.getY()));
                }
            }
        }
    }
}