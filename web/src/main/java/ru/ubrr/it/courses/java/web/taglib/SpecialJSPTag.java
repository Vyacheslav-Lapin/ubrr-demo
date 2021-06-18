package ru.ubrr.it.courses.java.web.taglib;

import javax.servlet.jsp.tagext.TagSupport;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.val;
import ru.ubrr.it.courses.java.web.model.JSPSetBean;

@Data
public class SpecialJSPTag extends TagSupport {

  JSPSetBean set;

  public void setSet(JSPSetBean set) {
    this.set = set;
  }

  @Override
  @SneakyThrows
  public int doStartTag() {

    val out = pageContext.getOut();

    val size = set.getSize();
    out.write("Size = <b>(%d)</b>".formatted(size));

    out.write("<table border=\"1\">");

    for (var i = 0; i < size; i++)
      out.println(String.format("<tr><td>%s</td></tr>", set.getElement()));

    out.println("</table>");

    return SKIP_BODY;
  }
}
