package ru.ubrr.jdbc.pool;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;

@RequiredArgsConstructor
public class PooledConnection implements Connection {

  @Delegate(excludes = Closeable.class)
  Connection connection;

  Consumer<PooledConnection> closer;

  @Override
  public void close() throws SQLException {
    closer.accept(this);
  }

  @SneakyThrows
  public void reallyClose() {
    connection.close();
  }
}
