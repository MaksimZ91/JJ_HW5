package org.example.Server;

import org.example.Message.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientManager {
    private Socket clientSocket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private ConcurrentHashMap <String,  ClientManager> clientSocketMap;
    private  String userName;
    private  String uuid;

    public ClientManager(ConcurrentHashMap clientSocketMap, Socket clientSocket, String uuid) throws IOException, ClassNotFoundException {
        this.clientSocketMap = clientSocketMap;
        this.clientSocket = clientSocket;
        this.objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        this.objectOutputStream= new ObjectOutputStream(clientSocket.getOutputStream());
        this.uuid = uuid;
        setName();
        sendBroadcastMessage(new Message("Server", "Client", "Server: " + userName + " подключился"));
    }

    public void listener(){
        new Thread(() -> {
            while (clientSocket.isConnected()){
                try {
                    Message message = (Message) objectInputStream.readObject();
                    sendBroadcastMessage(message);
                } catch (IOException  |  ClassNotFoundException e) {
                   e.printStackTrace();
                }
            }
        }).start();
    }

    private void setName() throws IOException, ClassNotFoundException {
        Message message = (Message) objectInputStream.readObject();
        this.userName = message.getPayload();
    }
    public void sendBroadcastMessage(Message message) throws IOException {
        for (Map.Entry<String, ClientManager> entry : clientSocketMap.entrySet()){
            if(entry.getKey() != uuid){
                entry.getValue().objectOutputStream.writeObject(message);
                entry.getValue().objectOutputStream.flush();
            }
        }

    }

}
