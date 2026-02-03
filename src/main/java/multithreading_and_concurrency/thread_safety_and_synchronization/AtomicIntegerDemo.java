package multithreading_and_concurrency.thread_safety_and_synchronization;

import java.util.concurrent.atomic.AtomicInteger;

class PurchaseAtomicCounter {

    // A thread-safe integer backed by hardware-level CAS
    private final AtomicInteger likes = new AtomicInteger(0);

    // Atomically add 1 to the like counter
    public void incrementLikes() {
        int prev, next;
        do {

            // Step 1  – read the current value.
            //           (May be outdated if another thread wins the race.)
            prev = likes.get();

            // Step 2  – compute the desired next value.
            next = prev + 1;

            // Step 3  – attempt to swap:
            /*          “If current value is still ‘prev’, set it to ‘next’.”
             *          Returns true on success, false if another thread
             *          already changed the value (retry needed).
             */
        } while (!likes.compareAndSet(prev, next));
    }

    // Read-only accessor
    public int getCount() {
        return likes.get();
    }
}

/**
 Why AtomicInteger?
 Holds a single int that can be updated without locks.
 Internally uses the CPU’s Compare-And-Swap (CAS) instruction, giving high throughput under moderate contention.

 What is compareAndSet(old, new) ?
 Compare the variable’s current value to old.
 If equal, swap in new atomically and return true.
 If not, do nothing and return false (someone else changed it first).


 How the Loop Works
 The table below assumes both threads (U1 and U2) are trying to increment the same atomic counter simultaneously. The operations are broken down step by step to show how compareAndSet() ensures safe updates using Compare-And-Swap (CAS) internally.
 Iteration Step     Thread U1 Value Flow        Thread U2 Value Flow                    Outcome
 prev = get()	    reads 10	                reads 10	                            both see same start
 next = prev + 1	computes 11	                computes 11	                            both want 11
 compareAndSet()	U1 succeeds (10 → 11)	    U2 fails (value now 11)	                U1 wins
 Retry loop	        exits	                    retries with new prev=11, next=12	    eventually U2 sets 12


 * **/



public class AtomicIntegerDemo {
}
