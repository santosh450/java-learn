package Multithreading;

public class ThreadTester03 {

    public static void main(String[] args) {
        System.out.println("Main Started");

        Thread thread3 = new Thread03();                //Thread03 extends Thread
        thread3.start();

        Thread thread4 = new Thread(new Thread04());    //Thread03 implements Runnable
        thread4.start();

        System.out.println("Main Ended");
    }
}

class Thread04 implements Runnable{

    @Override
    public void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println("Inside Thread04: "+i);
        }
    }

}

/*
Main Started
Main Ended
Inside Thread04: 0
Inside Thread04: 1
Inside Thread04: 2
Inside Thread04: 3
Inside Thread04: 4
Inside Thread03: 0
Inside Thread03: 1
Inside Thread03: 2
Inside Thread03: 3
Inside Thread03: 4
 */