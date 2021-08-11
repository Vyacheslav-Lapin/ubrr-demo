package ru.ubrr.it.credit.reflection;

import static java.util.Spliterator.ORDERED;

import io.vavr.CheckedFunction2;
import io.vavr.Tuple;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class PackageClassFinder {

  /**
   * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
   *
   * @param packageName The base package
   * @return The classes
   */
  @SneakyThrows
  public Stream<Class<?>> getClasses(@NotNull String packageName) {

//    Tuple4<String, Integer, Double, Boolean> tuple4 = Tuple.of("lkjbadfg", 24, 5.7, true);
//
//    String s = tuple4._1;
//    Integer integer = tuple4._2;
//
//    Integer apply = tuple4.apply((s1, integer1, aDouble, aBoolean) -> {
//      return s1.length() + integer1 + aDouble.intValue() + (aBoolean ? 1 : 0);
//    });

    //    isDirectoryExists(packageName);

    return CheckedFunction2.of(ClassLoader::getResources).unchecked()
        .andThen(Enumeration::asIterator)
        .andThen(urlIterator -> Spliterators.spliteratorUnknownSize(urlIterator, ORDERED))
        .andThen(urlSpliterator -> StreamSupport.stream(urlSpliterator, false))
        .tupled()
        .<ClassLoader>compose(o -> Tuple.of(o, packageName.replace('.', '/')))
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
    return Optional.of(directory)
        .filter(File::exists)
        .map(File::listFiles)
        .map(files -> Arrays.stream(files).<Class<?>>flatMap(file -> getClassesStream(packageName, file)))
        .orElseGet(Stream::empty);
  }

  @NotNull
  @SneakyThrows
  private Stream<? extends Class<?>> getClassesStream(String packageName, @NotNull File file) {
    String fileName = file.getName();

    if (file.isDirectory()) {
      assert !file.getName().contains(".");
      return findClasses(file, "%s.%s".formatted(packageName, fileName));
    } else if (fileName.endsWith(".class"))
      return Stream.of(Class.forName("%s.%s".formatted(packageName, fileName.substring(0, fileName.length() - 6))));
    return Stream.empty();
  }
}
