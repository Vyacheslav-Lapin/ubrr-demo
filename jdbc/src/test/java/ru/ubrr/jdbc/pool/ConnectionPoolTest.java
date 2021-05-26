package ru.ubrr.jdbc.pool;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
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
    @Cleanup val connection = ConnectionPool.getInstance().get();
    @Cleanup val statement = connection.createStatement();
    @Cleanup ResultSet resultSet = statement.executeQuery(SQL);

    if (resultSet.next())
      assertThat(resultSet.getString("first_name"))
          .isEqualTo("Jose");
  }
}
