package Multithreading;

public class ThreadTester {

    public static void main(String[] args) {
        System.out.println("Main Started");
        Thread thread1 = new Thread01();
        Thread thread2 = new Thread02();
        thread2.setDaemon(true);
//        thread1.start();
        thread2.start();
        System.out.println("Main Ended");
    }
}

class Thread01 extends Thread{

    @Override
    public void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println("Inside Thread01: "+i);
        }
    }

}

class Thread02 extends Thread{

    @Override
    public void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println("Inside Thread02: "+i);
        }
    }

}

/*
Main Started
Main Ended
Inside Thread01: 0
Inside Thread01: 1
Inside Thread01: 2
Inside Thread01: 3
Inside Thread01: 4
 */