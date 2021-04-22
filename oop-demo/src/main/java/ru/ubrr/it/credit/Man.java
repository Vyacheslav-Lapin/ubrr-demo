package ru.ubrr.it.credit;

public class Man {
  public static String form = "man";
  static {
    System.out.println("static block in Man.");
  }
  public static void stMan() {
    System.out.println("static method in Man.");
  }

}
