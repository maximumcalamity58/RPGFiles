package main.java.entities.enemies;

import javax.swing.*;
import java.awt.*;

public class Enemy extends JPanel {
    private Rectangle enemy;
    private String type;
    private int speed;
    private int jumpCD = 60;

    public Enemy() {}

    public Enemy(int posX, int posY, int width, int height, int speed, String type) {
        this.type = type;
        this.speed = speed;
        enemy = new Rectangle(posX, posY, width, height);
    }

    public Rectangle getRectangle() {
        return enemy;
    }

    public String getType() {
        return type;
    }

    public int getSpeed() {
        return speed;
    }

    public int getJumpCD() {
        return jumpCD;
    }

    public void setJumpCD(int i) {
        jumpCD = i;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void update() {

    }
}
