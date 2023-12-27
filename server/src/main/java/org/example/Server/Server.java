package org.example.Server;

import org.example.Message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private final int port;
    private ConcurrentHashMap<String, ClientManager> clientMap;
    private ServerSocket serverSocket;

    public Server(int port) {
        this.port = port;
        this.clientMap = new ConcurrentHashMap<>();
    }



    public void serverStart() throws IOException, ClassNotFoundException {
        serverSocket = new ServerSocket(port);
        while (!serverSocket.isClosed()) {
            Socket clientSocket = serverSocket.accept();
            String uuid = UUID.randomUUID().toString();
            ClientManager clientManager = new ClientManager(clientMap,clientSocket, uuid);
            clientManager.listener();
            clientMap.put(uuid, clientManager);

        }
    }


}
