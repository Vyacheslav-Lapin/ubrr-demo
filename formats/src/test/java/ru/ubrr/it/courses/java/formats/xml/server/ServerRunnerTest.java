package ru.ubrr.it.courses.java.formats.xml.server;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ubrr.it.courses.java.formats.xml.wsclient.HelloService;

class ServerRunnerTest {

  @Test
  @SneakyThrows
  @DisplayName("WSDL Server works correctly")
  void wSDLServerWorksCorrectlyTest() {
    Assertions.assertThat(new HelloService().getHelloPort().sayHello("Henry")).isNotNull()
        .isEqualTo("Hello, Henry");
  }
}
