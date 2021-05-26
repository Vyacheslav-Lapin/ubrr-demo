package ru.ubrr.it.io;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

//@UtilityClass

public final class TestUtils {

  static String LINE_SEPARATOR = System.lineSeparator();
  static String TEST_RESOURCES_PATH = "./src/test/resources/";

  @NotNull
  @SneakyThrows
  @Contract("_ -> new")
  static public String fromSystemOutPrint(@NotNull Runnable task) {
    return OutputStreamUtils.fromPrintStream(
        printStream -> {
          val realOut = System.out;
          System.setOut(printStream);
          task.run();
          System.setOut(realOut);
        }
    );
  }

  @NotNull
  static public String fromSystemOutPrintln(@NotNull Runnable runnable) {
    String s = fromSystemOutPrint(runnable);
    return s.endsWith(LINE_SEPARATOR) ?
               s.substring(0, s.length() - LINE_SEPARATOR.length())
               : s;
  }

  @NotNull
  @Contract(pure = true)
  static public String toTestResourceFilePath(@NotNull String fileName) {
    return TEST_RESOURCES_PATH + fileName;
  }
}
