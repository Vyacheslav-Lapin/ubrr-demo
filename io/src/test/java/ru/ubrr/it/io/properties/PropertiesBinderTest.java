package ru.ubrr.it.io.properties;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@ExtensionMethod(PropertiesBinder.class)
class PropertiesBinderTest {

  @Test
  @SneakyThrows
  @DisplayName("PropsBinder works correctly")
  void propsBinderWorksCorrectlyTest() {
    assertThat(Props.class.from()).isNotNull()
        .matches(props -> props.getProp1() == 50)
        .matches(props -> props.getProp2().equals("qwerty!"))
        .extracting(Props::getP3)
        .extracting(Props2::getP1, Props2::getP2)
        .contains(2, "qwerty!!!");
  }
}
