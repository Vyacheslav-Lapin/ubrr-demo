package ru.ubrr.it.io.properties;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;
import java.util.function.UnaryOperator;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import ru.ubrr.it.io.InputStreamUtils;

@UtilityClass
@ExtensionMethod(InputStreamUtils.class)
public class PropertiesBinder {

  @NotNull
  public <T> T from(@NotNull Class<T> tClass) {
    String name = tClass.getSimpleName();
    return from(name.substring(0, 1).toLowerCase() + name.substring(1), tClass);
  }

  @NotNull
  public <T> T from(String fileName, Class<T> tClass) {
    val properties = new Properties();
    fileName.withFileInputStream(properties::load); // InputStreamUtils.withFileInputStream(fileName, properties::load);
    return from(properties::getProperty, tClass);
  }

  @NotNull
  public <T> T from(UnaryOperator<String> getProperty, Class<T> tClass) {
    return from(getProperty, getMaxParamsCountConstructor(tClass));
  }

  @NotNull
  @SneakyThrows
  public <T> T from(UnaryOperator<String> getProperty,
                    @NotNull Constructor<T> constructor) {
    return constructor.newInstance(
        Arrays.stream(constructor.getParameters())
            .map(parameter -> resolveParameter(getProperty, parameter))
            .toArray()
    );
  }

  @NotNull
  @SneakyThrows
  private <T> Constructor<T> getMaxParamsCountConstructor(@NotNull Class<T> propsClass) {
    //noinspection unchecked
    return (Constructor<T>) Arrays.stream(propsClass.getConstructors())
        .max(Comparator.comparingInt(Constructor::getParameterCount))
        .orElseThrow();
    //                                .orElseThrow(() -> new PropsBindException("There is no any constructor!"));
  }

  private Object resolveParameter(@NotNull UnaryOperator<String> getValue,
                                  @NotNull Parameter parameter) {
    String name = parameter.getName();
    String value = getValue.apply(name);
    Class<?> type = parameter.getType();

    if (type == String.class) return value;
    if (type == int.class || type == Integer.class) return Integer.parseInt(value);
    if (type == double.class || type == Double.class) return Double.parseDouble(value);
    if (type == long.class || type == Long.class) return Long.parseLong(value);
    if (type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(value);
    if (type == char.class || type == Character.class) return value.charAt(0);
    if (type == float.class || type == Float.class) return Float.parseFloat(value);
    if (type == byte.class || type == Byte.class) return Byte.parseByte(value);
    if (type == short.class || type == Short.class) return Short.parseShort(value);

    String prefix = name + ".";
    return from(propertyName -> getValue.apply(prefix + propertyName), type);
  }
}
