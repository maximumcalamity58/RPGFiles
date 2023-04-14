package main.java.entities;

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
                        int x = 550;
                        int y = 340;

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

            if (keys[0]) {
                enemies.get(i).getRectangle().y += 3;
                player.setImage("src/main/assets/player/idle/player_forward.png");
            }
            if (keys[1]) {
                enemies.get(i).getRectangle().x += 3;
                player.setImage("src/main/assets/player/idle/player_left.png");
            }
            if (keys[2]) {
                enemies.get(i).getRectangle().y -= 3;
                player.setImage("src/main/assets/player/idle/player_right.png");
            }
            if (keys[3]) {
                enemies.get(i).getRectangle().x -= 3;
                player.setImage("src/main/assets/player/idle/player_forward.png");
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
                break;

            case KeyEvent.VK_A:
                keys[1] = true;
                break;

            case KeyEvent.VK_S:
                keys[2] = true;
                break;

            case KeyEvent.VK_D:
                keys[3] = true;
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W:
                keys[0] = false;
                break;

            case KeyEvent.VK_A:
                keys[1] = false;
                break;

            case KeyEvent.VK_S:
                keys[2] = false;
                break;

            case KeyEvent.VK_D:
                keys[3] = false;
                break;
        }
    }
}
