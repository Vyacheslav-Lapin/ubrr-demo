package ru.ubrr.it.courses.java.formats.xml.sax;

import lombok.Data;

@Data
public class Food {
  final int id;
  String name;
  String price;
  String description;
  int calories;
}
