package ru.ubrr.it.courses.java.web.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebServlet("/ControllerSendRedirect")
public class ControllerSendRedirect extends PostHttpServlet {

  @Override
  @SneakyThrows
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    resp.sendRedirect("main.jsp");
  }
}
