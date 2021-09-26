package com.example.demo.Pr2.server;

import java.rmi.RemoteException;

public class Finder implements TheRootFinder{
    @Override
    public String find(double a, double b, double c) throws RemoteException {

        double d = Math.pow(b, 2) - 4 * a * c;
        System.out.println(d);
        System.out.println(Math.sqrt(d));
        if(d<0)
            return "Корней нет";
        Double x1 = (-b + Math.abs(d)) / (2 * a);
        Double x2 = (-b - Math.abs(d)) / (2 * a);
        if(x1.equals(x2))
            return "x = " + x1;
        return String.format("x1 = %s, x2 = %s", x1, x2);
    }
}
