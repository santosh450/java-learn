package Multithreading;

public class ThreadTester02 {

    public static void main(String[] args) {
        System.out.println("Main Started");
        Thread thread3 = new Thread03();
        thread3.setDaemon(true);
        thread3.start();
        System.out.println("Main Ended");
    }
}

class Thread03 extends Thread{

    @Override
    public void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println("Inside Thread03: "+i);
        }
    }

}

/*
Main Started
Main Ended
 */