package main.java;

import main.java.entities.EntityManager;
import main.java.entities.player.Player;
import main.java.entities.player.PlayerImages;
import main.java.terrain.Ground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Run extends JPanel implements MouseListener, KeyListener {
    private Player player = new Player();
    private PlayerImages playerImages = new PlayerImages();
    private EntityManager entityManager = new EntityManager();
    private Ground ground = new Ground();

    public Run() {
        addMouseListener(this);
        addKeyListener(this);
        setLayout(null);

        setFocusable(true);
        grabFocus();
    }

    public void update() {
        player.start();
        entityManager.update();
        ground.update();
        Utils.setWidth(getWidth());
        Utils.setHeight(getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ground.paintComponent(g);
        entityManager.paintComponent(g);
        playerImages.paintComponent(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        entityManager.keyPressed(e);
        ground.keyPressed(e);
//        ground.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        entityManager.keyReleased(e);
        ground.keyReleased(e);
//        ground.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        entityManager.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
