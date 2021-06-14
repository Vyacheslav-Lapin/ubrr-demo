<%--
  Created by IntelliJ IDEA.
  User: vyacheslavlapin
  Date: 14.06.2021
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>login-password</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/form-parse" method="get">

  <input type="hidden" name="command" value="forward"/>

  Enter login:<br/>
  <input name="login" title="login"/><br/>

  Enter password:<br/>
  <input type="password" name="password" title="password"/><br/>

  <input type="submit" value="Отправить"/><br/>
</form>


</body>
</html>
