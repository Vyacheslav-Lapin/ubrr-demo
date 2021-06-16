package ru.ubrr.it.courses.java.web.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebServlet("/usebean")
public class ServletForJspElement extends PostHttpServlet {

  @Override
  @SneakyThrows
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    if ("naming".equals(req.getParameter("command")))
      req.getRequestDispatcher("/usebean.jsp")
          .forward(req, resp);
  }
}
