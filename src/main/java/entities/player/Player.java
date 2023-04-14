package main.java.entities.player;

import main.java.Main;
import main.java.Run;
import main.java.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends JPanel {
    private static int pX;
    private static int pY;
    private int pWidth = 105;
    private int pHeight = 135;
    private int maxHealth = 5;
    private int health = 5;
    private int invincibilityFrames;
    private String filepath = "src/main/assets/player/idle/player_forward.png";
    private double healthPercent;
    private int healthWidth;
    private boolean started = false;

    public Player() {}

    public void start() {
        if (!started && Utils.getWidth() != 0) {
            maxHealth = (int) Utils.jsonReadAndWrite("read", "max_health", maxHealth, "int", "src/data/playerdata.json");
            health = maxHealth;
            started = true;
        }
    }

    public static int getpX() {
        return pX;
    }

    public static int getpY() {
        return pY;
    }

    public Rectangle getPlayer() {
        return new Rectangle(pX, pY, pWidth, pHeight);
    }

    public int getHealthWidth() {
        return healthWidth;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
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

    public void damagePlayer(int damage) {
        if (invincibilityFrames > 30) {
            health -= damage;
            invincibilityFrames = 0;
        }
    }

    public void update() {
        pX = (Utils.getWidth() - pWidth) / 2;
        pY = (Utils.getHeight() - pHeight) / 2;

        invincibilityFrames++;
        healthPercent = (double) health / (double) maxHealth;
        healthWidth = (int) (372.0 * healthPercent);
    }
}
