package ru.ubrr.it.courses.java.formats.xml.jdom;

import static org.jdom2.Namespace.getNamespace;

import lombok.SneakyThrows;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

public class JDomExample {

  public static final String XML_FILE_PATH = "formats/src/main/resources/menu.xml";
  public static Namespace NAMESPACE = getNamespace("http://ubrr.ru/it/courses/java/formats/xml");

  @SneakyThrows
  public static void main(String... __) {
    new SAXBuilder()
        .build(XML_FILE_PATH)
        .getRootElement()
        .getChildren()
        .stream()
        .map(element -> element.getChildText("name", NAMESPACE))
        .forEach(System.out::println);
  }
}
