package ru.ubrr.it.credit.lambdas;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@ExtensionMethod(TestUtils.class)
class AnonimTest {

  final Runnable main = Anonim::main;

  @Test
  @SneakyThrows
  @DisplayName("main method works correctly")
  void mainMethodWorksCorrectlyTest() {
    assertThat(main.fromSystemOutPrintln()).isNotNull()
        .matches(s -> s.startsWith("args"))
        .isEqualTo("""
            args.length = 0
            sm().getAsInt() = 55
            sm().getAsInt() = 56
            sm().getAsInt() = 3""");
  }

  @Test
  @SneakyThrows
  @DisplayName("sm method works correctly")
  void smMethodWorksCorrectlyTest() {
    assertThat(Anonim.sm()).isNotNull()
        .matches(intSupplier -> intSupplier.getAsInt() == 55);
  }
}
