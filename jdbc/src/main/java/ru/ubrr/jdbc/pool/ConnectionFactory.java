package ru.ubrr.jdbc.pool;

import io.vavr.CheckedFunction0;
import io.vavr.Function2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;
import java.util.Optional;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ru.ubrr.it.io.InputStreamUtils;

@Slf4j
@RequiredArgsConstructor
public class ConnectionFactory implements Supplier<Stream<Connection>> {

  String url;
  String user;
  String password;

  int poolSize;

  String initScriptsPath;

  public Stream<Connection> get() {
    return Stream.generate(
        CheckedFunction0.of(
            () -> DriverManager.getConnection(url, user, password)).unchecked())
               .limit(poolSize);
  }

  public String getInitSqlFilesContent() {

    val toSqlScriptFileName =
        Function2.<String, String, String>of("/%s/%s.sql"::formatted)
            .apply(initScriptsPath);

    StringBuilder sb = new StringBuilder();
    for (int operand = 1; ; operand = operand + 1) {
      String s = String.valueOf(operand);
      String s1 = toSqlScriptFileName.apply(s);
      Optional<String> fileAsString = InputStreamUtils.getFileAsString(s1);
      if (!fileAsString.isPresent()) {
        break;
      }
      String s2 = fileAsString.get();
      sb.append(s2);
    }
    return sb.toString();

//    return IntStream.iterate(1, operand -> operand + 1)
//               .mapToObj(String::valueOf)
//               .map(toSqlScriptFileName)
//               .map(InputStreamUtils::getFileAsString)
//               .takeWhile(Optional::isPresent)
//               .map(Optional::get)
//               .collect(Collectors.joining());
  }

  public <E, C extends Collection<E>> Supplier<C> toSizedCollection(IntFunction<C> collectionFactory) {
    return () -> collectionFactory.apply(poolSize);
  }
}
