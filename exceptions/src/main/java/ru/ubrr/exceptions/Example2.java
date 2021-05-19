package ru.ubrr.exceptions;

public class Example2 {
  public static void main(String[] args) {
    try {
      int a = args.length;
      try {
//        System.exit(-1);
        if (a == 1) { a = a / (a - a); }
        if (a == 2) { int с[] = { 1 }; с[42] = 99; }
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Индекс выходит за границу массива: "
                               + e);
        throw e;
        // or // throw new
        // ArithmeticException(“Исключение в catch.");
      }
      int b = 42 / a;
      System.out.println("a = " + a);
    } catch (ArithmeticException e) {
      System.out.println("Деление на ноль: " + e);
    } catch (Exception e) {
      System.out.println("Общий обработчик.");
    } finally {
      System.out.println("\"123\" = " + "123");
    }

  }
}
