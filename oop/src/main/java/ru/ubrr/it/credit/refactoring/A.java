package ru.ubrr.it.credit.refactoring;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

public interface A {
  void method();

  default void method2(@NotNull Integer integer) {
    System.out.println("integer = " + integer);
  }
}

class AImpl implements A {

  public void method() {
    System.out.println("Lorem ipsum dolor sit amet");
  }

  @SneakyThrows
  public void method2(@NotNull Integer integer) {
    System.out.println(integer);
  }

  @SneakyThrows
  public final void method3(@NotNull Integer integer, @NotNull String s) {
    System.out.println("s + integer = " + s + integer);
  }
}

@RequiredArgsConstructor
class B implements A {

  @Delegate(types = A.class)
  final AImpl a;

  @SneakyThrows
  public void method4() {
    this.method2(1);
  }



  //  @Override
  //  public void method() {
  //    a.method();
  //  }
}

class C {
  public static void main(String[] args) {
    A a = new B(new AImpl());
    a.method(); // "Lorem"
  }
}
