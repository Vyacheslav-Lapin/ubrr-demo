package ru.ubrr.it.io.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PropertiesTemplateTest {

  PropertiesTemplate properties = PropertiesTemplate.from("props");

  @Test
  void getAndRemove() {
    // given
    assertThat(properties.size()).isEqualTo(4);
    assertTrue(properties.containsOnlyKeys("prop1", "prop2", "p3.p1", "p3.p2"));

    // then
    assertThat(properties.getAndRemove("prop1"))
            .isPresent()
            .contains("50");

    assertThat(properties.size()).isEqualTo(3);
    assertTrue(properties.containsOnlyKeys("prop2", "p3.p1", "p3.p2"));
  }
}
