package com.example.demo.Pr2.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TheRootFinder extends Remote {

    String find(double a, double b, double c) throws RemoteException;

}
