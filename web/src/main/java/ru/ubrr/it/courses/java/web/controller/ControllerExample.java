package ru.ubrr.it.courses.java.web.controller;

import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

//@WebServlet("/tomain")
public class ControllerExample extends HttpServlet {

  @Override
  @SneakyThrows
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    req.setAttribute("param1", new Date());
    req.setAttribute("param2", getServletConfig().getInitParameter("param2"));
    req.getRequestDispatcher("/WEB-INF/jsp/main.jsp")
        .forward(req, resp);
  }
}
