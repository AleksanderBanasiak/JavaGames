package org.example.tictactoe;

import javax.swing.*;
import java.awt.*;

public class TicTacToe {
    JSetup setup = new JSetup();
    TicTacToeGameVerification verification = new TicTacToeGameVerification();
    JFrame jFrame = setup.jFrameSetup();
    JLabel jLabel = setup.jLabelSetup();
    JPanel jPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    private final JButton[][] board = new JButton[3][3];
    private final String playerX = "X";
    private final String playerO = "O";
    private String player = playerX;
    private boolean gameOver = false;
    private static int turns = 9;
    private final JButton newGameButton =new JButton("New Game");

    public TicTacToe() {
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jLabel);
        jFrame.add(jPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        jFrame.add(boardPanel);

        boardSetup();
        setupNewGameButton();
    }
    private void boardSetup() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = setup.tileSetup();
                board[i][j] = tile;
                boardPanel.add(tile);

                game(tile);
            }
        }
    }
    private void game(JButton tile) {
        newGameButton.setVisible(false);
        tile.addActionListener(e -> {
            if (gameOver) return;
            JButton tile1 = (JButton) e.getSource();
            if (tile1.getText().isEmpty()) {
                tile1.setText(player);

                turns--;
                gameOver = verification.checkWinner(turns, board);

                if (!gameOver) {
                    player = player.equals(playerX) ? playerO : playerX;
                    jLabel.setText(player + "'s turn");
                }
                gameEndScore();
            }
        });
    }
    private void gameEndScore() {
        if(gameOver){
            if(turns == 0){
                jLabel.setText("Tie!");
                newGameButton.setVisible(true);
            }else {
                jLabel.setText("      "+player+ " wins!");
                newGameButton.setVisible(true);
            }
        }
    }
    private void setupNewGameButton() {
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(Color.WHITE);
        newGameButton.addActionListener(e -> resetGame());
        jPanel.add(newGameButton, BorderLayout.EAST);
    }
    private void resetGame() {
        turns = 9;
        gameOver = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
                board[i][j].setForeground(Color.white);
                game(board[i][j]);
            }
        }
        player = playerX;
        jLabel.setText(player + "'s turn");
    }
}