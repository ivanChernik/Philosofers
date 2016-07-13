package by.epam.tc.philosophers;


public class Main {
        public static void main(String[] args) throws InterruptedException {
            int i = 1;
            Waiter waiter = new Waiter(1, true);
            Fork fork1 = new Fork(i++);
            Fork fork2 = new Fork(i++);
            Fork fork3 = new Fork(i++);
            Fork fork4 = new Fork(i++);
            Fork fork5 = new Fork(i);


            Philosopher philosopher1 = new Philosopher(waiter, fork5, fork1);
            Philosopher philosopher2 = new Philosopher(waiter, fork1, fork2);
            Philosopher philosopher3 = new Philosopher(waiter, fork3, fork2);
            Philosopher philosopher4 = new Philosopher(waiter, fork3, fork4);
            Philosopher philosopher5 = new Philosopher(waiter, fork5, fork4);

            new Thread(philosopher1).start();
            new Thread(philosopher2).start();
            new Thread(philosopher3).start();
            new Thread(philosopher4).start();
            new Thread(philosopher5).start();

            Thread.sleep(10000);

            philosopher1.stopThread();
            philosopher2.stopThread();
            philosopher3.stopThread();
            philosopher4.stopThread();
            philosopher5.stopThread();
        }
}
