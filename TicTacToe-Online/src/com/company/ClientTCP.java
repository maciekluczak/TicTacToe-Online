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


//
//            toServer.println(144666);
//
           outloop:
           do{
//               if(endConnect){
//                   break outloop;
//               }
               if(fromServer.ready()){
                String line = fromServer.readLine();
                System.out.println(line );}

               System.out.println(endConnect);

            }while(!endConnect);
            System.out.println("zamykam połączenie");
            toServer.close();

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
        System.out.println("Field info: " + move);
        toServer.println(move);
    }


    public static boolean isEndConnect() {
        return endConnect;
    }

    public static void setEndConnect(boolean endConnect) {
        ClientTCP.endConnect = endConnect;
    }
}