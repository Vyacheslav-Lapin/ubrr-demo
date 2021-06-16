package ru.ubrr.it.courses.java.web.controller;

import io.vavr.CheckedConsumer;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import lombok.SneakyThrows;
import lombok.val;

@WebServlet("/CookieController")
public class CookieController extends PostHttpServlet {

  @Override
  @SneakyThrows
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    String paramName = req.getParameter("paramName");
    String paramValue = req.getParameter("paramValue");
    Cookie cookie = paramName != null
                        ? new Cookie(paramName, paramValue)
                        : new Cookie("qwerty", "Lorem_ipsum_dolor_sit_amet");

    resp.addCookie(cookie);

    req.setAttribute("cookieConsumer", CheckedConsumer.<JspWriter>of(out -> {
      val cookies = new ArrayList<>(Arrays.asList(req.getCookies()));
      cookies.add((Cookie) req.getAttribute("cookie"));
      for (Cookie cook : cookies)
        if (cook != null)
          out.println("<strong>%s</strong> : %s<p/>".formatted(cook.getName(), cook.getValue()));
    }).unchecked());
    req.getRequestDispatcher("/cookie.jsp").forward(req, resp);

    //    ((Consumer<PrintWriter>) request.getAttribute("cookieConsumer"))
    //        .accept(out);
  }
}
