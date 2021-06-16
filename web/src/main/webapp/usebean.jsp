<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Title</title>
</head>
<body>
<jsp:useBean id="naming" class="ru.ubrr.it.courses.java.web.controller.SimplePerson">
    <jsp:setProperty property="*" name="naming"/>
    <jsp:getProperty property="name" name="naming"/>
    <jsp:getProperty property="surname" name="naming"/>
</jsp:useBean>

<%--
<jsp:getProperty property="date" name="naming"/>

<jsp:useBean id="pageDate" class="java.util.Date"/>
<jsp:setProperty name="naming" property="date" value="${pageDate}"/>
<jsp:getProperty property="date" name="naming"/>
        --%>

        ${pageScope.naming.name}

</body>
</html>
