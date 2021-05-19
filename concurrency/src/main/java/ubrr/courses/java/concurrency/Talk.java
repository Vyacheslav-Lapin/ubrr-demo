package ubrr.courses.java.concurrency;

import io.vavr.CheckedRunnable;
import lombok.SneakyThrows;
import lombok.val;

public class Talk extends Thread {

  @Override
  @SneakyThrows
  public void run() {
    for (int i = 0; i < 8; i++) {
      System.out.println("Talking");
      Thread.sleep(1_000);
    }
  }

  @SneakyThrows
  public static void main(String[] __) {

    val talk = new Talk();
    talk.setDaemon(true);

    val walk = new Thread(CheckedRunnable.of(() -> {
      for (int i = 0; i < 8; i++) {
        System.out.println("Walking");
        Thread.sleep(1_000);
      }
    }).unchecked(), "Вася");
    walk.setDaemon(true);

    final State state = talk.getState();
    final State state1 = walk.getState();

    System.out.println("talk.getState() = " + state);
    System.out.println("walk.getState() = " + state1);

    walk.start();
    talk.start();

    final State state2 = talk.getState();
    final State state3 = walk.getState();

    System.out.println("talk.getState() = " + state2);
    System.out.println("walk.getState() = " + state3);

//    walk.join();
//    talk.join();

    final State state4 = talk.getState();
    final State state5 = walk.getState();

    System.out.println("talk.getState() = " + state4);
    System.out.println("walk.getState() = " + state5);

    System.out.println("Lorem ipsum dolor sit amet");

    System.out.println("talk.getName() = " + talk.getName());
    System.out.println("walk.getName() = " + walk.getName());

//    throw new RuntimeException("!!!");

  }
}
