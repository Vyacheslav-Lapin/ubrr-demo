package ru.ubrr.jdbc.pool;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.ubrr.jdbc.pool.ConnectionPool.INSTANCE;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConnectionPoolTest {

  @Language("H2")
  static String SQL = """
      select id, first_name, last_name, permission, dob, email, password, address, telephone
      from Person""";

  @Test
  @SneakyThrows
  @DisplayName("Connection pool works correctly")
  void connectionPoolWorksCorrectlyTest() {
    @Cleanup Connection connection = INSTANCE.get();
    @Cleanup Statement statement = connection.createStatement();
    @Cleanup ResultSet resultSet = statement.executeQuery(SQL);

    assertThat(resultSet.next()).isTrue();

    assertThat(resultSet.getString("first_name"))
        .isEqualTo("Jose");
  }
}
