package org.example;

import org.example.Client.Client;
import org.example.Client.Connector;
import org.example.Message.Message;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner;
        Connector connector = new Connector("localhost", 13600);
        System.out.print("Введите свое имя: ");
        scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        try {
            Client client = new Client(connector.connect());
            client.listenerMessage();
            client.sendMessage(new Message("Client", "Server", name));
          while (client.isConnected()){
                System.out.println(name + ": ");
                scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                client.sendMessage(new Message("Client", "Server", name + ": " +  message));
            }
        } catch (IOException e){
            e.printStackTrace();

        }





    }
}