package TicTacToe.src;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {


    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JButton[] buttons = new JButton[9];
    JButton readyButton = new JButton();
    boolean youAreReady = false;
    int setPos;
    boolean player1_turn;
    String playerSide;
    String enemySide;
    Socket socket = new Socket("localhost",5656);
    ClientSide client = new ClientSide(socket);

    TicTacToe() throws IOException {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        readyButton.setFont(new Font("Russo One",Font.BOLD,100));
        readyButton.setText("Start");
        readyButton.setFocusable(false);
        readyButton.setHorizontalAlignment(JLabel.CENTER);
        readyButton.setBackground(new Color(100,100,100));
        readyButton.addActionListener(this);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150,150,150));

        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Russo One",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(new Color(100,100,100));
            buttons[i].addActionListener(this);
            buttons[i].setEnabled(false);
            buttons[i].setForeground(new Color(50,50,50));
            buttons[i].setText("");
        }

        title_panel.add(readyButton);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);

        client.ListenForCommand();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == readyButton) {
            restartGame();
            if(!youAreReady) {
                client.SendCommand("Ready");
                System.out.println("Send a command: ready");
                readyButton.setText("Ready");
                youAreReady = true;
            } else {
                client.SendCommand("NReady");
                System.out.println("Send a command: Nready");
                readyButton.setText("Not ready");
                youAreReady = false;
            }
        }
        readyButton.setBackground(new Color(100,100,100));
        for(int i = 0; i < 9; i++) {
            if(e.getSource() == buttons[i] && player1_turn && buttons[i].getText() == "") {
                client.SendCommand(playerSide + i);
                setPos = i;
            }
        }
    }

    public void DisableButtons(boolean value) {
        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(value);
        }
    }

    public void check(String command) {
        readyButton.setBackground(new Color(100,100,100));
        int a,b,c;
        String winner;
        char[] commandC = command.toCharArray();
        a = Character.getNumericValue(commandC[0]);
        b = Character.getNumericValue(commandC[1]);
        c = Character.getNumericValue(commandC[2]);
        winner = String.valueOf(commandC[3]);
        if(winner.equals("0")) oWins(a,b,c);
        else if(winner.equals("X")) xWins(a,b,c);
        if(winner.equals(playerSide)) readyButton.setText("You Win!");
        else readyButton.setText("You Lose(");
    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(new Color(0,0,255));
        buttons[b].setBackground(new Color(0,0,255));
        buttons[c].setBackground(new Color(0,0,255));
        readyButton.setText("X WINS!");
        readyButton.setEnabled(true);
        readyButton.setText("Restart");
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(new Color(255,0,0));
        buttons[b].setBackground(new Color(255,0,0));
        buttons[c].setBackground(new Color(255,0,0));
        readyButton.setText("0 WINS!");
        readyButton.setEnabled(true);
        readyButton.setText("Restart");
    }

    public void restartGame() {
        for(int i = 0; i < 9 ; i++ ) {
            buttons[i].setText("");
            buttons[i].setBackground(new Color(100,100,100));
            buttons[i].setEnabled(false);
        }
    }

    class ClientSide {
        private Socket clientSocket;
        private BufferedReader in;
        private BufferedWriter out;

        public ClientSide(Socket clientSocket) {
            try {
                this.clientSocket = clientSocket;
                this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                this.out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            } catch (IOException e) {
                closeConnection(clientSocket, in, out);
            }
        }

        public void SendCommand(String command) {
            try {
                out.write(command);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                closeConnection(clientSocket,in,out);
            }
        }

        public void ListenForCommand() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Created new Thread!");
                    String command;
                    while(clientSocket.isConnected()) {
                        try {
                            command = in.readLine();
                            System.out.println("GOT A COMMAND: " + command);
                            CommandChecker(command);
                        } catch (IOException e) {
                            closeConnection(clientSocket,in,out);
                        }
                    }
                }
            }).start();
        }

        public void closeConnection(Socket clientSocket, BufferedReader in, BufferedWriter out) {
            try {
                if(in != null) in.close();
                if(out != null) out.close();
                if(clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void CommandChecker(String command) {
            if(command.equals("X")) {
                playerSide = "X";
                enemySide = "0";
                readyButton.setEnabled(false);
            } else if(command.equals("0")) {
                playerSide = "0";
                enemySide = "X";
                readyButton.setEnabled(false);
            }
            if((command.equals("FX") && playerSide.equals("X")) || (command.equals("F0") && playerSide.equals("0"))) {
                readyButton.setText("Your Turn!");
                player1_turn = true;
                DisableButtons(true);
            } else {
                player1_turn = false;
                readyButton.setText("Enemy Turn!");
                DisableButtons(false);
            }
            if(command.length() == 3) {
                char[] commandC = command.toCharArray();
                String value = String.valueOf(commandC[0]);
                int pos = Character.getNumericValue(commandC[2]);
                buttons[pos].setText(value);
                readyButton.setText("Your Turn!");
                player1_turn = true;
                DisableButtons(true);
            }
            if(command.length() == 4) check(command);
            if(command.equals("Successful")) {
                buttons[setPos].setText(playerSide);
                player1_turn = false;
            }
            if(command.equals("Draw!")) {
                readyButton.setBackground(new Color(100,100,100));
                readyButton.setText("Draw");
                readyButton.setEnabled(true);
                player1_turn = false;
            }
        }

    }
}
