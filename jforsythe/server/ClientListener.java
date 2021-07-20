package jforsythe.server;

import jforsythe.Message;
import jforsythe.MessageType;

import java.io.*;
import java.net.Socket;

public class ClientListener implements Runnable {

    private static Socket socket;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private String name ;


    public ClientListener(Socket socket){
        this.socket = socket;

        try{
            this.outputStream = this.socket.getOutputStream();
            this.outputStream.flush();
            this.objectOutputStream = new ObjectOutputStream(this.outputStream);
            this.objectOutputStream.flush();

            this.inputStream = this.socket.getInputStream();
            this.objectInputStream = new ObjectInputStream(this.inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run(){
        try{
            while(true){
                Message temp = (Message)this.objectInputStream.readObject();
                switch (temp.getType()){
                    case CONNECT:
                        Server.broadcast(new Message(MessageType.CONNECT , temp.getName() , temp.getMessage()
                                + " has joined the server"));
                        this.setName(temp.getName());
                        break;
                    case MESSAGE:
                        Server.broadcast(temp);
                        break;
                    case EXIT:

                        break;
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Client Disconnected");
        } finally {
            try{
                objectOutputStream.close();
                objectInputStream.close();
                outputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void sendMessagetoClient(Message m) throws IOException{
            this.objectOutputStream.writeObject(m);

    }

    public static Socket getSocket() {
        return socket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
