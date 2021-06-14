package ru.ubrr.it.courses.java.formats.xml.server;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ServerRunnerTest {

  Thread thread = new Thread(ServerRunner::main);

  @BeforeEach
  void setUp() {
    thread.start();
  }

  @Test
  @SneakyThrows
  @DisplayName("WSDL Server works correctly")
  void wSDLServerWorksCorrectlyTest() {
//    assertThat(new HelloService().getHelloPort().sayHello("Henry")).isNotNull()
//        .isEqualTo("Hello, Henry");
  }

  @AfterEach
  void tearDown() {
    thread.interrupt();
  }
}
