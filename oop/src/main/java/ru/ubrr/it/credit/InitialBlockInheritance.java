package ru.ubrr.it.credit;

public class InitialBlockInheritance {
  public static void main(String[] args) {
    Doc.stMan();
    System.out.println("Run.");
    Doc doc = new Doc();
    System.out.println(doc.form);
    Doc.stDoctor();
  }

}
