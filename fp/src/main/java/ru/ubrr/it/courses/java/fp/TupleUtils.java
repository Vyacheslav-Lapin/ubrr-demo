package ru.ubrr.it.courses.java.fp;

import io.vavr.CheckedFunction2;
import io.vavr.CheckedFunction3;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class TupleUtils {

  public <T1, T2, R> @NotNull R applyChecked(@NotNull Tuple2<? extends T1, ? extends T2> self,
                                             @NotNull CheckedFunction2<? super T1, ? super T2, ? extends R> f) {
    return self.apply(f.unchecked());
  }

  public <T1, T2, R> Tuple2<R, T2> exchange1(@NotNull Tuple2<T1, T2> self,
                                             @NotNull CheckedFunction2<T1, T2, ? extends R> f) {
    return Tuple.of(f.unchecked().tupled().apply(self), self._2);
  }

  public <T1, T2, R> Tuple2<T1, R> exchange2(@NotNull Tuple2<T1, T2> self,
                                             @NotNull CheckedFunction2<T1, T2, ? extends R> f) {
    return Tuple.of(self._1, f.unchecked().tupled().apply(self));
  }

  public <T1, T2, T3, R> @NotNull R applyChecked(@NotNull Tuple3<? extends T1, ? extends T2, ? extends T3> self,
                                                 @NotNull CheckedFunction3<? super T1, ? super T2, ? super T3, ? extends R> f) {
    return self.apply(f.unchecked());
  }

  public <T1, T2, T3, R> Tuple3<T1, R, T3> exchange2(@NotNull Tuple3<T1, T2, T3> self,
                                                     @NotNull CheckedFunction3<T1, T2, T3, ? extends R> f) {
    return Tuple.of(self._1, f.unchecked().tupled().apply(self), self._3);
  }

  public <T1, T2, T3, R> Tuple3<R, T2, T3> exchange1(@NotNull Tuple3<T1, T2, T3> self,
                                                     @NotNull CheckedFunction3<T1, T2, T3, ? extends R> f) {
    return Tuple.of(f.unchecked().tupled().apply(self), self._2, self._3);
  }

  public <T1, T2, T3, R> Tuple3<T1, T2, R> exchange3(@NotNull Tuple3<T1, T2, T3> self,
                                                     @NotNull CheckedFunction3<T1, T2, T3, ? extends R> f) {
    return Tuple.of(self._1, self._2, f.unchecked().tupled().apply(self));
  }
}
