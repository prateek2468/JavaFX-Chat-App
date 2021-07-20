package jforsythe.server;

import jforsythe.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final ArrayList<ClientListener> clients  = new ArrayList<>();
    private static final ExecutorService pool  = Executors.newFixedThreadPool(40);

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(3390);
        System.out.println("Server started at "+ serverSocket.getLocalPort()+" waiting for clients ");

        while(true){
            Socket socket = serverSocket.accept();
            ClientListener temp1 = new ClientListener(socket);
            clients.add(temp1);
            pool.execute(temp1);

        }
    }

    public static void broadcast(Message a) throws IOException {
        for(ClientListener cl : clients){
           if(cl.getSocket().isClosed()){
               clients.remove(cl);
               break;
           }else {
               cl.sendMessagetoClient(a);
           }
        }
    }
}
