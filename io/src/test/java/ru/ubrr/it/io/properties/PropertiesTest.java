package ru.ubrr.it.io.properties;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Properties;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PropertiesTest {

  private final static String PROPS_FILE_NAME = "/props.properties";
  private final static Properties properties = new Properties();

  @BeforeAll
  @SneakyThrows
  static void setUp() {
    @Cleanup val inputStream = PropertiesTest.class.getResourceAsStream(PROPS_FILE_NAME);
    properties.load(inputStream);
  }

  @Test
  @DisplayName("getProperties method works correctly")
  void getProperties() {
    assertThat(properties.getProperty("prop1")).isEqualTo("50");
    assertThat(properties.getProperty("prop2", "3")).isEqualTo("qwerty!");
    assertThat(properties.getProperty("prop3", "3")).isEqualTo("3");
  }
}
