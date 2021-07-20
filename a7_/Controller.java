package a7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import jforsythe.Message;
import jforsythe.MessageType;

import java.io.*;
import java.net.Socket;

public class Controller {
    @FXML
    TextField textInput;

    @FXML
    TextArea textOutput;

    @FXML
    TextArea textMembers;

    private String name;

    private Socket socket;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;


    public void initialize() throws IOException {
        TextInputDialog nameInput = new TextInputDialog("what is your name ??");
        nameInput.setHeaderText("Welcome to ChatRoom");
        nameInput.showAndWait();
        name  = nameInput.getResult();

        socket = new Socket("odin.cs.csub.edu" , 3390);
        outputStream = socket.getOutputStream();
        outputStream.flush();
        objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.flush();

        ServerListener serverListener = new ServerListener(this.socket , this);
        serverListener.start();
        
        Message temp = new Message(MessageType.CONNECT , name , "  " );
        objectOutputStream.writeObject(temp);
        objectOutputStream.flush();
    }
    public void onInputAction(ActionEvent actionEvent) throws IOException {
        Message temp = new Message(MessageType.MESSAGE , name , textInput.getText());
        textInput.clear();
        objectOutputStream.writeObject(temp);
        objectOutputStream.flush();
    }

    public void exit() throws IOException {
        objectOutputStream.close();
        outputStream.close();
        socket.close();
    }

    public void addMessage(String s) {
        textOutput.appendText(s);
    }

    public void add(String s){
        textMembers.appendText(s);
    }
}
