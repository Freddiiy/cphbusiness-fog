<%--
  Created by IntelliJ IDEA.
  User: Frederik
  Date: 08/11/2021
  Time: 13.40
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/img/favicon.png" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/custom/custom.css" />
    <script defer src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.js"></script>
    <title>Fog</title>
</head>

<body>
<div id="body">
    <jsp:doBody />
</div>
</body>
</html>