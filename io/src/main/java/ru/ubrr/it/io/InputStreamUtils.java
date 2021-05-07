package ru.ubrr.it.io;

import io.vavr.CheckedConsumer;
import java.io.InputStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InputStreamUtils {

  public void withFileInputStream(String fileName, CheckedConsumer<InputStream> inputStreamConsumer) {

    if (!fileName.startsWith("/")) fileName = "/" + fileName;
    if (!fileName.endsWith(".properties")) fileName += ".properties";
    inputStreamConsumer.unchecked().accept(
        InputStreamUtils.class.getResourceAsStream(fileName)
    );
  }
}
