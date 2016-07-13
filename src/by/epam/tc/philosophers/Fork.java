package by.epam.tc.philosophers;



import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Fork {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private int id;
    private Lock lock;
    public Fork(int id){
        this.id = id;
        lock = new ReentrantLock();

    }

    public  void get() throws InterruptedException {
        Thread.sleep(100);
        LOGGER.debug(Thread.currentThread().getName() + " взял вилку " + id);
    }

    public  void put() throws InterruptedException {
        Thread.sleep(100);
        LOGGER.debug(Thread.currentThread().getName() + " положил вилку " + id);
    }

    public Lock getLock() {
        return lock;
    }
}
