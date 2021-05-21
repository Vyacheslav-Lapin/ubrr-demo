package ubrr.courses.java.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo implements Runnable {
  public static int count;
  private static Lock lock = new ReentrantLock();

  public void run() {
    for (int i = 0; i < 100; i++) {

      if (lock.tryLock())
        System.out.println((Thread.currentThread().getName() + " - true"));
      else {
        System.out.println((Thread.currentThread().getName() + " - false"));
        continue;
      }

      count++;
      lock.unlock();
    }
    System.out.println(Thread.currentThread().getName() + " " + count);
  }

  public static void main(String[] args) {
    Thread th1 = new Thread(new LockDemo());
    Thread th2 = new Thread(new LockDemo());

    th1.start();
    th2.start();
  }
}
