package multithreading_and_concurrency.locks_and_synchronization_mechanism;

import java.util.concurrent.locks.*;

// Shared stock price data guarded by a ReadWriteLock
class StockData {
    private double price = 100.0;  // initial price
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    // Writer: updates price
    public void updatePrice(double newPrice) {
        lock.writeLock().lock();  // acquire write lock
        try {
            System.out.printf("%s updating price to %.2f%n",
                    Thread.currentThread().getName(), newPrice);
            price = newPrice;
        } finally {
            lock.writeLock().unlock(); // release write lock
        }
    }

    // Reader: reads price
    public void readPrice() {
        lock.readLock().lock();   // acquire read lock
        try {
            System.out.printf("%s read price: %.2f%n",
                    Thread.currentThread().getName(), price);
        } finally {
            lock.readLock().unlock();  // release read lock
        }
    }
}
