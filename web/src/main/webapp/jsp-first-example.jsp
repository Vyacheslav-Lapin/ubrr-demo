<%@ page contentType="text/html;charset=UTF-8" %>
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

<form action="/usebean" method="post">
    <input type="hidden" name="command" value="naming"/>
    <input name="name" placeholder="Your name"/><br/>
    <input name="surname" placeholder="Your surname"/><br/>
    <input type="submit" value="Отправить"/>
</form>

</body>
</html>
