package ru.ubrr.it.courses.java.fp;

import io.vavr.Function0;
import io.vavr.Function1;
import java.util.Date;
import java.util.Random;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@ExtensionMethod(Function1Util.class)
public class SupplyDemo {
  public static void main(String[] args) {
    Function0<Integer> getRandomInt =
        Function1Util.supply(
            Function1.<Long, Random>of(Random::new)
                .andThen(Random::nextInt),
            18L);

    //        .supply(18L);

    System.out.println("getRandomInt.get() = " + getRandomInt.get());
    System.out.println("getRandomInt.get() = " + getRandomInt.get());
    System.out.println("getRandomInt.get() = " + getRandomInt.get());
    System.out.println("getRandomInt.get() = " + getRandomInt.get());
    System.out.println("getRandomInt.get() = " + getRandomInt.get());

    var date = new Date() {
      @SneakyThrows
      @Contract(pure = true)
      public @NotNull String method() {
        return "Lorem ipsum dolor sit amet";
      }
    };

    System.out.println("date.method() = " + date.method());

//    return date;
  }
}


sealed class A permits B, C {

}

sealed class B extends A {

}

final class C extends A {

}

final class D extends B {

}
