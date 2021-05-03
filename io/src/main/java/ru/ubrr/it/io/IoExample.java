package ru.ubrr.it.io;

import java.util.Objects;
import lombok.SneakyThrows;
import lombok.val;

public class IoExample {

  @SneakyThrows
  public static void main(String... args) {
    // Адреса файлов:
    // 1. Абсолютный адрес /usr/local/kkljxfg.txt
    // 2. Относительный путь ./gfh/,jhsv/hjgsdf/jhrg.txt
    // 3. CLASSPATH

    try (val inputStream = IoExample.class.getResourceAsStream("/1.txt");) {
      System.out.println("s = " + new String(
          Objects.requireNonNull(inputStream)
              .readAllBytes()).trim().intern());
    }
  }
}
