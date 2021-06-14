package ru.ubrr.jdbc.pool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Supplier;
import java.util.logging.Logger;
import javax.sql.DataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Accessors(chain = false)
public abstract class DefaultDataSource implements DataSource, Supplier<Connection> {

  @NonFinal
  PrintWriter logWriter = new PrintWriter(System.out);

  @NonFinal
  int loginTimeout; //todo 28.05.2021: найти в документации приемлемый default

  @Override
  public Connection getConnection() throws SQLException {
    return get();
  }

  @Override
  public Connection getConnection(String username, String password) throws SQLException {
    return get();
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return iface.isInstance(this);
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    if (!isWrapperFor(iface))
      throw new SQLException(
          "%s is not compatible with %s"
              .formatted(iface.getName(), getClass().getName()));

    //noinspection unchecked
    return (T) this;
  }

  @Override
  public Logger getParentLogger() {
    return null;
  }
}
