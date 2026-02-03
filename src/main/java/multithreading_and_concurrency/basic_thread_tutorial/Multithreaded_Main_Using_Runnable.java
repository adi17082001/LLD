package multithreading_and_concurrency.basic_thread_tutorial;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Implementing the Runnable interface for sending SMS
class SMSTask implements Runnable {
    public void run() {
        try {
            Thread.sleep(2000); // 2-second delay for SMS
            System.out.println("SMS Sent using Runnable.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Implementing the Runnable interface for sending Email
class EmailTask implements Runnable {
    public void run() {
        try {
            Thread.sleep(3000); // 3-second delay for Email
            System.out.println("Email Sent using Runnable.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Implementing the Runnable interface for calculating ETA
class ETATask implements Runnable {
    public void run() {
        try {
            Thread.sleep(5000); // 5-second delay for ETA calculation
            System.out.println("ETA Calculated using Runnable. Estimated Time: 25 minutes.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Multithreaded_Main_Using_Runnable {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // Create Runnable objects for SMS, Email, and ETA calculation
        SMSTask smsTask = new SMSTask();
        EmailTask emailTask = new EmailTask();
        ETATask etaTask = new ETATask();

        // Create Thread objects and pass the Runnable tasks to them
        Thread smsThread = new Thread(smsTask);
        Thread emailThread = new Thread(emailTask);
        Thread etaThread = new Thread(etaTask);

        System.out.println("Task Started.\n");

        // Start all threads
        smsThread.start();
        System.out.println("Task 1 ongoing...");

        emailThread.start();
        System.out.println("Task 2 ongoing...");

        etaThread.start();
        System.out.println("Task 3 ongoing...");

        // Wait for all threads to finish
        try {
            smsThread.join();
            emailThread.join();
            etaThread.join();
            System.out.println("All tasks completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
