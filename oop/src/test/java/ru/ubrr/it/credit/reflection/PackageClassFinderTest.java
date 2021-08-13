package ru.ubrr.it.credit.reflection;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@ExtensionMethod(PackageClassFinder.class)
class PackageClassFinderTest {

  @Test
  @SneakyThrows
  @DisplayName("findClasses method works correctly")
  void findClassesMethodWorksCorrectlyTest() {
    assertThat("ru.ubrr.it.credit.reflection".getClasses()).isNotNull()
        .hasSize(2)
        .containsExactlyInAnyOrder(PackageClassFinder.class, PackageClassFinderTest.class);
  }
}
