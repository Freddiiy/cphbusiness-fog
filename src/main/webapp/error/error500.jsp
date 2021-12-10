<%--
  Created by IntelliJ IDEA.
  User: Frederik
  Date: 10/12/2021
  Time: 12.46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="./favicon.png">
    <title>404</title>
    <style>
        body {
            background-color: #0c2069;
            color: #fff;
            text-align: center;
            font-family: arial, sans-serif;
            margin: 0;
        }
        div {
            position:absolute;
            top: 50%;
            left: 50%;
            transform: translateX(-50%) translateY(-50%);
        }
        p {
            color: #eee;
        }
        a {
            color: #eee;
        }
    </style>
</head>
<body>
<div>
    <h1>Åh nej, fejl 500!</h1>
    <p>Bare rolig, du gjorde intet forkert. Fejlen er på vores side.</p>
    <a href="${pageContext.request.contextPath}/">Tryk her for at komme tilbage til startsiden</a>
</div>

</body>
</html>
