package ubrr.courses.java.concurrency;

public class ThreadDefaultUncaughtExceptionDemo {
  public static void main(String[] args) {

    Thread.setDefaultUncaughtExceptionHandler(
        (t, e) -> System.out.printf("%s (default handler)throws exception: %s%n", t, e));

    Thread t1 = new Thread(new MyThread2());
    Thread t2 = new Thread(new MyThread2());

    t2.setUncaughtExceptionHandler(
        (t, e) -> System.out.printf("%s throws exception: %s%n", t, e));

    t1.start();
    t2.start();
  }
}

class MyThread2 implements Runnable {
  public void run() {
    throw new RuntimeException();
  }
}
