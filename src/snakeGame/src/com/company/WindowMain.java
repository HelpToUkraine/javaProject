package snakeGame.src.com.company;

import javax.swing.*;
import java.io.IOException;

public class WindowMain extends JFrame {

    public static void main(String[] args) throws IOException {
        WindowMain winmain  = new WindowMain();
    }

    public WindowMain() throws IOException {
        setTitle("Snake v.1.0");
        setSize(369, 392);
        setLocation(600, 300);
        add(new PlayingField());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
    }


}
