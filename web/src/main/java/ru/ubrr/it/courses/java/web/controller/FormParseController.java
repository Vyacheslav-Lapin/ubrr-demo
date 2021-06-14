package ru.ubrr.it.courses.java.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.val;

@WebServlet("/form-parse")
public class FormParseController extends PostHttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    resp.setContentType("text/html");

    String login = req.getParameter("login");
    String password = req.getParameter("password");

    @Cleanup val out = resp.getWriter();
    out.printf("""
          <html>
            <head>
              <title>Hello, World</title>
            </head>
            <body>
              <h1>Login &amp; Password</h1>
              Your login: %s<br />Your password: %s
            </body>
          </html>""".formatted(login, password));
  }
}
