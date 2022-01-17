package com.company;

import java.io.IOException;

public class GameBufor {
    private static int Turn;
    private static int isGameRun;
    private static String playerMark; // "-", "x", "o"
    private static ClientTCP clientTCP;
    public GameBufor() {

    }
    public void connectServer(){
        playerMark = "-";
        clientTCP = new ClientTCP();
        clientTCP.setEndConnect(false);
        Thread connectServer =new Thread(clientTCP);
        connectServer.start();
    }
    public void playerMove(String move) throws IOException {
        clientTCP.playerMove(move);

    }
    public void endConnectServer() throws IOException {
        clientTCP.setEndConnect(true);

        System.out.println("GameBufor: end connection");
    }

    public static void setPlayerMark(String playerMark) {
        GameBufor.playerMark = playerMark;
        System.out.println("GRACZ: "+ playerMark);
    }

    public static String getPlayerMark() {
        return playerMark;
    }

    public static int getTurn() {
        return Turn;
    }

    public static void setTurn(int turn) {
        Turn = turn;
    }

    public static int getIsGameRun() {
        return isGameRun;
    }

    public static void setIsGameRun(int isGameRun) {
        GameBufor.isGameRun = isGameRun;
    }

}
