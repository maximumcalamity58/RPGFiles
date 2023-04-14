package main.java.entities.player;

import main.java.Utils;

import java.awt.*;

public class PlayerImages extends Player {
    Player player = new Player();

    @Override
    public void paintComponent(Graphics g) {
        Toolkit t = Toolkit.getDefaultToolkit();

        Image currentHealth = t.getImage("src/main/assets/gui/health_bar.png");

        // Draw the appropriate portion of the sprite image
        g.drawImage(currentHealth, 10, 10, 384, 48,null);

        Image playerImage = t.getImage(player.getFilepath());
        g.drawImage(playerImage, player.getPlayer().x, player.getPlayer().y, player.getPlayer().width, player.getPlayer().height, this);

        g.setColor(Color.GRAY);
        g.fillRect(16, 16, 372, 37);

        g.setColor(Color.RED);
        g.fillRect(16, 16, player.getHealthWidth(), 37);
    }
}
