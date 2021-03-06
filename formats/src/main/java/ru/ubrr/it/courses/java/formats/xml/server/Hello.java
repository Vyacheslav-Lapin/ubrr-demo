package ru.ubrr.it.courses.java.formats.xml.server;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Hello {
  public String sayHello(String name) {
    return "Hello, %s".formatted(name);
  }
}
