package ru.ubrr.it.courses.java.fp;

import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckedFunction1Util {

  public <T, R> CheckedFunction0<R> supply(CheckedFunction1<T, R> self, T param) {
    return () -> self.apply(param);
  }
}
