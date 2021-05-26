package ru.ubrr.jdbc.properties;

import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.function.Supplier;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;

@FunctionalInterface
public interface PropertiesTemplate extends Supplier<Properties> {

  @SneakyThrows
  static PropertiesTemplate from(String fileName) {
    @Cleanup val inputStream = PropertiesTemplate.class.getResourceAsStream(
        String.format("/%s.properties", fileName));
    val properties = new Properties();
    properties.load(inputStream);
    return () -> properties;
  }

  default Optional<String> getAndRemove(String key) {
    return Optional.ofNullable((String) get().remove(key));
  }

  default int size() {
    return get().size();
  }

  default boolean containsOnlyKeys(String... keys) {
    val properties = get();
    return properties.size() == keys.length
            && properties.keySet().containsAll(Set.of(keys)); // Java 9+
//            && properties.keySet().containsAll(Arrays.asList(keys)); // Java 8+
}

  }
