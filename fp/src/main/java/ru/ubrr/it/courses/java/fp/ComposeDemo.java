package ru.ubrr.it.courses.java.fp;

import io.vavr.Function1;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class ComposeDemo {

  public void main(String[] args) {
    String s = method1(55);
    Double aDouble = method2(s);
    System.out.println("aDouble = " + aDouble);

    System.out.println("aDouble = " + method2(method1(55)));

    System.out.println("function1.apply(55) = " +
                           Function1.of(ComposeDemo::method1)
                               .andThen(ComposeDemo::method2)
                               .apply(55));

    System.out.println("function1.apply(55) = " +
                           Function1.of(ComposeDemo::method2)
                               .compose(ComposeDemo::method1)
                               .apply(55));
  }

  @Contract(pure = true)
  public @NotNull String method1(@NotNull int i) {
    return "" + i;
  }

  @Contract(pure = true)
  public @NotNull Double method2(@NotNull String s) {
    return Double.parseDouble(s);
  }
}
