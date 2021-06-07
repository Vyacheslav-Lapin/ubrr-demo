package ru.ubrr.it.courses.java.formats.xml.dom;

import lombok.experimental.UtilityClass;
import org.w3c.dom.Element;

@UtilityClass
public class ElementUtil {

  /**
   * Получаем значение элемента по указанному тегу
   */
  public String getInnerTagValue(Element self, String tagName) {
    return getSingleChild(self, tagName)
        .getChildNodes()
        .item(0)
        .getNodeValue();
  }

  public static Element getSingleChild(Element self, String childName){
    return (Element) self.getElementsByTagName(childName)
        .item(0);
  }

}
