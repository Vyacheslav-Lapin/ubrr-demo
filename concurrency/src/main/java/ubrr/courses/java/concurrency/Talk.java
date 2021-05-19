package ubrr.courses.java.concurrency;

import lombok.val;

public class Talk extends Thread {

  @Override
  public void run() {
    for (int i = 0; i < 8; i++) {
      System.out.println("Talking");
      try {
        Thread.sleep(1_000);
      } catch (InterruptedException e) {
        System.err.print(e);
      }
    }
  }

  public static void main(String[] args) {

    val talk = new Talk();

    val walk = new Thread(() -> {
      for (int i = 0; i < 8; i++) {
        System.out.println("Walking");
        try {
          Thread.sleep(1_000);
        } catch (InterruptedException e) {
          System.err.print(e);
        }
      }
    });

    talk.start();
    walk.start();
  }
}
