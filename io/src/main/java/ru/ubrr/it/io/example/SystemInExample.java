package ru.ubrr.it.io.example;

import lombok.SneakyThrows;
import lombok.val;

public class SystemInExample {
  @SneakyThrows
  public static void main(String... __) {
    val size = 5;
    val bytes = new byte[size];
    val inputStream = System.in;
    if (inputStream.read(bytes) == size) {
      System.out.write(bytes);
      System.out.write('\n');
      System.out.flush();
    }
  }
}
