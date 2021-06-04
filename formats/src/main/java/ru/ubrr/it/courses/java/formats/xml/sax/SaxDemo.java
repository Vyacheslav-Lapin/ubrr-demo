package ru.ubrr.it.courses.java.formats.xml.sax;

import java.util.List;
import lombok.SneakyThrows;
import lombok.val;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;

public class SaxDemo {
  @SneakyThrows
  public static void main(String... __) {

//    val reader = javax.xml.parsers.SAXParserFactory.newInstance();
    val reader = XMLReaderFactory.createXMLReader();
    val handler = new MenuSaxHandler();
    reader.setContentHandler(handler);
    reader.parse(new InputSource("formats/src/main/resources/menu.xml"));

    List<Food> menu = handler.getFoods();
    System.out.println("menu = " + menu);
  }
}
