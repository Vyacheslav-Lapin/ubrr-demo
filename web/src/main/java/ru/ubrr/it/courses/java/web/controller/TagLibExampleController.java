package ru.ubrr.it.courses.java.web.controller;

import java.util.Set;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.ubrr.it.courses.java.web.model.JSPSetBean;

@WebServlet("/TagLibExampleController")
public class TagLibExampleController extends PostHttpServlet {

  @Override
  @SneakyThrows
  protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) {
    req.setAttribute("userbean", new JSPSetBean(Set.of("one", "two", "three")));
    req.getRequestDispatcher("taglib-example-demo.jsp").forward(req, resp);
  }
}
