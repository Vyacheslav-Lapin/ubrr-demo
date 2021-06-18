package ru.ubrr.it.courses.java.web.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SimplePerson {
  String name;
  String surname;
  LocalDate date;
}
