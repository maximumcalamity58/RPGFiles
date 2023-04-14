package main.java.terrain;

import main.java.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ground extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/main/assets/ground/town/cobblestone_path.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int x = 0;
        int y = 0;
        int width = Utils.getWidth();
        int height = Utils.getHeight();
        while (y < height) {
            while (x < width) {
                g.drawImage(image, x, y, image.getWidth()*5, image.getWidth()*5, null);
                x += image.getWidth()*5;
            }
            x = 0;
            y += image.getHeight()*5;
        }
    }
}
