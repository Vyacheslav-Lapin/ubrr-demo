<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>index</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/ControllerForward">
    <input type="submit" value="forward()"/><br/>
</form>

<br/>

<form action="${pageContext.request.contextPath}/ControllerSendRedirect">
    <input type="submit" value="sendRedirect()"/><br/>
</form>
</body>
</html>
