package ru.ubrr.it.credit.interfaces;

public interface Interface1<T extends Number> {

  default String m1() {
    return m() + 52;
  }

  @Private
  private String m() {
    return "Lorem ipsum dolor sit amet";
  }

  int m2();

  default String m3() {
    return m() + 56;
  }

  static void main(String[] args) {
    var interface1 = new Interface1() {
      @Override
      public int m2() {
        return 10;
      }

      public String m1(int x) {
        return Interface1.super.m1();
      }
    };

    System.out.println("interface1.m1() = " + interface1.m1());
    System.out.println("interface1.m2() = " + interface1.m2());
  }
}

class Abc implements Interface1, Interface2 {

  @Override
  public String m1() {
    return Interface1.super.m1();
  }

  @Override
  public int m2() {
    return 100;
  }
}

interface Interface2 {
  String m1();
}
