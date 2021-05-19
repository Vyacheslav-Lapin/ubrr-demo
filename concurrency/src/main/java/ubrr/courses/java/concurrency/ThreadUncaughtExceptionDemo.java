package ubrr.courses.java.concurrency;

public class ThreadUncaughtExceptionDemo {

  public static void main(String[] args) {
    Thread t = new Thread(new SimpleThread());
    t.setUncaughtExceptionHandler(
        (t1, e) -> System.out.printf("%s throws exception: %s%n", t1, e));
    t.start();
  }
}

class SimpleThread implements Runnable {
  public void run() {
    throw new RuntimeException("It is a great exception.");
  }
}
