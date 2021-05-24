package ubrr.courses.java.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample implements Runnable {

  public static AtomicInteger count = new AtomicInteger();

  public void run() {
    for (int i = 0; i < 10_000_000; i++) {
      // count.incrementAndGet();
      // count.addAndGet(1);
      count.getAndAdd(1);
    }
    System.out.println(count);
  }

  public static void main(String[] args) {
    new Thread(new AtomicExample()).start();
    new Thread(new AtomicExample()).start();
    new Thread(new AtomicExample()).start();
    new Thread(new AtomicExample()).start();
    new Thread(new AtomicExample()).start();
  }
}
