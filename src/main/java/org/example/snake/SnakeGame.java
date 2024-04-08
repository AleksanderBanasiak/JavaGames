package org.example.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener, MouseListener  {
    private final int panelWidth;
    private final int panelHeight;
    private final int tileSize = 25;
    private final ArrayList<Tile> snakeBody = new ArrayList<>();
    private Tile snake;
    private final Tile apple;
    private final Timer gameLoop;
    private int velocityX = 0;
    private int velocityY = 0;

    private boolean gameOver = false;

    public SnakeGame(int panelWidth, int panelHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;

        setPreferredSize(new Dimension(this.panelWidth, this.panelHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);

        Random random = new Random();

        snake = new Tile(random.nextInt(panelWidth / tileSize), random.nextInt(panelHeight / tileSize));
        apple = new Tile(random.nextInt(panelWidth / tileSize), random.nextInt(panelHeight / tileSize));

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    private void drawGame(Graphics g) {
        drawGrid(g);
        drawApple(g);
        drawSnake(g);
        drawScore(g);
        checkGameOver();
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.GRAY);
        for (int i = 0; i < panelWidth / tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, panelHeight);
            g.drawLine(0, i * tileSize, panelWidth, i * tileSize);
        }
    }

    private void drawApple(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(apple.x * tileSize, apple.y * tileSize, tileSize, tileSize);
    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(snake.x * tileSize, snake.y * tileSize, tileSize, tileSize);

        for (Tile snakePart : snakeBody) {
            g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize);
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Score: " + snakeBody.size(), tileSize - 16, tileSize);
    }

    private void checkGameOver() {
        if (gameOver) {
            int choice = JOptionPane.showConfirmDialog(this, "Game over! Your score: " + snakeBody.size() + ". Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                restartGame();
            } else {
                System.exit(0);
            }
        }
    }

    public void placeApple() {
        Random random = new Random();
        apple.x = random.nextInt(panelWidth / tileSize);
        apple.y = random.nextInt(panelHeight / tileSize);
    }

    public void move() {
        if (collision(snake, apple)) {
            snakeBody.add(new Tile(apple.x, apple.y));
            placeApple();
        }

        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.x = snake.x;
                snakePart.y = snake.y;
            } else {
                Tile previousSnakePart = snakeBody.get(i - 1);
                snakePart.x = previousSnakePart.x;
                snakePart.y = previousSnakePart.y;
            }
        }

        snake.x += velocityX;
        snake.y += velocityY;

        for (Tile snakePart : snakeBody) {
            if (collision(snake, snakePart)) {
                gameOver = true;
                break;
            }
        }

        if (snake.x < 0 || snake.x >= panelWidth / tileSize || snake.y < 0 || snake.y >= panelHeight / tileSize) {
            gameOver = true;
        }
    }

    private boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if (gameOver) {
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (velocityY != 1) {
                    velocityX = 0;
                    velocityY = -1;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (velocityY != -1) {
                    velocityX = 0;
                    velocityY = 1;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (velocityX != 1) {
                    velocityX = -1;
                    velocityY = 0;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (velocityX != -1) {
                    velocityX = 1;
                    velocityY = 0;
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    private void restartGame() {
        snake = new Tile(panelWidth / tileSize / 2, panelHeight / tileSize / 2);
        snakeBody.clear();
        velocityX = 0;
        velocityY = 0;
        placeApple();
        gameOver = false;
        gameLoop.start();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameOver) {
            restartGame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}




}