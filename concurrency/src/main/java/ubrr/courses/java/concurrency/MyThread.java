package ubrr.courses.java.concurrency;

import lombok.SneakyThrows;

public class MyThread extends Thread {

  public MyThread(String threadname, ThreadGroup tgOb) {
    super(tgOb, threadname);
    System.out.println("New thread: " + this);
    start();
  }

  public void run() {
    try {
      for (int i = 5; i > 0; i--) {
        System.out.println(getName() + ": " + i);
        Thread.sleep(1_000);
      }
    } catch (Exception e) {
      System.out.println("Exception in " + getName());
    }
    System.out.println(getName() + " exiting.");
  }

  @SneakyThrows
  public static void main(String[] args) {
    ThreadGroup groupA = new ThreadGroup("Group A");
    ThreadGroup groupB = new ThreadGroup("Group B");

    MyThread ob1 = new MyThread("One", groupA);
    MyThread ob2 = new MyThread("Two", groupA);
    MyThread ob3 = new MyThread("Three", groupB);
    MyThread ob4 = new MyThread("Four", groupB);

    try {
      Thread.sleep(2_500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    groupA.interrupt();
    ob3.join();
    ob4.join();

    System.out.println("----------------------------------------------------");

    ThreadGroup mainGroup = new ThreadGroup("mainGroup");
    ThreadGroup groupC = new ThreadGroup(mainGroup, "Group C");
    ThreadGroup groupD = new ThreadGroup(mainGroup, "Group D");
    MyThread ob5 = new MyThread("One", groupC);
    MyThread ob6 = new MyThread("Two", groupC);
    MyThread ob7 = new MyThread("Three", groupD);
    MyThread ob8 = new MyThread("Four", groupD);

    groupC.list();
    groupD.list();

    mainGroup.interrupt();
  }
}
