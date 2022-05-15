package TicTacToe.src.online.mod.com;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private static ArrayList<ClientHandler> connections = new ArrayList<>();
    private BufferedReader in;
    private BufferedWriter out;
    private String playerSide;
    private static String playerTurn;
    private static boolean winnerIsExist = false;
    private boolean player_is_ready = false;
    private static String[] blocks = new String[9];

    public ClientHandler(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            connections.add(this);
        } catch (IOException e) {
            closeConnection(clientSocket,in,out);
        }
    }

    @Override
    public void run() {
        String command;
        while(clientSocket.isConnected()) {
            try {
                command = in.readLine();
                CommandChecker(command);
            } catch (IOException e) {
                closeConnection(clientSocket,in,out);
            }
        }
    }

    public void closeConnection(Socket clientSocket, BufferedReader in, BufferedWriter out) {
        removeClientHandler();
        System.out.println("Client disconnected");
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeClientHandler() {
        connections.remove(this);
    }

    public void CommandChecker(String command) {
        if(command.equals("Exit")) closeConnection(clientSocket,in,out);
        if(command.equals("Ready")) {
            player_is_ready = true;
            System.out.println("Player " + playerSide + " is ready!");
            int totalReady = 0;
            for(ClientHandler clientHandler : connections) {
                if(clientHandler.player_is_ready) totalReady++;
            }
            if(totalReady == 2) givePlayerSide();
        }
        if(command.equals("NReady")) {
            player_is_ready = false;
            System.out.println("Player " + playerSide + " is not ready now");
        }
        if(command.length() == 2) {
            char[] commandC = command.toCharArray();
            String value = String.valueOf(commandC[0]);
            int pos = Character.getNumericValue(commandC[1]);
            if(blocks[pos].equals("-") && playerSide.equals(playerTurn) && value.equals(playerTurn)) {
                blocks[pos] = value;
                System.out.println("Block [" + pos + "] field by " + value + "\n" + blocks[pos]);
                SendCommandToPLayer("Successful");
                SendCommandToEnemy(value + "-" + pos);
                if(playerTurn.equals("X")) playerTurn = "0";
                else playerTurn = "X";
            } else System.out.println("Cant fill Block[" + pos + "]");
            check();
        }
        if(command.equals("Pool")) ShowPool();
    }

    public void SendCommandToPLayers(String command) {
        for(ClientHandler clientHandler : connections) {
            try {
                clientHandler.out.write(command);
                clientHandler.out.newLine();
                clientHandler.out.flush();
            } catch (IOException e) {
                closeConnection(clientSocket, in, out);
            }
        }
    }

    public void SendCommandToPLayer(String command) {
        try {
            out.write(command);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            closeConnection(clientSocket, in, out);
        }
    }

    public void SendCommandToEnemy(String command) {
        for(ClientHandler clientHandler : connections) {
            if(clientHandler.playerSide != playerSide) {
                try {
                    clientHandler.out.write(command);
                    clientHandler.out.newLine();
                    clientHandler.out.flush();
                } catch (IOException e) {
                    closeConnection(clientSocket, in, out);
                }
            }
        }
    }

    public void givePlayerSide() {
        ClearPool();
        ShowPool();
        String[] PS = new String[2];
        System.out.println("PLAYERS ARE READY");
        ShowPool();
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            PS[0] = "X";
            PS[1] = "0";
        } else {
            PS[0] = "0";
            PS[1] = "X";
        }
        try {
            int i = 0;
            for (ClientHandler clientHandler : connections) {
                clientHandler.out.write(PS[i]);
                clientHandler.out.newLine();
                clientHandler.out.flush();
                clientHandler.player_is_ready = false;
                clientHandler.playerSide = PS[i];
                i++;
            }
        } catch (IOException e) {
            closeConnection(clientSocket, in, out);
        }
        if(random.nextInt(2) == 1) playerTurn = "X";
        else playerTurn = "0";
        SendCommandToPLayers("F" + playerTurn);
    }

    public void GameIsOvered(int a, int b, int c, String winner) {
        SendCommandToPLayers(a + "" + b + "" + c + "" + winner);
        System.out.println("GameIsOvered");
        for(ClientHandler clientHandler : connections) {
            clientHandler.player_is_ready = false;
        }
        ClearPool();
        winnerIsExist = false;
    }

    public void Draw() {
        SendCommandToPLayers("Draw!");
        ClearPool();
        for(ClientHandler clientHandler : connections) {
            clientHandler.player_is_ready = false;
        }
        winnerIsExist = false;
    }

    public void ShowPool() {
        for(int i = 1; i <= 9 ; i++) {
            System.out.print(blocks[i-1] + " ");
            if(i % 3 == 0) System.out.println();
        }
    }

    public void ClearPool() {
        for(int i = 0; i < 9 ; i++) {
            blocks[i] = "-";
        }
    }

    public void check() {
        if(blocks[0] != "-" && blocks[1] != "-" && blocks[2] != "-" && blocks[3] != "-" && blocks[4] != "-" && blocks[5] != "-" && blocks[6] != "-" && blocks[7] != "-" && blocks[8] != "-")
            winnerIsExist = true;
            //horizontal
        for(int i = 0; i < 9; i+=3) if(blocks[i].equals("X") && blocks[i+1].equals("X") && blocks[i+2].equals("X")) GameIsOvered(i,i+1,i+2,"X");
        else if(blocks[i].equals("0") && blocks[i+1].equals("0") && blocks[i+2].equals("0")) GameIsOvered(i,i+1,i+2,"0");
        //vertical
        for(int i = 0; i < 3; i++) if(blocks[i].equals("X") && blocks[i+3].equals("X") && blocks[i+6].equals("X")) GameIsOvered(i,i+3,i+6,"X");
        else if(blocks[i].equals("0") && blocks[i+3].equals("0") && blocks[i+6].equals("0")) GameIsOvered(i,i+3,i+6,"0");
        //diagonals
        if(blocks[0].equals("X") && blocks[4].equals("X") && blocks[8].equals("X")) GameIsOvered(0,4,8,"X");
        if(blocks[2].equals("X") && blocks[4].equals("X") && blocks[6].equals("X")) GameIsOvered(2,4,6,"X");
        if(blocks[0].equals("0") && blocks[4].equals("0") && blocks[8].equals("0")) GameIsOvered(0,4,8,"0");
        if(blocks[2].equals("0") && blocks[4].equals("0") && blocks[6].equals("0")) GameIsOvered(2,4,6,"0");
        if(winnerIsExist) Draw();
    }

}
