package ubrr.courses.java.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.val;

public class SharedResource {

  private List<Integer> list = new ArrayList<>();

  public void setElement(Integer element) {
    list.add(element);
  }

  public Integer getELement() {
    return !list.isEmpty() ? list.remove(0) : null;
  }
}

class IntegerSetterGetter extends Thread {
  private SharedResource resource;
  private boolean run;

  private Random rand = new Random();

  public IntegerSetterGetter(String name, SharedResource resource) {
    super(name);
    this.resource = resource;
    run = true;
  }

  public void stopThread() {
    run = false;
  }

  public void run() {
    int action;

    try {
      while (run) {
        action = rand.nextInt(1_000);
        if (action % 2 == 0) getIntegersFromResource();
        else setIntegersIntoResource();
      }
      System.out.printf("Поток %s завершил работу.%n", getName());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void getIntegersFromResource() throws InterruptedException {

    Integer number;

    synchronized (resource) {
      System.out.printf("Поток %s хочет извлечь число.%n", getName());
      number = resource.getELement();
      while (number == null) {
        System.out.printf("Поток %s ждет пока очередь заполнится.%n", getName());
        resource.wait();
        System.out.println("Поток " + getName()
                               + " возобновил работу.");
        number = resource.getELement();
      }
      System.out.printf("Поток %s извлек число %d%n", getName(), number);
    }
  }

  private void setIntegersIntoResource() {
    Integer number = rand.nextInt(500);
    synchronized (resource) {
      resource.setElement(number);
      System.out.println("Поток " + getName() + " записал число " + number);
      resource.notifyAll();
    }
  }

  @SneakyThrows
  public static void main(String[] args) {
    val resource = new SharedResource();
    go(resource, "Вася");
    go(resource, "Петя");
    go(resource, "Вова");
    go(resource, "Фёдор");
    go(resource, "Маша");
    go(resource, "Вася2");
    go(resource, "Петя2");
    go(resource, "Вова2");
    go(resource, "Фёдор2");
    go(resource, "Маша2");
    go(resource, "Вася3");
    go(resource, "Петя3");
    go(resource, "Вова3");
    go(resource, "Фёдор");
    go(resource, "Маша3");

    TimeUnit.SECONDS.sleep(5);
  }

  private static void go(SharedResource resource, String name) {
    IntegerSetterGetter вася;
    вася = new IntegerSetterGetter(name, resource);
    вася.setDaemon(true);
    вася.start();
  }
}
