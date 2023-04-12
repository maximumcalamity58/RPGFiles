package main.java.entities.player;

import main.java.Main;
import main.java.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends JPanel {
    private int pX = 0;
    private int pY = 0;
    private int pWidth = 105;
    private int pHeight = 135;
    private int maxHealth;
    private int health;

    public Player() {}

    public void start() {
        maxHealth = (int) Utils.jsonReadAndWrite("read", "max_health", maxHealth, "int", "src/data/playerdata.json");
    }

    public Rectangle getPlayer() {
        return new Rectangle(pX, pY, pWidth, pHeight);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int i) {
        maxHealth = i;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int i) {
        health = i;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit t = Toolkit.getDefaultToolkit();

        Image head = t.getImage("src/main/assets/player/idle/head.png");
        Image chest = t.getImage("src/main/assets/player/idle/chest.png");
        Image legs = t.getImage("src/main/assets/player/idle/legs.png");

        g.drawImage(head, 550, 290, pWidth, pHeight, this);
        g.drawImage(chest, 550, 290, pWidth, pHeight, this);
        g.drawImage(legs, 550, 290, pWidth, pHeight, this);
    }

    public void update() {
        System.out.println(maxHealth);
    }
}
