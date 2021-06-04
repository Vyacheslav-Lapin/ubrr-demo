package ru.ubrr.it.courses.java.formats.xml.sax;

import lombok.SneakyThrows;
import lombok.val;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXFilterDemo {

  @SneakyThrows
  public static void main(String... __) {
    val reader = XMLReaderFactory.createXMLReader();

    val elementFilter = new ElementFilter();
    elementFilter.setParent(new NamespaceFilter(reader));
    elementFilter.setContentHandler(new MenuSaxHandler());
    elementFilter.parse(new InputSource("formats/src/main/resources/menu.xml"));
  }
}
