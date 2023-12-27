package org.example.Client;

import java.io.IOException;
import java.net.Socket;

public class Connector {
    private final String serverIP;
    private final int port;

    public Connector(String serverIP, int port) {
        this.serverIP = serverIP;
        this.port = port;
    }

    public Socket connect() throws IOException {
        return new Socket(serverIP, port);
    }
}
