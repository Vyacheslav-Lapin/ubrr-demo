package ru.ubrr.it.courses.java.formats.xml;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;
import static lombok.AccessLevel.PRIVATE;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@XmlRootElement
@NoArgsConstructor
@FieldNameConstants
@XmlAccessorType(FIELD)
@AllArgsConstructor(access = PRIVATE)
public class Food {

  @XmlAttribute(required = true)
  int id;

  String name;
  String price;
  String description;
  int calories;
}
