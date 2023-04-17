package main.java.entities;

import main.java.Utils;
import main.java.entities.enemies.Enemy;
import main.java.entities.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class EntityManager extends JPanel implements KeyListener {
    private int xSpeed;
    private int ySpeed;
    private boolean[] keys = new boolean[10];
    Player player = new Player();

    public EntityManager() {

    }

    ArrayList<Enemy> enemies = new ArrayList<>(){{
        add(new Enemy(300, 300, 96, 72, 16, 1, "green_slime"));
    }};

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit t = Toolkit.getDefaultToolkit();

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getType().contains("green_slime")) {
                Image image = t.getImage("src/main/assets/enemies/slime/idle/slime.png");
                g.drawImage(image, enemies.get(i).getRectangle().x, enemies.get(i).getRectangle().y, enemies.get(i).getRectangle().width, enemies.get(i).getRectangle().height, this);
            }
        }
    }

    public void update() {
        for (int i = 0; i < enemies.size(); i++) {
            if (new Point(enemies.get(i).getRectangle().x, enemies.get(i).getRectangle().y).distance(player.getPlayer().x, player.getPlayer().y) < 700) {
                if (enemies.get(i).getType().contains("slime")) {
                    if (enemies.get(i).getJumpCD() <= 0) {
                        enemies.get(i).setJumpCD(110);
                        int x = player.getPlayer().x;
                        int y = player.getPlayer().y + 50;

                        double angleRadians = calculateAngle(x, y, enemies.get(i).getRectangle().x, enemies.get(i).getRectangle().y);
                        xSpeed = (int) (enemies.get(i).getSpeed() * Math.cos(angleRadians));
                        ySpeed = (int) (enemies.get(i).getSpeed() * Math.sin(angleRadians));

                        enemies.get(i).getRectangle().y += ySpeed;
                        enemies.get(i).getRectangle().x += xSpeed;
                    } else if (enemies.get(i).getJumpCD() > 95) {
                        enemies.get(i).getRectangle().y += ySpeed;
                        enemies.get(i).getRectangle().x += xSpeed;
                        enemies.get(i).setJumpCD(enemies.get(i).getJumpCD() - 1);
                    } else {
                        enemies.get(i).setJumpCD(enemies.get(i).getJumpCD() - 1);
                    }
                }
            }

            if (keys[1]) {
                enemies.get(i).getRectangle().x += Player.getSpeed();
            }

            if (keys[3]) {
                enemies.get(i).getRectangle().x -= Player.getSpeed();
            }

            if (keys[2]) {
                enemies.get(i).getRectangle().y -= Player.getSpeed();
            }

            if (keys[0]) {
                enemies.get(i).getRectangle().y += Player.getSpeed();
            }

            if (player.getPlayer().intersects(enemies.get(i).getRectangle())) {
                player.damagePlayer(enemies.get(i).getDamage());
            }
        }

        player.update();
    }

    public double calculateAngle(double x1, double y1, double x2, double y2) {
        double deltaX = x1 - x2;
        double deltaY = y1 - y2;
        return Math.atan2(deltaY, deltaX);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W:
                keys[0] = true;
                if (!keys[2]) {
                    Player.setFilepath("src/main/assets/player/idle/player_backward.png");
                }
                break;

            case KeyEvent.VK_A:
                keys[1] = true;
                if (!(keys[0] || keys[2] || keys[3])) {
                    Player.setFilepath("src/main/assets/player/running/player_left.gif");
                }
                break;

            case KeyEvent.VK_S:
                keys[2] = true;
                if (!keys[0]) {
                    Player.setFilepath("src/main/assets/player/idle/player_forward.png");
                }
                break;

            case KeyEvent.VK_D:
                keys[3] = true;
                if (!(keys[0] || keys[1] || keys[2])) {
                    Player.setFilepath("src/main/assets/player/running/player_right.gif");
                }
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W:
                keys[0] = false;
                if (!(keys[1] || keys[2] || keys[3])) {

                }
                break;

            case KeyEvent.VK_A:
                keys[1] = false;
                if (!(keys[0] || keys[2] || keys[3])) {
                    Player.setFilepath("src/main/assets/player/idle/player_left.png");
                }
                break;

            case KeyEvent.VK_S:
                keys[2] = false;
                if (!(keys[0] || keys[1] || keys[3])) {

                }
                break;

            case KeyEvent.VK_D:
                keys[3] = false;
                if (!(keys[0] || keys[1] || keys[2])) {
                    Player.setFilepath("src/main/assets/player/idle/player_right.png");
                }
                break;
        }
    }
}
