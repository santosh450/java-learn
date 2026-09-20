package Multithreading;

public class ThreadStack {

    public static void main(String[] args) {

        System.out.println("Main Thread Start");
        Stack stack = new Stack(5);

        new Thread(() -> {
            int counter = 0;
            while(++counter < 10){
                System.out.println("Pushed: "+stack.push(100));
            }
        }, "Pusher").start();

        new Thread(() -> {
            int counter = 0;
            while(++counter < 10){
                System.out.println("Popped: "+stack.pop());
            }
        }, "Popper").start();

        System.out.println("Main Thread End");

    }
}

class Stack {
    private int[] array;
    private int stackTop;
    private Object lock;

    public Stack(int capacity){
        array = new int[capacity];
        stackTop = -1;
        lock = new Object();
    }

    public synchronized boolean push(int element){
            if (isFull()) {
                return false;
            }
            ++stackTop;
            try {
                Thread.sleep(400);
            } catch (Exception e) {
            }
            array[stackTop] = element;
            return true;
    }

    public synchronized int pop(){
            if (isEmpty()) {
                return Integer.MIN_VALUE;
            }
            int element = array[stackTop];
            array[stackTop] = Integer.MIN_VALUE;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            stackTop--;
            return element;
    }

    private boolean isEmpty(){
        return stackTop < 0;
    }

    private boolean isFull(){
        return stackTop >= array.length - 1;
    }
}

/*
Main Thread Start
Main Thread End
Popped: 0
Pushed: true
Popped: -2147483648
Popped: 100
Popped: -2147483648
Popped: -2147483648
Popped: -2147483648
Popped: -2147483648
Popped: -2147483648
Popped: -2147483648
Exception in thread "Pusher" java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 5
	at Multithreading.Stack.push(ThreadStack.java:44)
	at Multithreading.ThreadStack.lambda$main$0(ThreadStack.java:13)
	at java.base/java.lang.Thread.run(Thread.java:834)

Process finished with exit code 0
 */
