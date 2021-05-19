package ubrr.courses.java.concurrency;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Clicker extends Thread {

  @Getter
  int click;

  private volatile boolean running = true;

  public void run() {
    while (running)
      click++;
  }

  public void stopClick() {
    running = false;
  }
}
