package org.example.sudoku;

import javax.swing.*;
import java.awt.*;

public class JSetupSudoku {


    protected JButton jButtonSetup(){
        Dimension buttonSize = new Dimension(75, 75);

        JButton button = new JButton();
        button.setBackground(Color.darkGray);
        button.setPreferredSize(buttonSize);
        button.setFont(new Font("Arial", Font.BOLD, 35));
        return button;
    }

    protected JLabel jLabelSetup() {
        JLabel jLabel = new JLabel();
        jLabel.setBackground(Color.BLACK);
        jLabel.setForeground(Color.white);
        jLabel.setFont(new Font("Arial", Font.BOLD, 30));
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setText("Sudoku Game");
        jLabel.setOpaque(true);
        return jLabel;
    }

    protected JFrame jFrameSetup() {
        JFrame jFrame = new JFrame("Sudoku");
        jFrame.setVisible(true);
        jFrame.setSize(700, 800);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());
        return jFrame;
    }

}
