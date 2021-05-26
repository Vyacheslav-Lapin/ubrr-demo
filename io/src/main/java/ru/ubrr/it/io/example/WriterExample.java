package ru.ubrr.it.io.example;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import lombok.Cleanup;
import lombok.SneakyThrows;

public class WriterExample {
  @SneakyThrows
  public static void main(String... __) {
    @Cleanup Writer out = new BufferedWriter(
        new OutputStreamWriter(
            new FileOutputStream("outfilename.txt"), UTF_8));
    out.write("asdf");
  }
}
