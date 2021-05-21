package ubrr.courses.java.concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.SneakyThrows;
import lombok.val;

public class ConsoleClock extends Thread {

  @SneakyThrows
  public static void main(String... __) {
    val clock = new ConsoleClock();
    clock.start();
    Thread.sleep(3_000);
    clock.suspend();
    Thread.sleep(3_000);
    clock.resume();
  }

  public void run() {

    for (int i = 0; i < 10; i++) {

      System.out.println(i + " - " + time());
      try {
        Thread.sleep(1_000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private String time() {
    val d = new Date();
    val s = new SimpleDateFormat("hh/mm/ss");
    return s.format(d);
  }
}
