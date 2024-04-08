package org.example.sudoku;

import org.example.mainPanel.GamesManager;

import javax.swing.*;
import java.awt.*;

import static org.example.sudoku.SudokuGenerator.*;

public class Sudoku {

    private final JSetupSudoku jSetupSudoku;
    private final JFrame jFrame;
    private final JLabel jLabel;
    private final JPanel jPanel;
    private JPanel boardPanel;
    private int[][] sudoku;
    private int[][] removedCells;

    GamesManager gamesManager =new GamesManager();

    public Sudoku() {
        jSetupSudoku = new JSetupSudoku();
        jFrame = jSetupSudoku.jFrameSetup();
        jLabel = jSetupSudoku.jLabelSetup();
        jPanel = new JPanel(new BorderLayout());
        resetGame();
        gamesManager.backToGames(jFrame, jPanel);
    }


    private void resetGame() {
        boardPanel = new JPanel(new GridLayout(3, 3));
        sudoku = generateSudoku();
        removedCells = removeSomeCells(sudoku);
        sudokuGame();
    }
    private void sudokuGame() {
        setupPanel();
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                JPanel blockPanel = getjPanel(blockRow, blockCol);
                boardPanel.add(blockPanel);
                for (int i = blockRow * 3; i < blockRow * 3 + 3; i++) {
                    for (int j = blockCol * 3; j < blockCol * 3 + 3; j++) {
                        game(i, j, blockPanel);
                    }
                }
            }
        }
        jFrame.add(boardPanel, BorderLayout.CENTER);
        jFrame.pack();
    }

    private void setupPanel() {
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jLabel);
        jFrame.add(jPanel, BorderLayout.NORTH);
    }

    private static JPanel getjPanel(int blockRow, int blockCol) {
        JPanel blockPanel = new JPanel(new GridLayout(3, 3));
        blockPanel.setBorder(BorderFactory.createMatteBorder(
                blockRow == 0 ? 1 : 0,
                blockCol == 0 ? 1 : 0,
                1, 1, Color.BLUE));
        return blockPanel;
    }

    private void game(int i, int j, JPanel blockPanel) {
        JButton button = jSetupSudoku.jButtonSetup();
        button.addKeyListener(new TileKeyListener(i, j, this));
        TileMouseListener listener = new TileMouseListener(this);
        blockPanel.add(button);

        if (removedCells[i][j] != 0) {
            button.setText(Integer.toString(removedCells[i][j]));
            button.setEnabled(false);
        }
        else {
            button.addMouseListener(listener);
        }
    }
    private JButton lastHighlightedButton;

    public JButton getLastHighlightedButton() {
        return lastHighlightedButton;
    }

    public void setLastHighlightedButton(JButton button) {
        lastHighlightedButton = button;
    }

    private boolean isSudokuCompleted() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (removedCells[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void addExtraButton() {
        JButton extraButton = new JButton("Check the solution");
        extraButton.addActionListener(e -> checkSudoku());
        extraButton.setForeground(Color.white);
        extraButton.setBackground(Color.black);
        jPanel.add(extraButton, BorderLayout.EAST);

    }

    public void updateSudoku(int row, int col, int value) {
        removedCells[row][col] = value;
        if (isSudokuCompleted()) {
            addExtraButton();
        }
    }

    private void checkSudoku() {
        jFrame.remove(boardPanel);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] != removedCells[i][j]) {
                    JOptionPane.showMessageDialog(jFrame, "Incorrect solution!");
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(jFrame, "Correct solution!");
        jFrame.revalidate();
        jFrame.repaint();
        resetGame();
    }






}
