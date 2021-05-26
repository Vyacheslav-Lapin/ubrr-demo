package ru.ubrr.jdbc.pool;

import io.vavr.Function2;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.NonFinal;
import lombok.val;
import ru.ubrr.jdbc.properties.PropertiesBinder;

//@ExtensionMethod(PropertiesBinder.class)
public enum ConnectionPool implements Supplier<Connection>, Closeable {

  INSTANCE;

  public static ConnectionPool getInstance() {
    return INSTANCE;
  }

  BlockingQueue<PooledConnection> pooledConnections;

  @SneakyThrows
  ConnectionPool() {
    val connectionFactory = PropertiesBinder.from(ConnectionFactory.class);

    val pooledConnectionFactory = Function2.of(PooledConnection::new)
                                      .reversed()
                                      .apply(this::closePolledConnection);

    pooledConnections = connectionFactory.get()
                            .map(pooledConnectionFactory)
                            .collect(Collectors.toCollection(
                                connectionFactory.toSizedCollection(ArrayBlockingQueue::new)));

    val initSqlFilesContent = connectionFactory.getInitSqlFilesContent();

    @Cleanup val connection = get();
    @Cleanup val statement = connection.createStatement();
    statement.executeUpdate(initSqlFilesContent);
  }

  @NonFinal
  volatile boolean closed;

  @Override
  public void close() {
    closed = true;
    pooledConnections.forEach(PooledConnection::reallyClose);
  }

  @SneakyThrows
  private void closePolledConnection(PooledConnection pooledConnection) {
    if (closed) pooledConnection.reallyClose();
    else if (pooledConnection.isClosed())
      throw new RuntimeException("Attempting to close closed connection.");
    else if (pooledConnection.isReadOnly()) {
      pooledConnection.setReadOnly(false);
      if (!pooledConnections.offer(pooledConnection))
        throw new SQLException("Error allocating connection in the pool.");
    }
  }

  @Override
  @SneakyThrows
  public Connection get() {
    if (closed)
      throw new RuntimeException("Pool is already closed!");
    return pooledConnections.take();
  }
}
