package ubrr.courses.java.concurrency;

import lombok.SneakyThrows;

public class StaticSynch {
  @SneakyThrows
  public static synchronized void a() {
    System.out.println("Line #1 in the method a");
    Thread.sleep(1_000);
    System.out.println("Line #2 in the method a");
  }

  @SneakyThrows
  public static synchronized void b() {
    System.out.println("Line #1 in the method b");
    Thread.sleep(1_000);
    System.out.println("Line #2 in the method b");
  }

  public static void main(String[] args) {
//    a();
//    b();
    new Thread(StaticSynch::a).start();
    new Thread(StaticSynch::b).start();
  }
}
