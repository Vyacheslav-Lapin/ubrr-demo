package ru.ubrr.jdbc;

import static ru.ubrr.jdbc.HelloWorldExample.URL;

import java.sql.DriverManager;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;

public class MetaDataExample {

  @SneakyThrows
  public static void main(String[] args) {
    @Cleanup val con = DriverManager.getConnection(URL);
    val meta = con.getMetaData();
    @Cleanup val rs = meta.getTables(
        null,
        null,
        null,
        new String[]{"TABLE"});

    if (rs.next())
      System.out.println(rs.getString(3));
  }
}
