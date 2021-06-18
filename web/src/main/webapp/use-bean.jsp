<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Use Bean</title>
</head>
<body>

<jsp:useBean id="mybean" type="ru.ubrr.it.courses.java.web.model.SimplePerson" scope="request"/>

<jsp:getProperty property="name" name="mybean"/>
<jsp:getProperty property="surname" name="mybean"/>
<jsp:getProperty property="date" name="mybean"/>

        ${request.mybean.name}

</body>
</html>
