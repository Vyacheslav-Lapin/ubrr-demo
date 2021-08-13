package ru.ubrr.it.credit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class MedicalStaff {}
class Doctor extends MedicalStaff {}
class HeadDoctor extends Doctor {}
class Nurse extends MedicalStaff {}

public class GenericsDemo {

  public static void main(String[] args) {

    System.out.println("GenericsDemo.<Date>m(\"dfg\").getTime() = " + GenericsDemo.m("dfg", Date.class).getTime());

    //    List<? super Doctor> list7 = new ArrayList<HeadDoctor>(); // error
    List<? super Doctor> list6 = new ArrayList<>();
    List<? super Doctor> list5 = new ArrayList<MedicalStaff>();
    List<? super Doctor> list4 = new ArrayList<Object>();

//    list5.add(new Object()); // error
//    list5.add(new MedicalStaff()); // error
    list5.add(new Doctor());
    list5.add(new HeadDoctor());

    List<MedicalStaff> list3 = new ArrayList<>();
    list3.add(new MedicalStaff());
    list3.add(new Doctor());
  }

  public static void method() {
//    List<String> strings = new ArrayList<>();
    List strings = new ArrayList();
    List<Integer> integers = new ArrayList<>();

//    strings.add("kjhsdf");
    strings.add("kjhsdf");
    integers.add(123);
//    integers.add("876345");
//    list.add(new Date());

    System.out.println("((String) strings.get(0)).length() = " + ((String) strings.get(0)).length());
    //    int i = (int) list.get(1);

  }

  static <T> T m(String id, Class<T> aClass) {
    //noinspection unchecked
    return (T) new Date();
  }
}
