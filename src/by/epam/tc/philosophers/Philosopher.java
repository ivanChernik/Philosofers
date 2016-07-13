package by.epam.tc.philosophers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Random;


public class Philosopher implements Runnable {
    private volatile Fork fork1;
    private volatile Fork fork2;
    private Waiter waiter;
    private final Random RANDOM = new Random();
    private volatile boolean stopThread = false;
    private final static Logger LOGGER = LogManager.getRootLogger();

    public Philosopher(Waiter waiter, Fork fork1, Fork fork2) {
        this.waiter = waiter;
        this.fork1 = fork1;
        this.fork2 = fork2;
    }

    @Override
    public void run() {
        while (!stopThread) {
            try {
                eat();
                think();
            } catch (InterruptedException e) {
                LOGGER.error("Философ уничтожен", e);
            }
        }
    }

    private void eat() throws InterruptedException {
        try {
            LOGGER.debug("Философ " + Thread.currentThread().getName() + " хочет взять вилки");
            getForks();
            LOGGER.debug("Философ " + Thread.currentThread().getName() + " ест");
            Thread.sleep(RANDOM.nextInt(1000) + 1000);
        } finally {
            releaseForks();
        }
    }

    private void releaseForks() throws InterruptedException {
        fork1.put();
        fork2.put();
        waiter.releaseForks(fork1, fork2);
    }

    private void getForks() throws InterruptedException {
        while (!waiter.acquireForks(fork1, fork2)) {
            think();
        }
        fork1.get();
        fork2.get();
    }

    private void think() throws InterruptedException {
        Thread.sleep(RANDOM.nextInt(1000) + 1000);
    }
    public void stopThread() {
        stopThread = true;
    }
}
