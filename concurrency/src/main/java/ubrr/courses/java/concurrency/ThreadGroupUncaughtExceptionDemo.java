package ubrr.courses.java.concurrency;

import lombok.val;

public class ThreadGroupUncaughtExceptionDemo {

  public static void main(String[] args) {
    val threadGroup = new NewThreadGroup("one");

    ThreadD t1 = new ThreadD("1", threadGroup);
    ThreadD t2 = new ThreadD("2", threadGroup);
    ThreadD t3 = new ThreadD("3", threadGroup);

    t3.setUncaughtExceptionHandler(
        (t, e) -> System.out.printf("%s throws exception: %s%n", t, e));

    t1.start();
    t2.start();
    t3.start();
  }
}

class NewThreadGroup extends ThreadGroup {
  NewThreadGroup(String name) {
    super(name);
  }

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    System.out.printf("%s has unhandled exception:%s%n", t, e);
  }
}

class ThreadD extends Thread {

  public ThreadD(String threadname, ThreadGroup threadGroupOb) {
    super(threadGroupOb, threadname);
  }

  public void run() {
    throw new RuntimeException("Oy, exception!!!");
  }
}
