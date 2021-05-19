package ru.ubrr.collections;

import static java.util.Collections.reverseOrder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.experimental.ExtensionMethod;

@ExtensionMethod(Collections.class)
public class CollectionsExample {
  public static void main(String[] args) {
    List<String> list1 = Arrays.asList("red", "greean", "blue");
    list1.sort();
    System.out.println(list1);

    List<String> list2 = Arrays.asList("greean", "red", "yellow", "blue");
    list2.sort(reverseOrder());
    System.out.println(list2);
  }
}
