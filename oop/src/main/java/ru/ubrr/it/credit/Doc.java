package ru.ubrr.it.credit;

public class Doc extends Man {
  static {
    System.out.println("static block in Doc");
  }
  public static void stDoctor() {
    System.out.println("static method in Doc.");
  }
}

