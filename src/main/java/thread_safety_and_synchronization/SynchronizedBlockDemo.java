package thread_safety_and_synchronization;

class SafeCounterBlockDemo {
    private int count = 0;

    public void increment() {
        // Lock only the code that truly needs protection
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        // No lock needed for simple read, or use block if strict consistency required
        return count;
    }
}


public class SynchronizedBlockDemo {
    public static void main(String[] args) throws InterruptedException {
        // Create a shared counter
        // PurchaseCounter counter = new PurchaseCounter();

        // Use SafeCounter instead of the previous counter
        SafeCounterBlockDemo counter = new SafeCounterBlockDemo();
        // Task that bumps the counter 1000 times
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        // Run the same task in two threads
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Expect 2000, but rarely get it
        System.out.println("Final Count: " + counter.getCount());
    }
}

