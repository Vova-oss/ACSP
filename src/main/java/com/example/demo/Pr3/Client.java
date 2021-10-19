package com.example.demo.Pr3;

import java.io.*;
import java.net.*;

class ClientFunction {
    // сокет - это одна конечная точка двустороннего канала связи между двумя программами,
    // работающими на разных компьютерах в сети.
    // Обычно сервер работает на определенном компьютере в сети и имеет сокет,
    // привязанный к определенному номеру порта.


    private Socket socket;
    private BufferedReader in; // из сокета
    private BufferedWriter out; // в сокет
    private BufferedReader inputUser; // консоль

    public ClientFunction(String addr, int port) {
        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            new readWord().start();
            new writeWord().start();
        } catch (IOException e) {
            ClientFunction.this.down();
        }
    }

    private void down() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {
        }
    }

    private class readWord extends Thread {
        @Override
        public void run() {
            String str;
            try {
                while (true) {
                    str = in.readLine();
                    if (str.equals("stop")) {
                        ClientFunction.this.down();
                        break;
                    }
                    System.out.println(str);
                }
            } catch (IOException e) {
                ClientFunction.this.down();
            }
        }
    }

    public class writeWord extends Thread {
        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    userWord = inputUser.readLine();
                    if (userWord.equals("stop")) {
                        out.write("stop" + "\n");
                        ClientFunction.this.down();
                        break;
                    } else {
                        out.write(userWord + "\n");
                    }
                    out.flush();
                } catch (IOException e) {
                    ClientFunction.this.down();
                }
            }
        }
    }
}

public class Client {
    public static String ipAddr = "localhost";
    public static int port = 8080;

    public static void main(String[] args) {
        new ClientFunction(ipAddr, port);
    }
}

class Client2 {
    public static String ipAddr = "localhost";
    public static int port = 8080;

    public static void main(String[] args) {
        new ClientFunction(ipAddr, port);
    }
}