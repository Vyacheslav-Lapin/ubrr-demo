package ru.ubrr.it.strings;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceProperty {

  private ResourceBundle bundle;

  public ResourceProperty(Locale locale) {
    bundle = ResourceBundle
                 .getBundle("prop", locale);
  }

  public String getValue(String key) {
    return bundle.getString(key);
  }

  public static void main(String[] args) {
    ResourceProperty myBundle = new ResourceProperty(new Locale("en", "US"));
    System.out.println(myBundle.getValue("prop.key1")); // value01 en US

    myBundle = new ResourceProperty(new Locale("en", "UK"));
    System.out.println(myBundle.getValue("prop.key2")); // value02 en

    myBundle = new ResourceProperty(new Locale("ru", "BY"));
    System.out.println(myBundle.getValue("prop.key1"));  // value01 default

    myBundle = new ResourceProperty(new Locale("ru", "RU"));
    System.out.println(myBundle.getValue("prop.key2")); // значение ru RU
  }
}
