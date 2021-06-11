package ru.ubrr.it.courses.java.formats.xml.server;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ubrr.it.courses.java.formats.xml.wsclient.HelloService;

class ServerRunnerTest {

  @Test
  @SneakyThrows
  @DisplayName("WSDL Server works correctly")
  void wSDLServerWorksCorrectlyTest() {
    assertThat(new HelloService().getHelloPort().sayHello("Henry")).isNotNull()
        .isEqualTo("Hello, Henry");
  }
}
