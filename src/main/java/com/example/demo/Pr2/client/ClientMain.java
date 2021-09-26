package com.example.demo.Pr2.client;

import com.example.demo.Pr2.server.TheRootFinder;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain {

    public static final String UNIQUE_BINDING_NAME = "server.finder";

    public static void main(String[] args) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry(7777);
        TheRootFinder rootFinder = (TheRootFinder) registry.lookup(UNIQUE_BINDING_NAME);
        String result = rootFinder.find(15, 55, 5);
        System.out.println(result);

    }

}
