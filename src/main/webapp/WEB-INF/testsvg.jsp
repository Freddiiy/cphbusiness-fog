<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head>
    <t:navbar>
        <jsp:body>

                        ${requestScope.svg}
                        ${requestScope.svg1}

        </jsp:body>
    </t:navbar>
</t:head>