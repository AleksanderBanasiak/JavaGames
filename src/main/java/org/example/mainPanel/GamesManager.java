package org.example.mainPanel;

import javax.swing.*;
import java.awt.*;

public class GamesManager {

    public void backToGames(JFrame jFrame, JPanel jPanel) {
        JButton backToGames = new JButton("Back");
        backToGames.setBackground(Color.BLACK);
        backToGames.setForeground(Color.WHITE);
        backToGames.addActionListener(e ->{
            new Games();
            jFrame.dispose();
        } );
        jPanel.add(backToGames, BorderLayout.WEST);
        backToGames.setVisible(true);
    }

}
