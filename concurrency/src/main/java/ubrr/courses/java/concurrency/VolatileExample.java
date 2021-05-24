package ubrr.courses.java.concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.val;

public class VolatileExample implements Runnable {
  public static volatile int count;
  public static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

  public void run() {
    val writeLock = reentrantReadWriteLock.writeLock();
    for (int i = 0; i < 10_000_000; i++) {
      writeLock.lock();
      count++;
      writeLock.unlock();
    }

    System.out.println(count);
  }

  public static void main(String[] args) {
    new Thread(new VolatileExample()).start();
    new Thread(new VolatileExample()).start();
    new Thread(new VolatileExample()).start();
    new Thread(new VolatileExample()).start();
    new Thread(new VolatileExample()).start();
  }
}
