<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag" %>
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

<jsp:useBean id="userbean" type="ru.ubrr.it.courses.java.web.model.JSPSetBean" scope="request"/>

<mytag:jspset set="${userbean}"/><br/>

<mytag:bodyjspset num="${userbean.size}">
    ${userbean.element}
</mytag:bodyjspset>

</body>
</html>
