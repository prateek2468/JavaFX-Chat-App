package jforsythe.client;

import jforsythe.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerListener extends Thread{
    private final Socket socket;
    private final InputStream inputStream;
    private final ObjectInputStream objectInputStream;

    public ServerListener(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.objectInputStream = new ObjectInputStream(inputStream);
    }

    @Override
    public void run(){
        try{
            while(true){
                Message temp = (Message) objectInputStream.readObject();
                System.out.printf("%s: %s%n" , temp.getName() , temp.getMessage());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Disconnected from server");
        }finally{
            try{
                objectInputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
