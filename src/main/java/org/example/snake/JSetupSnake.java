package org.example.snake;

import javax.swing.*;
import java.awt.*;

public class JSetupSnake {


    protected JFrame jFrameSetup(){
        JFrame jFrame = new JFrame("Snake");
        jFrame.setVisible(true);
        jFrame.setSize(700, 800);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());
        return jFrame;
    }


    protected JLabel jLabelSetup() {
        JLabel jLabel = new JLabel();
        jLabel.setBackground(Color.BLACK);
        jLabel.setForeground(Color.white);
        jLabel.setFont(new Font("Arial", Font.BOLD, 50));
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setText("Snake Game");
        jLabel.setOpaque(true);
        return jLabel;
    }


}
