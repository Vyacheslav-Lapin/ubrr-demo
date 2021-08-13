package ru.ubrr.it.credit;

public class Main {

  public static void main(String[] args) {

    System.out.println("method() = " + method("5", 2));
    System.out.println("Hello, World!");

    //region for
    for (String arg : args) {
      System.out.println("arg = " + arg);
    }
    //endregion

  }

//  public static int method(String s, int x) throws SQLException {
//    return 5;
//  }

  public static String method(String s, int x) {
//    String s = "jkhdfg";
    System.out.println("s = " + s);
    return "jz";
  }

}
