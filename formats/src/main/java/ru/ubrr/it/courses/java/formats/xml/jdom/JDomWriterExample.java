package ru.ubrr.it.courses.java.formats.xml.jdom;

import static ru.ubrr.it.courses.java.formats.xml.Food.Fields.description;
import static ru.ubrr.it.courses.java.formats.xml.jdom.JDomExample.NAMESPACE;
import static ru.ubrr.it.courses.java.formats.xml.jdom.JDomExample.XML_FILE_PATH;

import java.io.FileOutputStream;
import lombok.SneakyThrows;
import lombok.val;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class JDomWriterExample {

  @SneakyThrows
  public static void main(String... __) {
    val targetId = "1";
    val document = new SAXBuilder().build(XML_FILE_PATH);

    document
        .getRootElement()
        .getChildren()
        .stream()
        .filter(element -> element.getAttributeValue("id").equals(targetId))
        .forEach(element -> element
            .getChild(description, NAMESPACE)
            .setText("Lorem ipsum dolor sit amet"));

    new XMLOutputter().output(
        document,
        new FileOutputStream(
            XML_FILE_PATH.replace("menu", "jdom_menu")));
  }
}
