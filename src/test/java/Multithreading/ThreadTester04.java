package Multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadTester04 {

    public static void main(String[] args) {

        BlockingQueueWithoutWait queue =
                new BlockingQueueWithoutWait(5);

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                queue.add(i);
                System.out.println("Produced: " + i);
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                int value = queue.remove();
                System.out.println("Consumed: " + value);
            }
        }, "Consumer");

        producer.start();
        consumer.start();
    }
}

class BlockingQueueWithoutWait {

    private final Queue<Integer> queue;
    private final int capacity;

    public BlockingQueueWithoutWait(int capacity) {
        queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public boolean add(int item) {
        while (true) {
            synchronized (this) {
                if (queue.size() < capacity) {
                    queue.add(item);
                    return true;
                }
            }
        }
    }

    public int remove() {
        while (true) {
            synchronized (this) {
                if (!queue.isEmpty()) {
                    return queue.poll();
                }
            }
        }
    }
}