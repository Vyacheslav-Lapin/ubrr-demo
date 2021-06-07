package ru.ubrr.it.courses.java.formats.xml.dom;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.w3c.dom.Element;
import ru.ubrr.it.courses.java.formats.xml.Food;

@ExtensionMethod({NodeListUtil.class, ElementUtil.class})
public class DomParserExample {
  @SneakyThrows
  public static void main(String... __) {
    val doc =
        DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder()
            .parse(new File("formats/src/main/resources/menu.xml"));

    doc.getDocumentElement().normalize();

    System.out.printf("Корневой элемент: %s%n", doc.getDocumentElement().getNodeName());

    // создадим из from список объектов Food
    NodeListUtil.<Element>toStream(doc.getElementsByTagName("food"))
        .map(DomParserExample::getFood)
        .forEach(System.out::println); // печатаем в консоль информацию по каждому объекту
  }

  // создаем из узла документа объект Language
  private static Food getFood(Element element) {
    return Food.builder()
        .id(Integer.parseInt(element.getAttribute("id")))
        .name(element.getInnerTagValue("name"))
        .price(element.getInnerTagValue("price"))
        .description(element.getInnerTagValue("description"))
        .calories(Integer.parseInt(element.getInnerTagValue("calories"))).build();
  }
}
