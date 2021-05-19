package ubrr.courses.java.concurrency;

import lombok.Getter;

@Getter
public class NewThread implements Runnable {

  String name;
  Thread thread;

  NewThread(String name) {
    this.name = name;
    thread = new Thread(this, name);
    System.out.println("New thread: " + thread);
    thread.start();
  }

  @Override
  public void run() {
    try {
      for (int i = 5; i > 0; i--) {
        System.out.println(name + ": " + i);
        Thread.sleep(1_000);
      }
    } catch (InterruptedException e) {
      System.out.println(name + " interrupted.");
    }
    System.out.println(name + " exiting.");
  }
}
