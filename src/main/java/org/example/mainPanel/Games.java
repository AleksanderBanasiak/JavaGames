package org.example.mainPanel;

import org.example.snake.SnakeSetup;
import org.example.sudoku.Sudoku;
import org.example.tictactoe.TicTacToe;

import javax.swing.*;
import java.awt.*;

public class Games {

    JSetup jSetup = new JSetup();
    JFrame jFrame = jSetup.jFrameSetup();
    JPanel jPanel = new JPanel();
    JLabel jLabel = jSetup.jLabelSetup();
    JPanel gamesPanel = new JPanel();
    private final JButton[][] gamesBoard = new JButton[2][2];

    public Games() {
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jLabel);
        jFrame.add(jPanel, BorderLayout.NORTH);

        gamesPanel.setLayout(new GridLayout(2, 2, 2, 2));
        gamesPanel.setBackground(Color.DARK_GRAY);

        jFrame.add(gamesPanel);

        gamesSetup();

        jFrame.pack();
    }

    private void gamesSetup() {
        String[] gameNames = {"Tic Tac Toe", "Snake", "Sudoku", "Exit"};

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                JButton game = jSetup.gameSetup();
                gamesBoard[i][j] = game;
                game.setPreferredSize(new Dimension(350, 310));
                game.setFont(new Font("Arial", Font.BOLD, 35));

                game.setText(gameNames[i * 2 + j]);
                gamesPanel.add(game);

                button(game);
            }
        }
    }

    private void button(JButton game) {
        game.addActionListener(e ->{

            JButton chosenGame = (JButton) e.getSource();

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if(chosenGame == gamesBoard[i][j]){
                        switch (i * 2 + j) {
                            case 0 -> {
                                jFrame.dispose();
                                new TicTacToe();
                            }
                            case 1 -> {
                                new SnakeSetup();
                                jFrame.dispose();
                            }
                            case 2 -> {
                                new Sudoku();
                                jFrame.dispose();
                            }
                            case 3 -> System.exit(0);
                        }
                    }
                }
            }
        });
    }
}
