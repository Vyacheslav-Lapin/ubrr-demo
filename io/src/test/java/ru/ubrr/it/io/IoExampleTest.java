package ru.ubrr.it.io;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.ubrr.it.io.TestUtils.fromSystemOutPrintln;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IoExampleTest {

  @Test
  @SneakyThrows
  @DisplayName("main method works correctly")
  void mainMethodWorksCorrectlyTest() {
    assertThat(fromSystemOutPrintln(IoExample::main)).isNotNull()
        .isEqualTo("s = Мама мыла раму!");
  }
}
