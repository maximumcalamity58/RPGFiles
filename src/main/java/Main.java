package main.java;

import javax.swing.*;
import java.awt.*;

public class Main {
    private final Run run;

    public Main() {
        // Set up the JFrame
        // The JFrame that holds the application's UI
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(800, 450));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main.main.java.Run component and add it to the viewport

        run = new Run();
        frame.addMouseListener(run);
        frame.addKeyListener(run);
        // The panel that holds the main.main.java.Run component
        JPanel panel = run;
        JViewport viewport = new JViewport();
        viewport.setView(panel);
        frame.add(viewport);

        // Set up the JFrame and add the main.main.java.Run component
        frame.setFocusable(true);
        frame.requestFocus();
        frame.add(run);
        frame.setVisible(true);
    }

    /**
     * Starts the application.
     */
    public void start() {
        while (true) {
            // Update the main.main.java.Run component and repaint it
            run.update();
            run.repaint();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The main method of the application.
     *
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}

