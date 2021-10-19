package com.example.demo.Pr3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Server {
    public static final int PORT = 8080;
    public static LinkedList<ServerFunction> serverList = new LinkedList<>();
    public static List<String> messages = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server have started");
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerFunction(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {

            server.close();
        }
    }
}

class ServerFunction extends Thread {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerFunction(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String word;
        try {
            try {
                while (true) {
                    Server.messages.clear();
                    word = in.readLine();
                    if (!word.equals("")) {
                        Server.messages.add(word);
                        System.out.println("New message: " + word);
                        Thread.sleep(5000);
                        for (ServerFunction vr : Server.serverList) {
                            vr.send(Server.messages);
                        }
                    }
                }
            } catch (NullPointerException ignored) {
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            this.down();
        }
    }

    private void send(List<String> msg) {
        try {
            if (!msg.isEmpty()) {
                out.write(msg + "\n");
                out.flush();
            }
        } catch (IOException ignored) {
        }
    }

    private void down() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (ServerFunction vr : Server.serverList) {
                    if (vr.equals(this)) vr.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {
        }
    }
}