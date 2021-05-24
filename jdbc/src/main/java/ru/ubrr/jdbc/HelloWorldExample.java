package ru.ubrr.jdbc;

import static ru.ubrr.jdbc.Student.Fields.groupId;
import static ru.ubrr.jdbc.Student.Fields.id;
import static ru.ubrr.jdbc.Student.Fields.name;

import java.sql.DriverManager;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import lombok.val;

public class HelloWorldExample {

  @SneakyThrows
  public static void main(String[] args) {
//    val aClass = Class.forName("org.h2.Driver");

    @Cleanup val connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
    @Cleanup val statement = connection.createStatement();
    statement.executeUpdate("create table student (%s identity, %s varchar not null, %s int)".formatted(id, name, groupId));
    statement.executeUpdate("insert into student (%s, %s) values ('Вася Пупкин', 123456), ('Фёдор Ермаков', 654321)".formatted(name, groupId));
    @Cleanup val resultSet = statement.executeQuery("select %s, %s, %s from student".formatted(id, name, groupId));
    while (resultSet.next())
      System.out.println(
          Student.from(resultSet.getInt(id),
              resultSet.getString(name),
              resultSet.getInt(groupId)));
  }
}

@Value
@FieldNameConstants
@AllArgsConstructor(staticName = "from")
class Student {
  int id;
  String name;
  int groupId;
}
