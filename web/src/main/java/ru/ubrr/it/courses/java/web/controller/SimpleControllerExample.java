package ru.ubrr.it.courses.java.web.controller;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.val;

@WebServlet(
    urlPatterns = "/hello",
    initParams = @WebInitParam(name="param3", value = "Lorem ipsum dolor sit amet"))
public class SimpleControllerExample extends HttpServlet {

  //...<%!...%>

  @Override
  @SneakyThrows
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    resp.setStatus(200);
    resp.setContentType("text/html");
    //...

    //    <%...%>

    try (val out = resp.getWriter()) {
      out.println("""
          <html>
            <head>
              <title>Hello, World</title>
            </head>
            <body>
              <h1>Hello, World!!</h1>
            </body>
          </html>""");
      //          </html>""" + new Date().toString());
    }
  }
}
