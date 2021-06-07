package ru.ubrr.it.courses.java.formats.xml.stax;

import static java.lang.Integer.parseInt;
import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;
import static ru.ubrr.it.courses.java.formats.xml.MenuTagName.FOOD;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import ru.ubrr.it.courses.java.formats.xml.Food;
import ru.ubrr.it.courses.java.formats.xml.Food.FoodBuilder;
import ru.ubrr.it.courses.java.formats.xml.MenuTagName;

@UtilityClass
@ExtensionMethod(XmlStreamReaderUtils.class)
public class StaxMenuParser {

  @SneakyThrows
  public void main(String... __) {
    @Cleanup val input = new FileInputStream("formats/src/main/resources/menu.xml");
    process(XMLInputFactory.newInstance().createXMLStreamReader(input))
        .forEach(System.out::println);
  }

  @SneakyThrows
  private List<Food> process(XMLStreamReader reader) {

//    return reader.toStream() // sequential
//        .filter(type -> type == START_ELEMENT)
//        .mapToObj(__ -> reader.getLocalName())
//        .map(MenuTagName::getElementTagName)
//        .filter(menuTagName -> menuTagName == FOOD)
//        .map(__ -> reader)
//        .map(StaxMenuParser::toFood);

    FoodBuilder foodBuilder = null;
    MenuTagName elementName = null;
    val menu = new ArrayList<Food>();

    while (reader.hasNext()) {

      // определение типа "прочтённого" элемента (тега)
      int type = reader.next();

      switch (type) {

        case START_ELEMENT -> {
          elementName = MenuTagName.getElementTagName(reader.getLocalName());
          if (elementName == FOOD)
            foodBuilder = Food.builder()
                .id(parseInt(reader.getAttributeValue(null, "id")));
        }

        case CHARACTERS -> {
          assert foodBuilder != null;
          val text = reader.getText().trim();
          if (text.isEmpty()) break;
          switch (Objects.requireNonNull(elementName)) {
            case NAME -> foodBuilder.name(text);
            case PRICE -> foodBuilder.price(text);
            case DESCRIPTION -> foodBuilder.description(text);
            case CALORIES -> foodBuilder.calories(parseInt(text));
          }
        }

        case END_ELEMENT -> {
          assert foodBuilder != null;
          elementName = MenuTagName.getElementTagName(reader.getLocalName());
          if (elementName == FOOD) menu.add(foodBuilder.build());
        }
      }
    }
    return menu;
  }

//  private Food toFood(XMLStreamReader reader) {
//    val foodBuilder = Food.builder();
//
//    return reader.toStream()
//        .takeWhile(type ->
//                       type == END_ELEMENT
//                           && MenuTagName.getElementTagName(reader.getLocalName()) == FOOD)
//  }
}
