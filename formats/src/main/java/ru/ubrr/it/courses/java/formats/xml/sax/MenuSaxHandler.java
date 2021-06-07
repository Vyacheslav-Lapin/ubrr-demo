package ru.ubrr.it.courses.java.formats.xml.sax;

import static ru.ubrr.it.courses.java.formats.xml.MenuTagName.FOOD;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ru.ubrr.it.courses.java.formats.xml.Food;
import ru.ubrr.it.courses.java.formats.xml.Food.FoodBuilder;
import ru.ubrr.it.courses.java.formats.xml.MenuTagName;

public class MenuSaxHandler extends DefaultHandler {

  @Getter
  final List<Food> foods = new ArrayList<>();

  FoodBuilder foodBuilder;
  StringBuilder text;

  @Override
  public void startDocument() {
    System.out.println("Parsing started.");
  }

  @Override
  public void endDocument() {
    System.out.println("Parsing ended.");
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    System.out.printf("startElement -> uri: %s, localName: %s, qName: %s%n", uri, localName, qName);

    text = new StringBuilder();
    if (MenuTagName.from(localName) == FOOD)
      foodBuilder = Food.builder().id(Integer.parseInt(attributes.getValue("id")));
  }

  @Override
  public void characters(char[] buffer, int start, int length) {
    text.append(buffer, start, length);
  }

  @Override
  public void endElement(String uri, String localName, String qName) {
    switch (MenuTagName.from(qName)) {
      case NAME -> foodBuilder.name(text.toString());
      case PRICE -> foodBuilder.price(text.toString());
      case DESCRIPTION -> foodBuilder.description(text.toString());
      case CALORIES -> foodBuilder.calories(Integer.parseInt(text.toString()));
      case FOOD -> {
        foods.add(foodBuilder.build());
        foodBuilder = null;
      }
    }

    text.delete(0, text.length());
  }

  @Override
  public void warning(SAXParseException exception) {
    System.err.printf("WARNING: line %d: %s%n", exception.getLineNumber(), exception.getMessage());
  }

  @Override
  public void error(SAXParseException exception) {
    System.err.printf("ERROR: line %d: %s%n", exception.getLineNumber(), exception.getMessage());
  }

  @Override
  public void fatalError(SAXParseException exception) throws SAXException {
    System.err.printf("FATAL: line %d: %s%n", exception.getLineNumber(), exception.getMessage());
    throw (exception);
  }
}

