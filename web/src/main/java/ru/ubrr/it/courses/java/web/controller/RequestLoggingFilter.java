package ru.ubrr.it.courses.java.web.controller;

import java.util.Arrays;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebFilter("/Controller")
public class RequestLoggingFilter extends HttpFilter {

  ServletContext context;

  @Override
  public void init(FilterConfig filterConfig) {
    context = filterConfig.getServletContext();
    context.log("RequestLoggingFilter initialized");
  }

  @SneakyThrows
  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) {

    for (String name : (Iterable<String>) req.getParameterNames()::asIterator)
      context.log("%s::Request Params::{%s=%s}".formatted(
          req.getRemoteAddr(),
          name,
          req.getParameter(name)));

    Optional.ofNullable(req.getCookies())
        .stream()
        .flatMap(Arrays::stream)
        .forEach(cookie -> System.out.printf("%s::Cookie::{%s,%s}%n",
            req.getRemoteAddr(),
            cookie.getName(),
            cookie.getValue()));

    chain.doFilter(req, resp);

    System.out.println("context = " + context);
  }
}
