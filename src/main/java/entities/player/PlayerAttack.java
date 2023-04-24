package main.java.entities.player;

import main.java.entities.EntityManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PlayerAttack extends EntityManager {
    @Override
    public void mouseClicked(MouseEvent e) {
        Point mouse = MouseInfo.getPointerInfo().getLocation();
    }
}
