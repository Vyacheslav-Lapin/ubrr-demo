package ru.ubrr.it.credit.reflection;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static java.util.Spliterator.ORDERED;

import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import io.vavr.Function1;
import io.vavr.Function3;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class PackageClassFinder {

  public final Function<Tuple2<String, File>, Stream<Class<?>>> TO_SINGLE_STREAM_OF_CLASSES =
      CheckedFunction1.<String, Class<?>>of(Class::forName).unchecked()
          .<Tuple2<String, String>>compose(tuple2 -> tuple2.apply("%s.%s"::formatted))
          .<Tuple2<String, String>>compose(tuple2 -> tuple2.map2(fileName -> fileName.substring(0, fileName.length() - 6)))
          .<Tuple2<String, File>>compose(tuple2 -> tuple2.map2(File::getName))
          .andThen(Stream::of);

  public final UnaryOperator<String> DOTS_TO_SLASHES =
      Function3.<String, Character, Character, String>of(String::replace)
          .reversed()
          .apply('/', '.')::apply;

  public <T> Stream<T> toStream(Enumeration<T> enumeration) {
    return Function1.<Enumeration<T>, Iterator<T>>of(Enumeration::asIterator)
        .andThen(iterator -> Spliterators.spliteratorUnknownSize(iterator, ORDERED))
        .andThen(spliterator -> StreamSupport.stream(spliterator, false))
        .apply(enumeration);
  }

  /**
   * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
   *
   * @param packageName The base package
   * @return The classes
   */
  @SneakyThrows
  public Stream<Class<?>> getClasses(@NotNull String packageName) {

    //    isDirectoryExists(packageName);

    return CheckedFunction2.of(ClassLoader::getResources).unchecked()
        .andThen(PackageClassFinder::toStream)
        .tupled()
        .<Tuple2<ClassLoader, String>>compose(t -> t.map2(DOTS_TO_SLASHES))
        .<ClassLoader>compose(classLoader -> Tuple.of(classLoader, packageName))
        .compose(Thread::getContextClassLoader)
        .apply(Thread.currentThread())

        .map(URL::getFile)
        .map(File::new)
        .flatMap(file1 -> findClasses(file1, packageName));
  }

  private void isDirectoryExists(@NotNull String packageName) {
    val file = new File(packageName);
    if (!file.exists() || !file.isDirectory())
      throw new RuntimeException("There is no dir for package param value");
  }

  /**
   * Recursive method used to find all classes in a given directory and subdirectories.
   *
   * @param directory   The base directory
   * @param packageName The package name for classes found inside the base directory
   * @return The classes
   */
  @SneakyThrows
  private @NotNull Stream<Class<?>> findClasses(@NotNull File directory, String packageName) {
    return Stream.of(directory)
        .filter(File::exists)
        .map(File::listFiles)
        .filter(Objects::nonNull)
        .flatMap(Arrays::stream)
        .flatMap(file -> getClassesStream(packageName, file));
  }

  @NotNull
  @SneakyThrows
  private Stream<Class<?>> getClassesStream(String packageName, @NotNull File file) {
    return Match(file).of(
        Case($(File::isDirectory), () -> findClasses(file, "%s.%s".formatted(packageName, file.getName()))),
        Case($(__ -> file.getName().endsWith(".class")), () -> TO_SINGLE_STREAM_OF_CLASSES.apply(Tuple.of(packageName, file))),
        Case($(), Stream::empty)
    );
  }
}
