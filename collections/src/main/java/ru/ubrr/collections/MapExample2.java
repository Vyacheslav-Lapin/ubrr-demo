package ru.ubrr.collections;

public class MapExample2 {
  public static void main(String[] args) {
    System.getProperties()
        .forEach((key, value) -> System.out.printf("%s -- %s%n", key, value));
  }
}
