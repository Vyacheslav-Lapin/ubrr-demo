package ru.ubrr.it.courses.java.formats.xml.server;

import javax.xml.ws.Endpoint;
import lombok.val;

public class ServerRunner {
  public static void main(String... __) {
    val url = "http://localhost:1212/hello";
    Endpoint.publish(url, new Hello());
    System.out.printf("Service started @ %s%n", url);
  }
}
