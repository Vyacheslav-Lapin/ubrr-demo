package ru.ubrr.it.io;

import java.util.Scanner;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;

public class ScannerRightUsageExample {

  @SneakyThrows
  public static void main(String... __) {
    {
      @Cleanup val con = new Scanner(new InputStreamCloseableProxy(System.in));
//      @Cleanup val con = new Scanner(System.in);
      while (con.hasNextInt()) {
        int n = con.nextInt();
        System.out.println("n = " + n);
      }
    }

    System.out.println("System.in.read() = " + System.in.read());

    //    var bytes2 = new byte[System.in.available()];
//    for (int i = 0; i < bytes2.length; )
//      bytes2[i++] = (byte) System.in.read();

//    for (byte b : bytes2)
//      System.out.print(b);
  }
}
