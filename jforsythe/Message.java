package jforsythe;

import java.io.Serializable;

<<<<<<< HEAD
public class Message implements Serializable {
    private MessageType type;
    private String name ;
    private String message;

=======
/**
 * Message object for sending all types of messages
 * @author Jason Forsythe
 * @version 1.0
 */
public class Message implements Serializable {
    private MessageType type;
    private String name;
    private String message;

    /**
     * Constructor of Message object
     * @param type MessageType lets everyone know the type of message being sent
     * @param name String representing the name of the client that sent the message
     * @param message String the message body or content
     */
>>>>>>> 7508ee20c404f6260f41181b50bc3582803ff8b5
    public Message(MessageType type, String name, String message) {
        this.type = type;
        this.name = name;
        this.message = message;
    }

<<<<<<< HEAD
=======
    /**
     * Gets the type of the message
     * @return MessageType representing the type of the message
     */
>>>>>>> 7508ee20c404f6260f41181b50bc3582803ff8b5
    public MessageType getType() {
        return type;
    }

<<<<<<< HEAD
=======
    /**
     * Sets the type of the message (not used)
     * @param type MessageType representing the type of the message
     */
>>>>>>> 7508ee20c404f6260f41181b50bc3582803ff8b5
    public void setType(MessageType type) {
        this.type = type;
    }

<<<<<<< HEAD
=======
    /**
     * Gets the name of the client sending the message
     * @return String representing the name of the client sending the message
     */
>>>>>>> 7508ee20c404f6260f41181b50bc3582803ff8b5
    public String getName() {
        return name;
    }

<<<<<<< HEAD
=======
    /**
     * Sets the name of the client sending the message
     * @param name String representing the name of the client sending the message
     */
>>>>>>> 7508ee20c404f6260f41181b50bc3582803ff8b5
    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
=======
    /**
     * Gets the message body
     * @return String representing the message the client sent
     */
>>>>>>> 7508ee20c404f6260f41181b50bc3582803ff8b5
    public String getMessage() {
        return message;
    }

<<<<<<< HEAD
=======
    /**
     * Sets the message body (not used)
     * @param message String representing the message body that the client sent
     */
>>>>>>> 7508ee20c404f6260f41181b50bc3582803ff8b5
    public void setMessage(String message) {
        this.message = message;
    }

<<<<<<< HEAD
    @Override
    public String toString(){
        return String.format("{Type: %-15s|Name :%-20s|Message:%-100s" , this.getType() , this.getName() , this.getMessage());
    }
=======
    /**
     * Override of the toString method to print messages nicely on the server
     * @return String representing the message object
     */
    @Override
    public String toString(){
        return String.format("{Type: %-15s|Name: %-20s|Message: %-100s",
                this.getType(), this.getName(), this.getMessage());
    }

>>>>>>> 7508ee20c404f6260f41181b50bc3582803ff8b5
}
