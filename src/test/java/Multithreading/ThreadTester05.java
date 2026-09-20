package Multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadTester05 {

    public static void main(String[] args) {

        BlockingQueue queue =
                new BlockingQueue(5);

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                queue.add(i);
                System.out.println(Thread.currentThread().getName() + " produced: " + i);
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                int value = queue.remove();
                System.out.println(Thread.currentThread().getName() + " consumed: " + value);
            }
        }, "Consumer");

        producer.start();
        consumer.start();
    }
}

class BlockingQueue {

    private final Queue<Integer> queue;
    private final int capacity;

    public BlockingQueue(int capacity) {

        queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public boolean add(int item) {
        synchronized (this) {
            while (queue.size() == capacity) {
                try {
                    System.out.println("Queue full. Producer waiting...");
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            }
            queue.add(item);
            System.out.println("Queue after add: " + queue);
            notifyAll();
            return true;
        }
    }

    public int remove() {
        synchronized (this) {
            while (queue.isEmpty()) {
                try {
                    System.out.println("Queue empty. Consumer waiting...");
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return -1;
                }
            }
            int element = queue.poll();
            System.out.println("Queue after remove: " + queue);
            notifyAll();
            return element;
        }
    }
}