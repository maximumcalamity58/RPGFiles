package main.java;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheetReader {
    private BufferedImage spriteSheet;
    private int maxFrames;
    private int currentFrame;
    private boolean isPlaying;
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
            isPlaying = true;
        } catch (IOException e) {
            System.err.println("Error reading sprite sheet: " + e.getMessage());
        }
    }

    public void readSpriteSheet(int xPos, int yPos, int desiredFps, Graphics g) {
        if (isPlaying) {
            long timePerFrame = 1000 / desiredFps;
            long timeSinceLastFrame = System.currentTimeMillis() - lastFrameTime;
            BufferedImage sprite = spriteSheet.getSubimage(0, currentFrame * frameHeight, frameWidth, frameHeight);
            g.drawImage(sprite, xPos, yPos, null);
            if (timeSinceLastFrame >= timePerFrame) {
                currentFrame = (currentFrame + 1) % maxFrames;
                lastFrameTime = System.currentTimeMillis();
            }
        }
    }

    public void stopAnim() {
        isPlaying = false;
    }

    public void startAnim() {
        isPlaying = true;
    }
}
