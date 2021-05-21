package ubrr.courses.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.val;

public class SimpleThreadPoolExecutorExample {
  public static void main(String... __) {
    final Runnable command = () -> {
      long count = 0;
      for (long i = 0; i < 20_000_000_000L; i++) count++;
      System.out.println(count);
    };

    val ex = Executors.newCachedThreadPool();
    ex.execute(command);
    ex.execute(command);
    ex.shutdown();
  }
}
