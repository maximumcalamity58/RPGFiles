package main.java;

import main.java.entities.EntityManager;
import main.java.entities.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Run extends JPanel implements MouseListener, KeyListener {
    private Player player = new Player();
    private EntityManager entityManager = new EntityManager();

    public Run() {
        addMouseListener(this);
        addKeyListener(this);
        addKeyListener(entityManager);
        setLayout(null);
        player.start();

        setFocusable(true);
        grabFocus();
    }

    public void update() {
        player.update();
        entityManager.update();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        entityManager.paintComponent(g);
        player.paintComponent(g);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        entityManager.keyPressed(e);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        entityManager.keyReleased(e);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
