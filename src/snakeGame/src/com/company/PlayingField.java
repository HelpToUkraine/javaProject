package snakeGame.src.com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class PlayingField extends JPanel implements ActionListener {
    //***************
    private final int Size = 320;
    private final int SizeDot = 16;
    private final int MaxDot = 400;
    private Timer timer;
    private int inGame = 2;
    private Image back;
    JButton rest;
    JButton start;
    //***************
    private boolean right = true;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;
    //***************
    private int X_apple;
    private int Y_apple;
    private Image apple;
    //***************
    private int[] X_snake = new int[MaxDot];
    private int[] Y_snake = new int[MaxDot];
    private Image snake;
    private int SizeSnake;
    //***************

    public PlayingField() throws IOException {
        imageUpload();
        initializeGame();
        addKeyListener(new FieldKeyListener());
        startButton();
        restartButton();
        setFocusable(true);
    }

    public void imageUpload() throws IOException {
        ImageIcon iApple = new ImageIcon("src/images/apple.png");
        ImageIcon iSnake = new ImageIcon("src/images/snake.png");
        ImageIcon bk = new ImageIcon("src/images/back.png");
        BufferedImage restIcon = ImageIO.read(new File("src/images/restart.png"));
        BufferedImage startIcon = ImageIO.read(new File("src/images/start.png"));
        rest = new JButton(new ImageIcon(restIcon));
        start = new JButton(new ImageIcon(startIcon));
        apple = iApple.getImage();
        snake = iSnake.getImage();
        back = bk.getImage();
    }

    public void initializeGame(){
        SizeSnake = 3;
        for (int i = 0; i < SizeSnake; i++) {
            X_snake[i] = 80 - i * SizeDot;
            Y_snake[i] = 64;
        }
        timer = new Timer(250,this);
        timer.start();
        spawnApple();
    }

    public void spawnApple(){
        X_apple = 32 + new Random().nextInt(18) * SizeDot;
        Y_apple = 32 + new Random().nextInt(18) * SizeDot;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(back, 0, 0, this);
        String creator = "game creator Danil Belіy";
        g.setColor(Color.GREEN);
        g.drawString(creator, 220, 349);
        if (inGame == 1){
            for (int i = 0; i < SizeSnake; i++) {
                g.drawImage(snake, X_snake[i], Y_snake[i], this);
            }
            g.drawImage(apple, X_apple, Y_apple, this);
        } else if ( inGame == 0) {
            String str = "Game Over";
            Font f2 = new Font("Bahnschrift", Font.BOLD, 22);
            g.setColor(Color.gray);
            g.setFont(f2);
            g.drawString(str, 113, 150);
        }
    }

    public void movement(){
        for (int i = SizeSnake; i > 0  ; i--) {
            X_snake[i] = X_snake[i-1];
            Y_snake[i] = Y_snake[i-1];
        }
        if(left){
            X_snake[0] -= SizeDot;
        }
        if(right){
            X_snake[0] += SizeDot;
        }
        if(up){
            Y_snake[0] -= SizeDot;
        }
        if(down){
            Y_snake[0] += SizeDot;
        }
    }

    public void checkApple(){
        if(X_snake[0] == X_apple && Y_snake[0] == Y_apple){
            SizeSnake++;
            spawnApple();
        }
    }
    public void checkClash(){
        for (int i = SizeSnake; i > 0; i--) {
            if (i>4 && X_snake[0] == X_snake[i] && Y_snake[0] == Y_snake[i]){
                inGame = 0;
            }
        }
        if (X_snake[0] > Size - 16){
            inGame = 0;
        }
        if (X_snake[0] < 26){
            inGame = 0;
        }
        if (Y_snake[0] > Size - 16){
            inGame = 0;
        }
        if (Y_snake[0] < 26){
            inGame = 0;
        }
    }

    public void startButton() {
        start.setBorderPainted(false);
        start.setFocusPainted(false);
        start.setContentAreaFilled(false);
        start.setBounds(110, 150, 130, 35);
        start.addActionListener(this);
    }

    public void restartButton() {
        rest.setBorderPainted(false);
        rest.setFocusPainted(false);
        rest.setContentAreaFilled(false);
        rest.setBounds(118, 180, 110, 25);
        rest.addActionListener(this);
    }

    public void restartGame(){
        inGame = 1;
        SizeSnake = 3;
        right = true;
        left = false;
        up = false;
        down = false;
        for (int i = 0; i < SizeSnake; i++) {
            X_snake[i] = 80 - i * SizeDot;
            Y_snake[i] = 64;
        }
        spawnApple();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame == 1){
            movement();
            checkApple();
            checkClash();
        } else if(inGame == 2) {
            add(start);
        } else if(inGame == 0){
            add(rest);
        }
        if(e.getSource()==start){
            inGame = 1;
            remove(start);
        }
        if(e.getSource()==rest){
            restartGame();
            remove(rest);
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_UP && !down){
                right = false;
                up = true;
                left = false;
            }
            if (key == KeyEvent.VK_DOWN && !up){
                left = false;
                right = false;
                down = true;
            }
        }
    }
}
