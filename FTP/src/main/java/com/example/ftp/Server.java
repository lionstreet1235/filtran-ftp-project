package com.example.ftp;

import java.io.File;
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

            // Simulate user login and directory access
            String username = "testUser"; // This should be dynamic based on the actual user
            File userDir = new File(System.getProperty("user.home") + File.separator + username);

            if (userDir.exists() && userDir.isDirectory()) {
                out.println("Welcome to the server, " + username + "!");
                out.println("Your directory contains:");
                for (File file : userDir.listFiles()) {
                    out.println(file.getName());
                }
            } else {
                out.println("Directory for user " + username + " not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
