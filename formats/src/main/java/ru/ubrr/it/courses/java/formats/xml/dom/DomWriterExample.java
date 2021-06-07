package ru.ubrr.it.courses.java.formats.xml.dom;

import java.io.File;
import java.io.FileWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;

public class DomWriterExample {

  public static final String FILE_NAME = "formats/src/main/resources/domMenu.xml";

  @SneakyThrows
  public static void main(String... __) {
    val document = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder()
        .newDocument();

    val breakfastMenu = document.createElement("breakfast-menu");
    val food = document.createElement("food");
    food.setAttribute("id", "234");

    val name = document.createElement("name");
    name.setTextContent("Waffles");

    food.appendChild(name);
    breakfastMenu.appendChild(food);
    document.appendChild(breakfastMenu);

    deleteFileIfExists(FILE_NAME);
    @Cleanup val writer = new FileWriter(FILE_NAME);
    TransformerFactory.newInstance()
        .newTransformer()
        .transform(
            new DOMSource(document),
            new StreamResult(
                writer));
  }

  private static void deleteFileIfExists(@SuppressWarnings("SameParameterValue") String fileName) {
    val file = new File(fileName);
    if (file.exists())
      //noinspection ResultOfMethodCallIgnored
      file.delete();
  }
}
