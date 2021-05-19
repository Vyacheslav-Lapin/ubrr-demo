package ubrr.courses.java.concurrency;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;
import static java.lang.Thread.NORM_PRIORITY;

import java.util.Arrays;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClickerTest {

  @Test
  @SneakyThrows
  @DisplayName("Clicker works correctly")
  void clickerWorksCorrectlyTest() {
    Thread.currentThread().setPriority(MAX_PRIORITY);

    val his = new Clicker[]{
        new Clicker(), new Clicker(), new Clicker(), new Clicker(), new Clicker(), new Clicker()
        , new Clicker(), new Clicker(), new Clicker(), new Clicker(), new Clicker(), new Clicker()
        , new Clicker(), new Clicker(), new Clicker(), new Clicker(), new Clicker(), new Clicker()};
    val los = new Clicker[]{
        new Clicker(), new Clicker(), new Clicker(), new Clicker(), new Clicker(), new Clicker()
        , new Clicker(), new Clicker(), new Clicker(), new Clicker(), new Clicker(), new Clicker()
        , new Clicker(), new Clicker(), new Clicker(), new Clicker(), new Clicker(), new Clicker()};

    for (Clicker hi : his) hi.setPriority(MAX_PRIORITY);
    for (Clicker lo : los) lo.setPriority(MIN_PRIORITY);

    los[0].start();
    his[0].start();
    los[1].start();
    his[1].start();
    los[2].start();
    his[2].start();
    los[3].start();
    his[3].start();
    los[4].start();
    his[4].start();
    los[5].start();
    his[5].start();
    los[6].start();
    his[6].start();
    los[7].start();
    his[7].start();
    his[8].start();
    los[8].start();
    his[9].start();
    los[9].start();
    his[10].start();
    los[10].start();
    his[11].start();
    los[11].start();
    his[12].start();
    los[12].start();
    his[13].start();
    los[13].start();
    his[14].start();
    los[14].start();
    his[15].start();
    los[15].start();
    his[16].start();
    los[16].start();
    his[17].start();
    los[17].start();

    try {
      Thread.sleep(50);
    } catch (InterruptedException e) {
      System.out.println("Main thread interrupted.");
    }

    for (Clicker lo : los) lo.stopClick();
    for (Clicker hi : his) hi.stopClick();

    try {
      for (Clicker hi : his) hi.join();
      for (Clicker lo : los) lo.join();
    } catch (InterruptedException e) {
      System.out.println("InterruptedException caught");
    }

    val hiAverage = Arrays.stream(his)
                      .mapToInt(Clicker::getClick)
                      .average();

    val loAverage = Arrays.stream(los)
                        .mapToInt(Clicker::getClick)
                        .average();

    loAverage.ifPresent(average -> System.out.println("Low-priority thread: " + average));
    hiAverage.ifPresent(average -> System.out.println("High-priority thread: " + average));
    loAverage.ifPresent(loAvg -> hiAverage.ifPresent(hiAvg -> System.out.println(hiAvg - loAvg)));
    loAverage.ifPresent(loAvg -> hiAverage.ifPresent(hiAvg -> System.out.println(
        (hiAvg - loAvg) / hiAvg * 100 + "%")));
  }
}
