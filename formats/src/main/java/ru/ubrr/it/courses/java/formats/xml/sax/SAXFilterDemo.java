package ru.ubrr.it.courses.java.formats.xml.sax;

import lombok.SneakyThrows;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXFilterDemo {

  @SneakyThrows
  public static void main(String... __) {
    XMLReader reader = XMLReaderFactory.createXMLReader();
    NamespaceFilter namespaceFilter = new NamespaceFilter(reader);
    ElementFilter elementFilter = new ElementFilter();
    elementFilter.setParent(namespaceFilter);
    MenuSaxHandler handler = new MenuSaxHandler();
    elementFilter.setContentHandler(handler);
    elementFilter.parse(new InputSource("formats/src/main/resources/menu.xml"));
  }
}
