package ru.ubrr.it.credit.lambdas;

import java.util.function.Consumer;
import java.util.function.IntSupplier;

public class Anonim {

//  static Consumer<String> println = System.toTableString::println;
  static Consumer<String> println = x -> System.out.println(x);

  public static void main(final String... args) {

    System.out.println("args.length = " + args.length);

//    args = new String[]{"jkdfhg", "kjhdfg"};

//    var int2 = new Int2() {
//      @Override
//      public String m1(String s) {
//        return Integer.toString(args.length);
//      }
//
//      @Override
//      public int m2(int s) {
//        return 0;
//      }
//    };

    IntSupplier sm = sm();
    println.accept("sm().getAsInt() = " + sm.getAsInt());
    ints[0] = 56;
    println.accept("sm().getAsInt() = " + sm.getAsInt());
    ints[0] = 3;
    println.accept("sm().getAsInt() = " + sm.getAsInt());
  }

  static final int[] ints = new int[]{55};

  static IntSupplier sm() {
    return () -> ints[0];
  }
}

interface Int2 {
  String m1(String s);

  int m2(int s);
}
