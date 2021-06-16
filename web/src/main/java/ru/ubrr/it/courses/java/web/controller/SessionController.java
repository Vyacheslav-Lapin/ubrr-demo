package ru.ubrr.it.courses.java.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.val;

@WebServlet("/SessionController")
public class SessionController extends PostHttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    val session = req.getSession();

    session.setAttribute(
        req.getParameter("paramName"),
        req.getParameter("paramValue"));

    req.getRequestDispatcher("/session.jsp").include(req, resp);
  }
}
