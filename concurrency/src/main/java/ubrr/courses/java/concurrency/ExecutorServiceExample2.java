package ubrr.courses.java.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.SneakyThrows;
import lombok.val;

public class ExecutorServiceExample2 {
  @SneakyThrows
  public static void main(String[] args) {
    val ex = Executors.newCachedThreadPool();
    Future<Integer> integerFuture = ex.submit(new CallableThread());
    Future<Integer> integerFuture1 = ex.submit(new CallableThread());
    System.out.println("а я уже здесь");
    System.out.println("integerFuture.isDone() = " + integerFuture.isDone());
    System.out.println("integerFuture.get() = " + integerFuture.get());
    System.out.println("integerFuture1.isDone() = " + integerFuture1.isDone());
    System.out.println("integerFuture1.get() = " + integerFuture1.get());
    ex.shutdown();
  }
}

class CallableThread implements Callable<Integer> {
  public int count;

  public Integer call() {
    while (count++ < 1_000_000) ;

    try {
      Thread.sleep(10_000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return count;
  }
}
