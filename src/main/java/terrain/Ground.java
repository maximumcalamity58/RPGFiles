package main.java.terrain;

import main.java.Utils;
import main.java.entities.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;
import java.awt.im.InputMethodHighlight;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

public class Ground extends JPanel implements KeyListener {
    int x = -120;
    int y = -120;
    int xMod = -120;
    int yMod = -120;
    boolean[] keys = new boolean[4];

    public void update() {
        if (keys[0]) {
            yMod = ((yMod + Player.getSpeed()) % 120) - 120;
        }
        if (keys[1]) {
            xMod = ((xMod + Player.getSpeed()) % 120) - 120;
        }
        if (keys[2]) {
            yMod = ((yMod - Player.getSpeed()) % 120) - 120;
        }
        if (keys[3]) {
            xMod = ((xMod - Player.getSpeed()) % 120) - 120;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit t = Toolkit.getDefaultToolkit();

        Image image = t.getImage("src/main/assets/ground/town/cobblestone_path.png");

        int width = Utils.getWidth();
        int height = Utils.getHeight();
        y = yMod;
        while (y < height) {
            x = xMod;
            while (x < width) {
                g.drawImage(image, x, y, image.getWidth(this)*5, image.getWidth(this)*5, null);
                x += image.getWidth(this)*5;
            }
            y += image.getHeight(this)*5;
        }
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
