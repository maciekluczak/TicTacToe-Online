// Client Side
package com.company;
import java.io.*;
import java.net.*;

public class ClientTCP implements Runnable {
    private static int  serverPort;
    private static InetAddress host;
    private static PrintWriter toServer;
    private static BufferedReader fromServer;
    private static Socket socket;
    private static boolean endConnect;
    private static String serverMsg;

    public void run() { //Runnable Interface
        try {

            if (isIpReachable(GameBufor.getServerIp(), 2)) {    //Check if "is connection with server"
                System.out.println(isIpReachable(GameBufor.getServerIp(), 2));

                serverPort = 1234;
                //InetAddress
                host = InetAddress.getByName(GameBufor.getServerIp());          //get sever IP
                System.out.println("Connecting to server on port " + serverPort);

                //Socket

                socket = new Socket(host, serverPort);                          //new Socket
                System.out.println("Just connected to " + socket.getRemoteSocketAddress());

                //PrintWriter
                toServer = new PrintWriter(socket.getOutputStream(), true);
                //BufferedReader
                fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                toServer.println("Ready");


                do {                                                //main Client loop, connected with, GameBufor, GameField class

                    if (fromServer.ready()) {

                        String line = fromServer.readLine();
                        if (line.length() == 1 & !line.equals("/n")) {          // First Line to get player Mark from Server
                            GameBufor.setPlayerMark(line);
                            MainScreen.letsPlay();

                        } else if (line.length() > 1) {                         //Gameplay, server sending opponent moves

                            GameBufor.setEnemyMove(String.valueOf(line.charAt(line.length() - 1)));


                        }

                        System.out.println(line);
                    }

                } while (!endConnect);                  //end game when endConnect = true, set from GameBuffor

                toServer.close();

                fromServer.close();

                socket.close();

            }


        }

        catch(UnknownHostException ex) {
            ex.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void playerMove(String move) throws IOException {
        toServer.println(move);
    }
    public boolean isIpReachable(String ip, int timeout){
        boolean state = false;

        try {
            state = InetAddress.getByName(ip).isReachable(timeout);
        } catch (IOException e) {
            //Parse error here
        }

        return state;
    }

    public static String getServerMsg() {
        return serverMsg;
    }

    public static boolean isEndConnect() {
        return endConnect;
    }

    public static void setEndConnect(boolean endConnect) {
        ClientTCP.endConnect = endConnect;
    }
}