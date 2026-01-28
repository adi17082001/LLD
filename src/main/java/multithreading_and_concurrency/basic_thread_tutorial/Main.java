package multithreading_and_concurrency.basic_thread_tutorial;

// OrderService class
class OrderService {

    // Main method simulates placing an order and executing tasks sequentially
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Placing order...\n");

        // Send SMS and simulate the delay of 2 seconds
        sendSMS();
        System.out.println("Task 1 done.\n");

        // Send Email and simulate the delay of 3 seconds
        sendEmail();
        System.out.println("Task 2 done.\n");

        // Calculate ETA (Estimated Time of Arrival) with a delay of 5 seconds
        String eta = calculateETA();
        System.out.println("Order placed. Estimated Time of Arrival: " + eta);
        System.out.println("Task 3 done.\n");
    }

    // Method to simulate sending SMS with a 2-second delay
    private static void sendSMS() {
        try{
            Thread.sleep(2000); // Delay of 2 seconds
            System.out.println("SMS Sent!");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to simulate sending Email with a 3-second delay
    private static void sendEmail(){
        try{
            Thread.sleep(3000); // Delay of 3 seconds
            System.out.println("Email Sent!");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to simulate calculating the ETA with a 5-second delay
    private static String calculateETA() {
        try {
            Thread.sleep(5000); // Delay of 5 seconds
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "25 minutes"; // Returning the calculated ETA
    }
}

// Main class
class Main {

    public static void main(String[] args) {

        // measure start time
        long startTime = System.currentTimeMillis();

        // Initiating the order processing by calling the OrderService main method
        try {
            OrderService.main(args); // Call the OrderService's main method
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // measure end time
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }
}