package com.example.ftp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8881);
            System.out.println("Waiting for connection...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection from " + clientSocket.getRemoteSocketAddress());

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Welcome to the server!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
