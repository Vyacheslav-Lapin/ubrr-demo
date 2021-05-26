package ru.ubrr.jdbc.pool;

import io.vavr.CheckedFunction0;
import io.vavr.Function2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.val;
import ru.ubrr.it.io.InputStreamUtils;

@RequiredArgsConstructor
public class ConnectionFactory implements Supplier<Stream<Connection>> {

  String url;
  String user;
  String password;

  int poolSize;

  String sqlScriptsPath;

  public Stream<Connection> get() {
    return Stream.generate(
        CheckedFunction0.of(
            () -> DriverManager.getConnection(url, user, password)).unchecked())
               .limit(poolSize);
  }

  public String getInitSqlFilesContent() {

    val toSqlScriptFileName =
        Function2.<String, String, String>of("/%s/%s.sql"::formatted)
            .apply(sqlScriptsPath);

    return IntStream.iterate(1, operand -> operand + 1)
               .mapToObj(String::valueOf)
               .map(toSqlScriptFileName)
               .map(InputStreamUtils::getFileAsString)
               .takeWhile(Optional::isPresent)
               .map(Optional::get)
               .collect(Collectors.joining());
  }

  public <E, C extends Collection<E>> Supplier<C> toSizedCollection(IntFunction<C> collectionFactory) {
    return () -> collectionFactory.apply(poolSize);
  }
}
