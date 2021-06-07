package ru.ubrr.it.courses.java.formats.xml.jdom;

import static org.jdom2.Namespace.getNamespace;

import lombok.SneakyThrows;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

public class JDomExample {

  private static Namespace namespace = getNamespace("http://ubrr.ru/it/courses/java/formats/xml");

  @SneakyThrows
  public static void main(String... __) {
    new SAXBuilder()
        .build("formats/src/main/resources/menu.xml")
        .getRootElement()
        .getChildren()
        .stream()
        .map(element -> element.getChildText("name", namespace))
        .forEach(System.out::println);
  }
}
