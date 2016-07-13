package by.epam.tc.philosophers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;


public class Waiter extends Semaphore {

    private static final String RELEASING_FORKS_FAILED = "Releasing forks failed";
	private static final String ACQUIRING_FORKS_FAILED = "Acquiring forks failed";
	private final static Logger LOGGER = LogManager.getRootLogger();

//    public Waiter(int permits) {
//        super(permits);
//    }

    public Waiter(int permits, boolean fair) {
        super(permits, fair);
    }

    public boolean acquireForks(Fork fork1, Fork fork2) {
        Lock fork1Lock;
        Lock fork2Lock;
        try {
            acquire();
            fork1Lock = fork1.getLock();
            fork2Lock = fork2.getLock();
            if (fork1Lock.tryLock()) {
                if (fork2Lock.tryLock()) {
                    return true;
                } else {
                    fork1Lock.unlock();
                    return false;
                }
            }
        } catch (InterruptedException e) {
            LOGGER.error(ACQUIRING_FORKS_FAILED,e);
        } finally {
            release();
        }
        return false;
    }


    public void releaseForks(Fork fork1, Fork fork2) {
        try {
            acquire();
            fork1.getLock().unlock();
            fork2.getLock().unlock();
        } catch (InterruptedException e) {
            LOGGER.error(RELEASING_FORKS_FAILED,e);
        } finally {
            release();
        }
    }
}
