package ru.ubrr.collections;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapExample {

  public static final String KEY = "Lewis";

  public static void main(String[] args) {
//    Map<String, Integer> linkedHashMap = new LinkedHashMap<>(16, 0.75f, false);
    Map<String, Integer> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
    linkedHashMap.put("Smith", 30);
    linkedHashMap.put("Anderson", 31);
    linkedHashMap.put(KEY, 29);
    linkedHashMap.put("Cook", 29);
    System.out.println(linkedHashMap);
    System.out.println("\nThe age for Lewis is " + linkedHashMap.get(KEY));
    System.out.println(linkedHashMap);
  }
}
