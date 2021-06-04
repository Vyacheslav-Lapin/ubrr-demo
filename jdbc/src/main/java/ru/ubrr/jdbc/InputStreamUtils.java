package ru.ubrr.jdbc;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import lombok.experimental.UtilityClass;
import lombok.val;

@UtilityClass
public class InputStreamUtils {

  public void withFileInputStream(String fileName, CheckedConsumer<InputStream> inputStreamConsumer) {

    if (!fileName.startsWith("/")) fileName = "/" + fileName;
    if (!fileName.endsWith(".properties")) fileName += ".properties";
    inputStreamConsumer.unchecked().accept(
        InputStreamUtils.class.getResourceAsStream(fileName)
    );
  }

  public Optional<String> getFileAsString(String fileName) {
    val path = fileName.startsWith("/") ? fileName : "/" + fileName;
    return Optional.ofNullable(InputStreamUtils.class.getResource(path))
        .map(URL::getFile)
        .map(s -> s.charAt(2) == ':' ? s.substring(1) : s)
        .map(Paths::get)
        .map(CheckedFunction1.<Path, String>narrow(Files::readString)
            .recover(throwable -> null));
  }
}
