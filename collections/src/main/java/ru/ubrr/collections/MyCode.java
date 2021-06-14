package ru.ubrr.collections;

import java.util.HashMap;
import java.util.Map;
import lombok.val;
import org.jetbrains.annotations.NotNull;

public class MyCode {

  @NotNull
  private static Map<String, Integer> getStringIntegerHashMap(String str) {

//    str.chars()
//        .

//    val chars = str.toCharArray();
    val results = new HashMap<String, Integer>();
//    for (char symbol : Set.copyOf(List.of(chars))) {
//      int count = (int) Arrays.stream(chars).filter(item -> item.equals(symbol)).count();
//      if (count > 1) results.put(symbol, count);
//    }
    return results;
  }

  public static void main(String... __) {
    System.out.println(getStringIntegerHashMap("HELLO WOrld!!! ==***==="));
  }
}
