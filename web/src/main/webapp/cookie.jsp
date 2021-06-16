<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.function.Consumer" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%--<jsp:useBean id="cookieConsumer" type="java.util.function.Consumer" scope="request"/>--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cookie</title>
</head>
<body>

<%
    ((Consumer<JspWriter>) request.getAttribute("cookieConsumer"))
            .accept(out);
%>

<form action="${pageContext.request.contextPath}/CookieController" method="post">
    <input name="paramName" placeholder="paramName" title="Parameter Name"/><br/>
    <input name="paramValue" placeholder="paramValue" title="Parameter Value"/><br/>
    <input type="submit" value="send next HttpRequest"/>
</form>

</body>
</html>
