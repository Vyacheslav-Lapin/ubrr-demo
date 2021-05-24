package ubrr.courses.java.concurrency;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.val;

public class CollectionsExample {
  @SneakyThrows
  public static void main(String... __) {
    val pbQueue = new QueueTask();
    for (int i = 0; i < 10; i++) pbQueue.setTask(new Task(i));

    val manager1 = new Manager(pbQueue, "Jonh");
    val manager2 = new Manager(pbQueue, "Pol");

    val th1 = new Thread(manager1);
    val th2 = new Thread(manager2);

    th1.start();
    th2.start();

    th1.join();
    th2.join();
  }
}

@Getter
@Setter
@AllArgsConstructor
class Task implements Comparable<Task> {

  private int taskNumer;

  @Override
  public int compareTo(Task task) {
    int comp = new Random().nextInt(2_000);
    return comp % 2 == 0 ? 1 : comp % 3 == 0 ? -1 : 0;
  }
}

@AllArgsConstructor
class Manager implements Runnable {
  private QueueTask pbQ;
  private String name;

  public void run() {
    Task task;
    while ((task = pbQ.getTask()) != null)
      System.out.printf("%s get task number %d%n", name, task.getTaskNumer());
  }
}

@Getter
class QueueTask {

//  private Queue<Task> queue = new PriorityQueue<>();
  private Queue<Task> queue = new PriorityBlockingQueue<>();

  public Task getTask() {
    return queue.poll();
  }

  public void setTask(Task task) {
    queue.add(task);
  }
}
