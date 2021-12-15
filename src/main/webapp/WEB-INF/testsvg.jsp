<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head>
    <t:navbar>
        <jsp:body>

            ${requestScope.svg1}
            ${requestScope.svg2}
            ${requestScope.svg3}
            ${requestScope.svg4}
            ${requestScope.svg5}

        </jsp:body>
    </t:navbar>
</t:head>