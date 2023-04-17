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
    private static int pWidth = 105;
    private static int pHeight = 135;
    private static int maxHealth = 5;
    private static int health = 5;
    private static int speed = 3;
    private static int invincibilityFrames;
    private static String filepath /*= "src/main/assets/player/idle/player_forward.png"*/;
    private Rectangle rect = new Rectangle();
    private double healthPercent;
    private static int healthWidth;
    private boolean started = false;

    public Player() {
        filepath = "src/main/assets/player/idle/player_forward.png";
    }

    public void start() {
        if (!started && Utils.getWidth() != 0) {
            maxHealth = (int) Utils.jsonReadAndWrite("read", "max_health", maxHealth, "static int", "src/data/playerdata.json");
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

    public Rectangle getRect() {
        return rect;
    }

    public static int getSpeed() {
        return speed;
    }

    public Rectangle getPlayer() {
        return new Rectangle(pX, pY, pWidth, pHeight);
    }

    public static int getHealthWidth() {
        return healthWidth;
    }

    public static String getFilepath() {
        return filepath;
    }

    public static void setFilepath(String fp) {
        filepath = fp;
    }

    public static int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int i) {
        maxHealth = i;
    }

    public static int getHealth() {
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
