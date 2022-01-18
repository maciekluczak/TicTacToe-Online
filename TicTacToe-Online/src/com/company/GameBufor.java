package com.company;

import java.io.IOException;

public class GameBufor {                         //Main Game Support Class
    private static int Turn;        //Info- Turn
    private static int isGameRun;  // Info - is game still run?
    private static String playerMark; // "-", "x", "o"
    private static ClientTCP clientTCP; //client management
    private static String enemyMove;    // Enemy move from server
    private static GameField[] fieldsArray; // Array of fields class from board 3x3
    private static String serverIp;         //string of typed ip

    public GameBufor() {

    }
    public void connectServer(){
        enemyMove = "-";
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

    public static GameField[] getFieldsArray() {
        return fieldsArray;
    }

    public static void setFieldsArray(GameField[] fieldsArray) {
        GameBufor.fieldsArray = fieldsArray;
    }

    public static String getEnemyMove() {
        return enemyMove;
    }

    public static String getServerIp() {
        return serverIp;
    }

    public static void setServerIp(String serverIp) {
        GameBufor.serverIp = serverIp;
    }

    public static void setEnemyMove(String enemyMove) {
        GameBufor.enemyMove = enemyMove;
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
