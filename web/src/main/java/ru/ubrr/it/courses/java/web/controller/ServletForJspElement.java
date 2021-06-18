package ru.ubrr.it.courses.java.web.controller;

import java.time.LocalDate;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import ru.ubrr.it.courses.java.web.model.SimplePerson;

@WebServlet("/use-bean")
public class ServletForJspElement extends PostHttpServlet {

  //<%!...%>

  @Override
  @SneakyThrows
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    req.setAttribute(
        "mybean",
        new SimplePerson()
            .setName(req.getParameter("name"))
            .setSurname(req.getParameter("surname"))
            .setDate(LocalDate.now()));

    //<%...%>

    @Cleanup val out = resp.getWriter();
    out.println(/*<%=...%>*/);

    if ("naming".equals(req.getParameter("command")))
      req.getRequestDispatcher("use-bean.jsp")
          .forward(req, resp);
    //...
  }
}
