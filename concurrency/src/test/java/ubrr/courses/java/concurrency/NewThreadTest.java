package ubrr.courses.java.concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NewThreadTest {

  @Test
  void run() {
    NewThread ob1 = new NewThread("One");
    NewThread ob2 = new NewThread("Two");
    NewThread ob3 = new NewThread("Three");

    assertThat(ob1.getThread().isAlive()).isTrue();
    assertThat(ob2.getThread().isAlive()).isTrue();
    assertThat(ob3.getThread().isAlive()).isTrue();

    try {
      System.out.println("Waiting for threads to finish.");
      ob1.getThread().join();
      ob2.getThread().join();
      ob3.getThread().join();
    } catch (InterruptedException e) {
      System.out.println("Main thread Interrupted");
    }

    assertThat(ob1.getThread().isAlive()).isFalse();
    assertThat(ob2.getThread().isAlive()).isFalse();
    assertThat(ob3.getThread().isAlive()).isFalse();

    System.out.println("Main thread exiting.");
  }
}
