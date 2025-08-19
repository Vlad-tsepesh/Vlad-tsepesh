package exercises.multitheading.threadmanagement.example;

public class JoiningExample {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Worker();
        worker.start(); // start the worker
       
        Thread.sleep(100L);
        System.out.println("Do something useful");
        
        worker.join(3000L);  // waiting for the worker
        System.out.println("The program stopped");
    }
}