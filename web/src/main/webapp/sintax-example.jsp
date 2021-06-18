<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>JSP-страница</title>
</head>

<%!
  private int count = 0;
  String version = "J2EE 1.5";

  private String getName() {
    return "J2EE 1.6 or 1.7";
  }
%>

<body>

  <% out.println("Значение count: "); %>

  <%=count++%><br/>

  <% out.println("Значение count после инкремента: " + count); %><br/>

  Старое значение version:
  <%=version%><br/>

  <%-- b --%>
  <!-- c -->

  <%
    version = getName();
    out.println("Новое значение version: " + version);
  %>

<div title="<%="Lorem ipsum dolor sit amet%>"/>
<sp<%="an"%>/>
</html>
