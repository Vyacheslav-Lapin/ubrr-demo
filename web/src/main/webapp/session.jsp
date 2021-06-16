<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Session demo</title>
</head>
<body>

<div>
    <%
        out.println("%s<br/>".formatted(session.getId()));
        for (String name : (Iterable<String>) session.getAttributeNames()::asIterator)
            out.println("%s - %s<br/>".formatted(name, session.getAttribute(name)));
    %>
</div><p/>

<form action="${pageContext.request.contextPath}/SessionController" method="post">
    <input name="paramName" placeholder="paramName" title="Parameter Name"/><br/>
    <input name="paramValue" placeholder="paramValue" title="Parameter Value"/><br/>
    <input type="submit" value="send next HttpRequest"/>
</form>

</body>
</html>

