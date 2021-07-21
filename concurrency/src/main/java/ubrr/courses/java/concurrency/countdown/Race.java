package ubrr.courses.java.concurrency.countdown;

import java.util.concurrent.CountDownLatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class Race {

  //Создаем CountDownLatch на 8 "условий"
  private final CountDownLatch START = new CountDownLatch(8);

  //Условная длина гоночной трассы
  private final int TRACK_LENGTH = 500_000;

  @SneakyThrows
  public void main(String... __) {
    for (int i = 1; i <= 5; i++) {
      new Thread(new Car(i, (int) (Math.random() * 100 + 50))).start();
      Thread.sleep(1_000);
    }

    while (START.getCount() > 3) // Проверяем, собрались ли все
      Thread.sleep(100);    // у старта. Если нет – ждем 100ms

    Thread.sleep(1_000);

    log.info("На старт!");
    START.countDown(); // Команда дана, уменьшаем счетчик на 1

    Thread.sleep(1_000);

    log.info("Внимание!");
    START.countDown(); // Команда дана, уменьшаем счетчик на 1

    Thread.sleep(1_000);
    log.info("Марш!");

    START.countDown(); // Команда дана, уменьшаем счетчик на 1
    //счетчик становится равным нулю, и все ожидающие потоки
    //одновременно разблокируются
  }

  @Slf4j
  @RequiredArgsConstructor
  public static class Car implements Runnable {

    int carNumber;
    int carSpeed; //считаем, что скорость автомобиля постоянная

    @Override
    @SneakyThrows
    public void run() {
      log.info("Автомобиль №{} подъехал к стартовой прямой", carNumber);

      //Автомобиль подъехал к стартовой прямой - условие выполнено
      START.countDown(); //уменьшаем счетчик на 1

      //метод await() блокирует поток, вызвавший его, до тех пор, пока
      //счетчик CountDownLatch не станет равен 0
      START.await();

      Thread.sleep(TRACK_LENGTH / carSpeed);//ждем пока проедет трассу
      log.info("Автомобиль №{} финишировал!", carNumber);
    }
  }
}
