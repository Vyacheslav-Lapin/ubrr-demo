package ru.ubrr.it.io;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;

public class PipedStreamsExample {

  @SneakyThrows
  public static void main(String... __) {
    @Cleanup val pipeIn = new PipedInputStream();
    @Cleanup val pipeOut = new PipedOutputStream(pipeIn);

    for (int i = 0; i < 20; i++)
      pipeOut.write(i);

    int willRead = pipeIn.available();
    int[] toRead = new int[willRead];

    for (int i = 0; i < willRead; i++) {
      toRead[i] = pipeIn.read();
      System.out.print(toRead[i] + " ");
    }
  }
}
