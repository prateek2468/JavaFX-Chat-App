package jforsythe.client;

import jforsythe.Message;
import jforsythe.MessageType;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static OutputStream outputStream;
    private static ObjectOutputStream objectOutputStream;



    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //TODO remove before production

        try {
            Thread.sleep(Integer.parseInt(args[0]));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Socket socket  = new Socket("localhost" , 3390);

        outputStream = socket.getOutputStream();
        outputStream.flush();
        objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.flush();



        //write to server
        Message temp = new Message(MessageType.CONNECT , "JforSythe" , "hi i am connected");
        objectOutputStream.writeObject(temp);
        objectOutputStream.flush();

       ServerListener serverListener = new ServerListener(socket);
       serverListener.start();

       Scanner s  =new Scanner(System.in);
       try {
           while (true) {
                String str = s.nextLine();
                Message temp1 = new Message(MessageType.MESSAGE , args[1] , str);
                objectOutputStream.writeObject(temp1);
                objectOutputStream.flush();
           }
       } catch(IOException e){
           e.printStackTrace();
       }

       // socket.close();
    }
}
