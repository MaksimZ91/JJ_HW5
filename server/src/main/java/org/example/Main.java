package org.example;


import org.example.Message.Message;
import org.example.Server.Server;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Server server = new Server(13600);
        server.serverStart();



    }
}