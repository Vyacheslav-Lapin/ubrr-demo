package ru.ubrr.it.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpExample {

  public static void main(String[] args) {
    Pattern p = Pattern.compile("J2SE");

    String candidateString_1 = "J2SE is the only one for me";
    String candidateString_2 = "For me, it's J2SE, or nothing at all";
    String candidateString_3 = "J2SEistheonlyoneforme";

    Matcher matcher = p.matcher(candidateString_1);
    System.out.println(":" + candidateString_1 + ": matches?: " + matcher.lookingAt());

    matcher.reset(candidateString_2);
    System.out.println(":" + candidateString_2 + ": matches?: " + matcher.lookingAt());

    matcher.reset(candidateString_3);
    System.out.println(":" + candidateString_3 + ": matches?: " + matcher.lookingAt());
  }
}
