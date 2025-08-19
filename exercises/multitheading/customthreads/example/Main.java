package exercises.multitheading.customthreads.example;

public class Main {
    public static void main(String[] args) {

        Thread t1 = new HelloThread(); // a subclass of Thread
        t1.start();

        Thread t2 = new Thread(new HelloRunnable()); // passing runnable
        t2.start();

        Thread myThread = new Thread(new HelloRunnable(), "my-thread");
        myThread.start();

        Thread t3 = new Thread(() -> {
            System.out.println(String.format("Hello, I'm %s", Thread.currentThread().getName()));
        });
        t3.start();

        System.out.println("Finished");
    }
}
