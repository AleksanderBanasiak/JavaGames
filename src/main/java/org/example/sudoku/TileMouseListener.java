package org.example.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TileMouseListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setBackground(Color.gray);
    }



}
