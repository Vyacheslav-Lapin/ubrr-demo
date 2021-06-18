package ru.ubrr.it.courses.java.web.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import lombok.Data;
import lombok.SneakyThrows;

@Data
public class JSPTagWithBody extends BodyTagSupport {

  int num;

  public void setNum(int num) {
    this.num = num;
  }

  @Override
  @SneakyThrows
  public int doStartTag() throws JspException {
    pageContext.getOut().write("<table border=\"3\" width=\"100%\"><tr><td>");
    return EVAL_BODY_INCLUDE;
  }

  @SneakyThrows
  @Override
  public int doAfterBody() {
    if (num-- > 1) {
      pageContext.getOut().write("</td></tr><tr><td>");
      return EVAL_BODY_AGAIN;
    } else
      return SKIP_BODY;
  }

  @Override
  @SneakyThrows
  public int doEndTag() {
    pageContext.getOut().write("</td></tr></table>");
    return SKIP_BODY;
  }
}
