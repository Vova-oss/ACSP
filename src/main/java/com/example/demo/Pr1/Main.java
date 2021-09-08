package com.example.demo.Pr1;

import javax.swing.table.TableRowSorter;

public class Main {

    public static void main(String[] args) {
        Object object = new Object();
        new Thread(() -> {
            synchronized (object){
                for(int i = 0;i <10 ; i++){
                    System.out.println("Ping");
                    object.notify();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (object) {
                for(int i = 0; i<10; i++) {
                    System.out.println("Pong");
                    object.notify();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
