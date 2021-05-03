package ru.ubrr.it.strings;

public class SpringsConcatExample {
  public static void main(String[] args) {
//    String s = "";
    StringBuffer s = new StringBuffer(90);

    for (int i = 0; i < 25; i++) {
//      s += i + ", ";
//      s = s + (i + ", ");
//      s = new StringBuffer(s).append(
//          new StringBuffer(i).append(", "))
//      .toString();
      s.append(i).append(", ");
    }

    System.out.println("s = " + s);
  }
}
