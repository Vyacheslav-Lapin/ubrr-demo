package ru.ubrr.it.courses.java.formats.xml.sax;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

public class NamespaceFilter extends XMLFilterImpl {

  public NamespaceFilter(XMLReader reader) {
    super(reader);
  }

  public void startPrefixMapping(String prefix, String uri) throws SAXException {
    System.out.printf("startPrefixMapping in NamespaceFilter -%s, %s%n", prefix, uri);
    super.startPrefixMapping(prefix, uri + "2");
  }
}
