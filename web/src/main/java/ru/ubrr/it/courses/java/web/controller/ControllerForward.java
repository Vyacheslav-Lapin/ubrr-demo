package ru.ubrr.it.courses.java.web.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebServlet("/ControllerForward")
public class ControllerForward extends PostHttpServlet {

  @Override
  @SneakyThrows
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    req.getRequestDispatcher("main.jsp")
        .forward(req, resp);
  }
}
