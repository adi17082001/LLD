package multithreading_and_concurrency.thread_safety_and_synchronization;

/**
 * two threads are incrementing the same counter at the same time,
 racing to update the shared value without coordination.
 * When you run the above code, you won’t always get the expected output. Ideally, the result should be 2000, since both threads are supposed to increment the counter 1000 times each.
 * However, you'll often get a smaller number — because both threads are interfering with each other while updating the shared value.
 * Why the output is Inconsistent?
 * count++ looks like one step but is really three: read, add 1, write back.
 * If both threads read the same value (say 42) before either writes, each writes 43. One increment vanishes.
 * With many interleavings, the final count lands anywhere below 2000.
 *
 *
 *
 * **/



// Purchase counter with no protection
class PurchaseCounter {
    // Shared count value
    private int count = 0;

    // Increment the counter
    public void increment() {
        // READ current value
        // INCREMENT it
        // WRITE it back
        count++;                 // <-- not atomic, unsafe
    }

    // Fetch the current count
    public int getCount() {
        return count;
    }
}

// Demonstrates the race condition
class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        // Create a shared counter
        PurchaseCounter counter = new PurchaseCounter();

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
