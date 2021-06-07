package ru.ubrr.it.courses.java.formats.xml;

import java.io.File;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import lombok.SneakyThrows;
import org.xml.sax.SAXException;

public class ValidationExample {

  public static final String SCHEMA_PATH = "formats/src/main/resources/xsd/food.xsd";
  public static final String XML_PATH = "formats/src/main/resources/menu.xml";
  public static final String XML_SCHEMA_NAMESPACE = "http://www.w3.org/2001/XMLSchema";

  @SneakyThrows
  public static void main(String... __) {

    try {
      SchemaFactory.newInstance(XML_SCHEMA_NAMESPACE)
          .newSchema(new File(SCHEMA_PATH))
          .newValidator()
          .validate(new StreamSource(XML_PATH));

      System.out.println(" is valid.");

    } catch (SAXException ex) {
      System.out.printf(" is not valid because %s%n", ex.getMessage());
    }
  }
}
