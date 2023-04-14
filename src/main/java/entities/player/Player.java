package main.java.entities.player;

import main.java.Main;
import main.java.Run;
import main.java.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends JPanel {
    private int pX = 550;
    private int pY = 290;
    private int pWidth = 105;
    private int pHeight = 135;
    private int maxHealth = 5;
    private int health = 5;
    private int invincibilityFrames;
    private String filepath = "src/main/assets/player/idle/player_forward.png";
    private double healthPercent;
    private int healthWidth;

    public Player() {}

    public void start() {
        maxHealth = (int) Utils.jsonReadAndWrite("read", "max_health", maxHealth, "int", "src/data/playerdata.json");
        health = maxHealth;
        setImage("src/main/assets/player/idle/player_forward.png");
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

    public void setImage(String filepath) {
        this.filepath = filepath;
    }

    public void damagePlayer(int damage) {
        if (invincibilityFrames > 30) {
            health -= damage;
            invincibilityFrames = 0;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit t = Toolkit.getDefaultToolkit();

        Image currentHealth = t.getImage("src/main/assets/gui/health_bar.png");

        // Draw the appropriate portion of the sprite image
        g.drawImage(currentHealth, 10, 10, 384, 48,null);

        Image playerImage = t.getImage(filepath);
        g.drawImage(playerImage, 550, 290, 105, 135, this);

        g.setColor(Color.GRAY);
        g.fillRect(16, 16, 372, 37);

        System.out.println(getHealth());

        g.setColor(Color.RED);
        g.fillRect(16, 16, healthWidth, 37);
    }

    public void update() {
        invincibilityFrames++;
        healthPercent = (double) health / (double) maxHealth;
        healthWidth = (int) (372.0 * healthPercent);
    }
}
