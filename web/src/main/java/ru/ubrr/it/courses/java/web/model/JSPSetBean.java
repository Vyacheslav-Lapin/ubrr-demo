package ru.ubrr.it.courses.java.web.model;

import java.util.Iterator;
import java.util.Set;
import lombok.Data;

@Data
public class JSPSetBean {

  Iterator<?> it;
  final Set<?> set;

  public int getSize(){
    it = set.iterator();
    return set.size();
  }

  public String getElement(){
    return it.next().toString(); //todo 18.06.2021: hasNext -> Optional
  }

}
