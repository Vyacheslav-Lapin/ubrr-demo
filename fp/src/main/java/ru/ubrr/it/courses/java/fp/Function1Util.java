package ru.ubrr.it.courses.java.fp;

import io.vavr.Function0;
import io.vavr.Function1;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class Function1Util {

  @Contract(pure = true)
  public <T,R> @NotNull Function0<R> supply(@NotNull Function1<T, R> self,
                                            @NotNull T t) {
    return () -> self.apply(t);
  }
}
