<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head>
    <t:navbar>
        <jsp:body>
            <c:choose>
                <c:when test="${requestScope.order != null}">
                    <div class="container" style="margin-top: 20px">
                        <div class="row bg-light rounded-3 p-0 p-md-2 mb-3 text-start">
                            <h1>Ordre ${requestScope.order.id}</h1>
                            <div class="col-6">
                                <table class="table table-striped table-bordered flex-column table-hover">
                                    <thead class="table-active">
                                        <th class="col">Kundeoplysninger</th>
                                        <th class="col"></th>
                                    </thead>
                                    <tr>
                                        <td>Kunde nummer:</td>
                                        <td>${requestScope.order.user.id}</td>
                                    </tr>
                                    <tr>
                                        <td>Navn:</td>
                                        <td>${requestScope.order.user.fname} ${requestScope.order.user.lname}</td>
                                    </tr>
                                    <tr>
                                        <td>Email:</td>
                                        <td>${requestScope.order.user.email}</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-6">
                                <table class="table table-striped table-bordered flex-column table-hover">
                                    <thead class="table-active">
                                        <th>Ordredetaljer</th>
                                        <th></th>
                                    </thead>
                                    <tr>
                                        <td>Ref nr.:</td>
                                        <td>${requestScope.order.id}</td>
                                    </tr>
                                    <tr>
                                        <td>Bestilt:</td>
                                        <td>${requestScope.order.timestamp}</td>
                                    </tr>
                                    <tr>
                                        <td>Status:</td>
                                        <td>${requestScope.order.status}</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-6">
                                <table class="table table-striped table-bordered flex-column table-hover">
                                    <thead class="table-active">
                                    <th>Carport</th>
                                    <th></th>
                                    </thead>
                                    <tr>
                                        <td>Carport bredde:</td>
                                        <td>${requestScope.order.carport.width} cm</td>
                                    </tr>
                                    <tr>
                                        <td>Carport længde:</td>
                                        <td>${requestScope.order.carport.length} cm</td>
                                    </tr>
                                    <tr>
                                        <td>Carport tag:</td>
                                        <td>${requestScope.order.carport.idRoof}</td>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${requestScope.order.carport.hasShed()}">
                                            <tr>
                                                <td>Redskabsrum:</td>
                                                <td>${requestScope.order.carport.hasShedString()}</td>
                                            </tr>
                                            <tr>
                                                <td>Redskabsrum bredde:</td>
                                                <td>${requestScope.order.carport.shedWidth} cm</td>
                                            </tr>
                                            <tr>
                                                <td>Redskabsrum længde:</td>
                                                <td>${requestScope.order.carport.shedLength} cm</td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td>Redskabsrum:</td>
                                                <td>${requestScope.order.carport.hasShedString()}</td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </table>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="container" style="margin-top: 80px">
                        <h1>Kunne ikke finde nogle ordre.</h1>
                    </div>
                </c:otherwise>
            </c:choose>
        </jsp:body>
    </t:navbar>
    <t:footer/>
</t:head>

