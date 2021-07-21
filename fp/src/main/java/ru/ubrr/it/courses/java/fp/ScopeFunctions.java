package ru.ubrr.it.courses.java.fp;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class ScopeFunctions {

  public <T> void with(@NotNull T self,
                       @NotNull CheckedConsumer<T> checkedConsumer) {
    checkedConsumer.unchecked().accept(self);
  }

  public <T> @NotNull T apply(@NotNull T self,
                              @NotNull CheckedConsumer<T> checkedConsumer) {
    with(self, checkedConsumer);
    return self;
  }

  public <T, R> @NotNull R map(@NotNull T self,
                               @NotNull CheckedFunction1<T, R> mapper) {
    return mapper.unchecked().apply(self);
  }

  public <T, R1, R2> @NotNull R2 map2(@NotNull T self,
                                      @NotNull CheckedFunction1<T, R1> mapper1,
                                      @NotNull CheckedFunction1<R1, R2> mapper2) {
    return mapper2.unchecked().apply(mapper1.unchecked().apply(self));
  }

  public <T, R> Tuple2<T, R> zip(@NotNull T self,
                                 @NotNull CheckedFunction1<? super T, R> mapper) {
    return Tuple.of(self, mapper.unchecked().apply(self));
  }
}
