package javagame;

public class Weapon {
    private String name;
    private int level;
    private int damage;

    public Weapon(String name) {
        this.name = name;
        this.level = 1;
        this.damage = 10;
    }

    public void upgrade() {
        this.level++;
        this.damage += 5;
    }

    public int getDamage() {
        return damage;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}