package ru.ubrr.it.courses.java.formats.xml.stax;

import static java.lang.Integer.parseInt;
import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;
import static ru.ubrr.it.courses.java.formats.xml.MenuTagName.FOOD;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import ru.ubrr.it.courses.java.formats.xml.Food;
import ru.ubrr.it.courses.java.formats.xml.MenuTagName;

public class StaxMenuParser {
  @SneakyThrows
  public static void main(String... __) {
    @Cleanup val input = new FileInputStream("formats/src/main/resources/menu.xml");

    List<Food> menu = process(
        XMLInputFactory
            .newInstance()
            .createXMLStreamReader(input));

    for (Food food : menu) {
      System.out.println(food.getName());
      System.out.println(food.getCalories());
    }
  }

  @SneakyThrows
  private static List<Food> process(XMLStreamReader reader) {

    List<Food> menu = new ArrayList<>();

    Food food = null;

    MenuTagName elementName = null;

    while (reader.hasNext()) {

      // определение типа "прочтённого" элемента (тега)
      int type = reader.next();

      switch (type) {

        case START_ELEMENT -> {
          elementName = MenuTagName.getElementTagName(reader.getLocalName());
          if (elementName == FOOD)
            food = new Food(parseInt(reader.getAttributeValue(null, "id")));
        }

        case CHARACTERS -> {
          String text = reader.getText().trim();
          if (text.isEmpty()) break;
          switch (elementName) {
            case NAME -> food.setName(text);
            case PRICE -> food.setPrice(text);
            case DESCRIPTION -> food.setDescription(text);
            case CALORIES -> food.setCalories(parseInt(text));
          }
        }

        case END_ELEMENT -> {
          elementName = MenuTagName.getElementTagName(reader.getLocalName());
          if (elementName == FOOD) menu.add(food);
        }
      }
    }
    return menu;
  }
}
