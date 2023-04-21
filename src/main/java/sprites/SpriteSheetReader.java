package main.java.sprites;

import jdk.swing.interop.SwingInterOpUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheetReader {
    private BufferedImage spriteSheet;
    private int maxFrames;
    private int currentFrame;
    private long lastFrameTime;
    private int frameWidth;
    private int frameHeight;

    public SpriteSheetReader(String filepath, int frameHeight) {
        try {
            spriteSheet = ImageIO.read(new File(filepath));
            maxFrames = spriteSheet.getHeight() / frameHeight;
            frameWidth = spriteSheet.getWidth();
            this.frameHeight = frameHeight;
            currentFrame = 0;
        } catch (IOException e) {
            System.err.println("Error reading sprite sheet: " + e.getMessage());
        }
    }

    public void readSpriteSheet(int xPos, int yPos, int width, int height, int desiredFps, Graphics g) {
        long timePerFrame = 1000 / desiredFps;
        long timeSinceLastFrame = System.currentTimeMillis() - lastFrameTime;
        if (currentFrame >= maxFrames) {
            currentFrame = maxFrames-1;
        }
        BufferedImage sprite = spriteSheet.getSubimage(0, currentFrame * frameHeight, frameWidth, frameHeight);
        g.drawImage(sprite, xPos, yPos, width, height,null);
        if (timeSinceLastFrame >= timePerFrame) {
            currentFrame = (currentFrame + 1) % maxFrames;
            lastFrameTime = System.currentTimeMillis();
        }
    }

    public void setSpriteSheet(String filepath, int frameHeight) {
        try {
            spriteSheet = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        maxFrames = spriteSheet.getHeight() / frameHeight;
        frameWidth = spriteSheet.getWidth();
        this.frameHeight = frameHeight;
    }
}
