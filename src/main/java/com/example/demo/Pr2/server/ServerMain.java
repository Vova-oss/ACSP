package com.example.demo.Pr2.server;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {

    public static final String UNIQUE_BINDING_NAME = "server.finder";

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {

        Finder finder = new Finder();
        Registry registry = LocateRegistry.createRegistry(7777);
        Remote stub = UnicastRemoteObject.exportObject(finder, 0);
        registry.bind(UNIQUE_BINDING_NAME, stub);

        Thread.sleep(Integer.MAX_VALUE);

    }

}
