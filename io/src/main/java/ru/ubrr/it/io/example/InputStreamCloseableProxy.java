package ru.ubrr.it.io.example;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor
public class InputStreamCloseableProxy extends InputStream {

  @Delegate(excludes = Closeable.class)
  final InputStream inputStream;

  @Override
  public void close() {
  }
}
