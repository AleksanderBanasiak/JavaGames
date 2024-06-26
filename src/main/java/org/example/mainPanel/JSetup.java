package org.example.mainPanel;

import javax.swing.*;
import java.awt.*;

public class JSetup {
    protected JButton gameSetup() {
        JButton tile = new JButton();
        tile.setBackground(Color.BLACK);
        tile.setForeground(Color.white);
        tile.setFont(new Font("Arial", Font.BOLD, 160));
        tile.setFocusable(false);
        return tile;
    }
    protected JLabel jLabelSetup() {
        JLabel jLabel = new JLabel();
        jLabel.setBackground(Color.BLACK);
        jLabel.setForeground(Color.white);
        jLabel.setFont(new Font("Arial", Font.BOLD, 50));
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setText("Choose Game");
        jLabel.setOpaque(true);
        return jLabel;
    }
    protected JFrame jFrameSetup() {
        JFrame jFrame = new JFrame("Simple games by Aleksander Banasiak");
        jFrame.setVisible(true);
        jFrame.setSize(700, 800);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());
        return jFrame;
    }
}
