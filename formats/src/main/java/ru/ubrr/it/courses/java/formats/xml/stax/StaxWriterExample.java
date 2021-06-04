package ru.ubrr.it.courses.java.formats.xml.stax;

import java.io.FileWriter;
import javax.xml.stream.XMLOutputFactory;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;

public class StaxWriterExample {

  @SneakyThrows
  public static void main(String... __) {

    @Cleanup val writer = XMLOutputFactory
        .newInstance()
        .createXMLStreamWriter(
            new FileWriter("formats/src/main/resources/output2.xml"));

    writer.writeStartDocument();
    writer.writeStartElement("document");
    writer.writeStartElement("data");
    writer.writeAttribute("name", "value");
    writer.writeCharacters("content");
    writer.writeEndElement();
    writer.writeEndElement();
    writer.writeEndDocument();

    writer.flush();
  }
}
