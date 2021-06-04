package ru.ubrr.it.courses.java.formats.xml.sax;

public enum MenuTagName {
  NAME, PRICE, DESCRIPTION, CALORIES, FOOD, BREAKFAST_MENU;

  static MenuTagName from(String qName) {
    return MenuTagName.valueOf(
        qName
            .toUpperCase()
            .replace("-", "_"));
  }
}
