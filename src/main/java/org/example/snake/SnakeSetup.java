package org.example.snake;

import org.example.mainPanel.GamesManager;

import javax.swing.*;
import java.awt.*;

public class SnakeSetup {

    JSetupSnake jSetupSnake = new JSetupSnake();
    JFrame jFrame = jSetupSnake.jFrameSetup();
    JPanel jPanel = new JPanel();
    JLabel jLabel = jSetupSnake.jLabelSetup();
    GamesManager gamesManager =new GamesManager();

    public SnakeSetup() {
        SnakeGame snakeGame = new SnakeGame(700, 700);
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jLabel);
        jFrame.add(jPanel, BorderLayout.NORTH);
        jFrame.add(snakeGame);
        jFrame.pack();
        snakeGame.requestFocus();
        gamesManager.backToGames(jFrame, jPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SnakeSetup::new);
    }

}
