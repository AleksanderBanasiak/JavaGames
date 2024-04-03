package org.example.tictactoe;

import javax.swing.*;
import java.awt.*;

public class TicTacToeGameVerification {

    private boolean gameOver;

    public boolean checkWinner(int turns, JButton[][] board) {
        gameOver = false;

        row(board);

        column(board);

        leftToRight(board);

        rightToLeft(board);

        checkTurns(turns, board);

        return gameOver;
    }


    private void checkTurns(int turns, JButton[][] board) {
        if(turns == 0){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    setTie(board[i][j]);
                }
            }
            gameOver = true;
        }
    }

    private void rightToLeft(JButton[][] board) {
        if(board[0][2].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][0].getText()) &&
                !board[0][2].getText().isEmpty()){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
        }
    }

    private void leftToRight(JButton[][] board) {
        if (board[0][0].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][2].getText()) &&
                !board[0][0].getText().isEmpty()){
            for (int j = 0; j < 3; j++) {
                setWinner(board[j][j]);
            }
            gameOver = true;
        }
    }

    private void column(JButton[][] board) {
        for (int i = 0; i < 3; i++) {
            if(board[0][i].getText().isEmpty()) continue;
            if(board[0][i].getText().equals(board[1][i].getText()) &&
                    board[1][i].getText().equals(board[2][i].getText())) {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[j][i]);
                }
                gameOver = true;
            }
        }
    }

    private void row(JButton[][] board) {
        for (int i = 0; i < 3; i++) {
            if(board[i][0].getText().isEmpty()) continue;
            if(board[i][0].getText().equals(board[i][1].getText()) &&
                    board[i][1].getText().equals(board[i][2].getText())) {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[i][j]);
                }
                gameOver = true;
            }
        }
    }

    private void setTie(JButton jButton) {
        jButton.setForeground(Color.red);
    }

    private void setWinner(JButton jButton) {
        jButton.setForeground(Color.green);
    }





}
