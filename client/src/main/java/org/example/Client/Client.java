package org.example.Client;


import org.example.Message.Message;

import java.io.*;
import java.net.Socket;


public class Client  {
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;
    private  Socket socket;

    public Client(Socket socket) throws IOException {
        this.objectOutputStream =  new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream =  new ObjectInputStream(socket.getInputStream());
        this.socket = socket;
    }



    public boolean isConnected(){
       return socket.isConnected();
    }

    public void sendMessage(Message message) throws IOException {
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
    }

    public Message readMessage() throws IOException, ClassNotFoundException {
        return (Message) objectInputStream.readObject();
    }

    public Socket getSocket(){
        return  socket;
    }

    public void listenerMessage(){
        new Thread(() -> {
            try {
                while (socket.isConnected()){
                    Message message = (Message) objectInputStream.readObject();
                    System.out.println(message.getPayload());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
