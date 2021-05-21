package ubrr.courses.java.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;

public class ReentrantExample {

  int x;

  @NotNull
  final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

  public ReentrantExample(int x) {
    this.x = x;
  }

  public static void main(String[] args) {
    val reentrantExample1 = new ReentrantExample(5);
    val reentrantExample2 = new ReentrantExample(6);

    val atomicInteger= new AtomicInteger(155);
    AtomicReference<Object> reference = new AtomicReference<>();
//    reference.

//    reentrantExample1.set(7);
//    reentrantExample1.set(8);
//    reentrantExample1.set(7);
//    reentrantExample1.set(8);
//    reentrantExample1.set(7);
//    reentrantExample1.set(8);
//    reentrantExample1.set(7);
//    reentrantExample1.set(8);
//    reentrantExample1.set(7);
//    reentrantExample1.set(8);
//    reentrantExample1.set(7);
//    reentrantExample1.set(8);
  }

  @SneakyThrows
  public final void get() {
    val lock = reentrantReadWriteLock.readLock();
    lock.lock();
    System.out.println("x = " + x);
    lock.unlock();
  }

  @SneakyThrows
  public final void set(int value) {
    val lock = reentrantReadWriteLock.writeLock();
    lock.lock();
    x = value;
    lock.unlock();
  }


}
