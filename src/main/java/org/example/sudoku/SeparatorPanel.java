package org.example.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class SeparatorPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2)); // Grubość linii
        // Rysowanie kreski poziomej
        g2d.draw(new Line2D.Float(0, getHeight() / 2, getWidth(), getHeight() / 2));
        // Rysowanie kreski pionowej
        g2d.draw(new Line2D.Float(getWidth() / 2, 0, getWidth() / 2, getHeight()));
        g2d.dispose();
    }
}
