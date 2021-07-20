package a7;

import jforsythe.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerListener extends Thread{

    private Socket socket ;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    Controller controller ;

    public ServerListener (Socket socket , Controller controller) throws IOException {
        this.socket = socket;
        this.controller = controller;
        inputStream = socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
    }
    @Override
    public void run(){
        try{

            while(true){
                Message temp = (Message) objectInputStream.readObject();
                switch (temp.getType()){
                    case CONNECT :
                        controller.add(String.format("%s %n" , temp.getName()) );
                }
                controller.addMessage(String.format("%s: %s%n" , temp.getName() , temp.getMessage()));
            }
        }catch (ClassNotFoundException | IOException e){
            System.err.println("Disconnected");
        }
        finally{
            try {
                objectInputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
