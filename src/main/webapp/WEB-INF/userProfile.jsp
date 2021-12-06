<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head>
    <t:navbar>
        <jsp:body>
                <div class="container" style="margin-top: 80px">
                    <h1>Profile page</h1>
                    <h3>${sessionScope.user.email}</h3>
                    <h3>${sessionScope.user.fname}</h3>
                    <h3>${sessionScope.user.lname}</h3>
                    <h3>${sessionScope.user.role}</h3>
                </div>
        </jsp:body>
    </t:navbar>
</t:head>