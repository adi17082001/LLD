package multithreading_and_concurrency.locks_and_synchronization_mechanism;

import java.util.concurrent.Semaphore;

// Enforces a max-devices policy for a TUF+ account
class TUFPlusAccount {

    // fixed number of login permits (tokens)
    private final Semaphore deviceSlots;

    // constructor sets permit count
    public TUFPlusAccount(int maxDevices) {
        this.deviceSlots = new Semaphore(maxDevices);
    }

    // user attempts to log in
    public boolean login(String user) throws InterruptedException {
        System.out.println(user + " trying to log in...");

        // try to grab a permit immediately (non-blocking)
        if (deviceSlots.tryAcquire()) {
            System.out.println(user + " successfully logged in.");
            return true;
        } else {
            System.out.println(user + " denied login - too many devices.");
            return false;
        }
    }

    // user logs out and returns the permit
    public void logout(String user) {
        System.out.println(user + " logging out.");
        deviceSlots.release();  // release permit for next device
    }
}

// Quick demo – 2 device limit
class SemaphoreExample {
    public static void main(String[] args) throws InterruptedException {
        TUFPlusAccount account = new TUFPlusAccount(2);

        Thread u1 = new Thread(() -> trySession(account, "User-A"));
        Thread u2 = new Thread(() -> trySession(account, "User-B"));
        Thread u3 = new Thread(() -> trySession(account, "User-C")); // should fail first

        u1.start(); u2.start();
        Thread.sleep(100);  // ensure first two log in
        u3.start();

        u1.join(); u2.join(); u3.join();
    }

    // helper for a user session
    private static void trySession(TUFPlusAccount acc, String name) {
        try {
            if (acc.login(name)) {
                Thread.sleep(500);  // simulate watching a video
                acc.logout(name);
            }
        } catch (InterruptedException ignored) { }
    }
}

