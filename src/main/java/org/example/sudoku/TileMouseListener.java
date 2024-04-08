package org.example.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TileMouseListener extends MouseAdapter {

    private static final Color HIGHLIGHT_COLOR = Color.gray;
    private static final Color NORMAL_COLOR = Color.DARK_GRAY;

    private final Sudoku sudoku;

    public TileMouseListener(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton button = (JButton) e.getSource();

        if (sudoku.getLastHighlightedButton() != null && sudoku.getLastHighlightedButton() != button) {
            sudoku.getLastHighlightedButton().setBackground(NORMAL_COLOR);
            sudoku.getLastHighlightedButton().repaint();
        }

        if (button.getBackground() == HIGHLIGHT_COLOR) {
            button.setBackground(NORMAL_COLOR);
        } else {
            button.setBackground(HIGHLIGHT_COLOR);
        }
        button.repaint();

        sudoku.setLastHighlightedButton(button);
    }


}
