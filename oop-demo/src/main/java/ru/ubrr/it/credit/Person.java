package ru.ubrr.it.credit;

import java.util.Set;
import java.util.TreeSet;

public class Person implements Comparable<Person> {
  private String firstName;
  private String lastName;
  private int age;

  public Person(String firstName, String lastName, int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", age=" + age +
               '}';
  }

  public int compareTo(Person anotherPerson) {
    return age - anotherPerson.age;
  }

  public static void main(String[] args) {
    Set<Person> set = new TreeSet<>();
    set.add(new Person("Вася", "Пупкин", 15));
    set.add(new Person("Петя", "Сидоров", 16));
    set.add(new Person("Митя", "Петров", 10));
    System.out.println("set = " + set);
  }
}

