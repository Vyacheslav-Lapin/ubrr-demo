package ru.ubrr.it.courses.java.web.controller;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import lombok.val;

@WebListener
public class ProjectRequestListener implements ServletRequestListener {

  public void requestInitialized(ServletRequestEvent event) {
    val request = (HttpServletRequest) event.getServletRequest();
    System.out.printf("Request from %s was created.%n", request.getContextPath());
  }

  public void requestDestroyed(ServletRequestEvent event) {
    val request = (HttpServletRequest) event.getServletRequest();
    System.out.printf("Request from %s was destroyed.%n", request.getContextPath());
  }
}
