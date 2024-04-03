package org.example.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static org.example.sudoku.SudokuGenerator.printSudoku;

public class TileKeyListener extends KeyAdapter {

    private final Sudoku sudokuGame;
    private final int row;
    private final int col;



    public TileKeyListener(int row, int col, Sudoku sudokuGame) {
        this.row = row;
        this.col = col;
        this.sudokuGame = sudokuGame;
    }



    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c) && (c >= '1' && c <= '9'))) {
            e.consume();
        } else {
            JButton button = (JButton) e.getSource();
            button.setText(Character.toString(c));
            button.setBackground(Color.gray);
            button.setForeground(Color.white);
            int value = Integer.parseInt(Character.toString(c));
            sudokuGame.updateSudoku(row, col, value);
        }
    }





}
