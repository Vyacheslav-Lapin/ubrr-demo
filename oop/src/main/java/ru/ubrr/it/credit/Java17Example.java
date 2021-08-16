package ru.ubrr.it.credit;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.With;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@UtilityClass
@ExtensionMethod({Arrays.class})
public class Java17Example {

  public void main(String[] args) {
    val o = new Object();
    System.out.println(formatterPatternSwitchOld(o));
    System.out.println(formatterPatternSwitchNew(o));

    System.out.println(formatterPatternSwitchOld("123"));
    System.out.println(formatterPatternSwitchNew("123"));

    System.out.println(formatterPatternSwitchOld(124356476L));
    System.out.println(formatterPatternSwitchNew(124356476L));

    int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    ints.sort();
    //    Arrays.sort(ints);
    System.out.println("ints = " + Arrays.toString(ints));
  }

  String formatterPatternSwitchOld(@NotNull Object o) {
    return Match(o).of(
        Case($(instanceOf(Integer.class)), (Integer i) -> "int %d".formatted(i)),
        Case($(instanceOf(Long.class)), (Long l) -> "long %d".formatted(l)),
        Case($(instanceOf(Double.class)), (Double d) -> "double %f".formatted(d)),
        Case($(instanceOf(String.class)), (String s) -> "String %s".formatted(s)),
        Case($(), Object::toString)
    );
  }

  @Contract(pure = true)
  private static @NotNull Set<Character> toCharacters(@NotNull String s) {
    Set<Character> unique = new HashSet<>();
    for (char c : s.toCharArray()) {
      unique.add(c);
    }
    return unique;
  }

  String formatterPatternSwitchNew(@NotNull Object o) {
    return switch (o) {
      case Integer i -> "int %d".formatted(i);
      case Long l -> "long %d".formatted(l);
      case Double d -> "double %f".formatted(d);
      case String s -> "String %s".formatted(s);
      default -> o.toString();
    };
  }
}
