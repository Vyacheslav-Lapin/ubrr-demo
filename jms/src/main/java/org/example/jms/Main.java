package org.example.jms;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;

@UtilityClass
public class Main {

  @SneakyThrows
  public void main(String... __) {

    @Cleanup val sender = new ExampleMessageSender();

    //noinspection unused
    @Cleanup val receiver = new ExampleMessageReceiver();

    for (int i = 1; i <= 5; i++) {
      sender.sendMessage("Hello world! %d".formatted(i));
      Thread.sleep(300);
    }
  }
}
