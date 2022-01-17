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

    public void run() {
        try {
            serverPort= 1234;
            //InetAddress
            host = InetAddress.getByName("192.168.1.103");
            System.out.println("Connecting to server on port " + serverPort);

            //Socket
            socket = new Socket(host,serverPort);
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());

            //PrintWriter
            toServer = new PrintWriter(socket.getOutputStream(),true);
            //BufferedReader
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            toServer.println("Ready");



           do{

               if(fromServer.ready()){

                String line = fromServer.readLine();
                if(line.length() == 1){
                    GameBufor.setPlayerMark(line);
                    MainScreen.letsPlay();

                }else if(line.length()==2){
                    GameBufor.setEnemyMove(line);
                }

                System.out.println(line );}

            }while(!endConnect);
            System.out.println("zamykam połączenie");

            toServer.close();

            fromServer.close();

            socket.close();


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