<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<%--<%--%>
<%--    val param1 = (Date) request.getAttribute("param1");--%>
<%--%>--%>
<jsp:useBean id="param1" scope="request" type="java.util.Date"/>
<jsp:useBean id="param2" scope="request" type="java.lang.String"/>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Hello, World!</title>
    <link href="${pageContext.request.contextPath}/favicon.ico" rel="icon" type="image/x-icon"/>
</head>

<body>

<h1>Hello, World! from main.jsp</h1>

<p>Date is: <%=param1%></p>
<p>Param 2 value is: <%=param2%></p>

</body>

</html>
