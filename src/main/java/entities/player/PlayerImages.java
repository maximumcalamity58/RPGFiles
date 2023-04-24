package main.java.entities.player;

import main.java.Utils;
import main.java.sprites.SpriteSheetReader;

import java.awt.*;

public class PlayerImages extends Player {
    Player player = new Player();
    SpriteSheetReader playerAnim = new SpriteSheetReader(getFilepath(), 36);

    @Override
    public void paintComponent(Graphics g) {
        Toolkit t = Toolkit.getDefaultToolkit();
        playerAnim.setSpriteSheet(Player.getFilepath(), 36);

        Image currentHealth = t.getImage("src/main/assets/gui/health_bar.png");

        // Draw the appropriate portion of the sprite image
        g.drawImage(currentHealth, 10, 10, 384, 48,null);

        playerAnim.readSpriteSheet(player.getPlayer().x, player.getPlayer().y, 84, 108, getSpeed(), g);

        g.setColor(Color.GRAY);
        g.fillRect(16, 16, 372, 37);

        g.setColor(Color.RED);
        g.fillRect(16, 16, Player.getHealthWidth(), 37);
    }
}
