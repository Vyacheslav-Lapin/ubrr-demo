package ru.ubrr.it.courses.java.formats.xml.stax;

import io.vavr.CheckedFunction1;
import io.vavr.CheckedPredicate;
import java.util.stream.IntStream;
import javax.xml.stream.XMLStreamReader;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;

@UtilityClass
public class XmlStreamReaderUtils {

  @SneakyThrows
  public IntStream toStream(XMLStreamReader reader) {
    val next = CheckedFunction1.<Integer, Integer>of(__ -> reader.next()).unchecked();
    val hasNext = CheckedPredicate.<Integer>of(__ -> reader.hasNext()).unchecked();
    return !reader.hasNext() ? IntStream.empty() :
        IntStream.iterate(reader.next(), hasNext::test, next::apply);
  }
}
