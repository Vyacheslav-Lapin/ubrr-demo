package ru.ubrr.exceptions;

public class Main {
  public static void main(String[] args) {
    System.out.println("getString() = " + getString());
  }

  public static String getString() {
    try {
      return "мама мыла раму!";
    } finally {
      System.out.println("\"МАМА МЫЛА РАМУ!!!\" = " + "МАМА МЫЛА РАМУ!!!");
    }
  }
}
