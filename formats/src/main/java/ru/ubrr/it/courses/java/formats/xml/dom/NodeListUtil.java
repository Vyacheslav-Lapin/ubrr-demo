package ru.ubrr.it.courses.java.formats.xml.dom;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@UtilityClass
public class NodeListUtil {

  public <T extends Node> Stream<T> toStream(NodeList self) {
    //noinspection unchecked
    return (Stream<T>) IntStream.range(0, self.getLength())
        .mapToObj(self::item);
  }
}
