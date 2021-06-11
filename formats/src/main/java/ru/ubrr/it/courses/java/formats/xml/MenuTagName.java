package ru.ubrr.it.courses.java.formats.xml;

public enum MenuTagName {
  NAME, PRICE, DESCRIPTION, CALORIES, FOOD, BREAKFAST_MENU;

  public static MenuTagName from(String qName) {
    return MenuTagName.valueOf(
        qName
            .toUpperCase()
            .replace("-", "_"));
  }

  public static MenuTagName getElementTagName(String element) {
    return switch (element) {
      case "food" -> FOOD;
      case "price" -> PRICE;
      case "description" -> DESCRIPTION;
      case "calories" -> CALORIES;
      case "breakfast-menu" -> BREAKFAST_MENU;
      case "name" -> NAME;
      default -> throw new EnumConstantNotPresentException(MenuTagName.class, element);
    };
  }
}
