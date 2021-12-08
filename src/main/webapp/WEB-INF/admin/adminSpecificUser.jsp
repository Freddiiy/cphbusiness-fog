<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head>
    <t:navbar>
        <jsp:body>
            <c:choose>
                <c:when test="${requestScope.user != null}">
                    <div class="container" style="margin-top: 20px">
                        <div class="row bg-light rounded-3 p-0 p-md-2 mb-3 text-start">
                            <h1>Kunde nr ${requestScope.user.id}</h1>

                            <div class="col-6">
                                <table class="table table-striped table-bordered flex-column table-hover">
                                    <thead class="table-active">
                                    <th class="col">Kundeoplysninger</th>
                                    <th class="col"></th>
                                    </thead>
                                    <tr>
                                        <td>Kunde nummer:</td>
                                        <td>${requestScope.user.id}</td>
                                    </tr>
                                    <tr>
                                        <td>Navn:</td>
                                        <td>${requestScope.user.fname} ${requestScope.user.lname}</td>
                                    </tr>
                                    <tr>
                                        <td>Email:</td>
                                        <td>${requestScope.user.email}</td>
                                    </tr>
                                    <tr>
                                        <td>Telefon:</td>
                                        <td>${requestScope.user.phone}</td>
                                    </tr>
                                    <tr>
                                        <td>Adresse:</td>
                                        <td>${requestScope.user.address}</td>
                                    </tr>
                                    <tr>
                                        <td>Post nr.:</td>
                                        <td>${requestScope.user.zipcode}</td>
                                    </tr>
                                    <tr>
                                        <td>By:</td>
                                        <td>${requestScope.user.city}</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-6"></div>

                            <h3>Ordrer:</h3>
                            <hr>
                            <c:choose>
                                <c:when test="${requestScope.orderList != null}">
                                    <c:forEach var="item" items="${requestScope.orderList}">
                                        <div class="col-6">
                                            <table class="table table-striped table-bordered flex-column table-hover">
                                                <thead class="table-active">
                                                <th>Ordre: ${item.id}</th>
                                                <th></th>
                                                </thead>
                                                <tr>
                                                    <td>Carport bredde:</td>
                                                    <td>${item.carport.width} cm</td>
                                                </tr>
                                                <tr>
                                                    <td>Carport længde:</td>
                                                    <td>${item.carport.length} cm</td>
                                                </tr>
                                                <tr>
                                                    <td>Carport tag:</td>
                                                    <td>${item.carport.idRoof}</td>
                                                </tr>
                                                <c:choose>
                                                    <c:when test="${item.carport.hasShed()}">
                                                        <tr>
                                                            <td>Redskabsrum:</td>
                                                            <td>${item.carport.hasShedString()}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Redskabsrum bredde:</td>
                                                            <td>${item.carport.shedWidth} cm</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Redskabsrum længde:</td>
                                                            <td>${item.carport.shedLength} cm</td>
                                                        </tr>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <tr>
                                                            <td>Redskabsrum:</td>
                                                            <td>${item.carport.hasShedString()}</td>
                                                        </tr>
                                                    </c:otherwise>
                                                </c:choose>
                                            </table>
                                        </div>
                                        <div class="col-6">
                                            <table class="table table-striped table-bordered flex-column table-hover">
                                                <thead class="table-active">
                                                <th>Ordredetaljer på ${item.id}</th>
                                                <th></th>
                                                </thead>
                                                <tr>
                                                    <td>Ref nr.:</td>
                                                    <td>${item.id}</td>
                                                </tr>
                                                <tr>
                                                    <td>Bestilt:</td>
                                                    <td>${item.timestamp}</td>
                                                </tr>
                                                <tr>
                                                    <td>Status:</td>
                                                    <td>${item.status}</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <h4>Kunden har ingen ordrer</h4>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="container" style="margin-top: 80px">
                        <h1>Kunne ikke finde nogen kunde.</h1>
                    </div>
                </c:otherwise>
            </c:choose>
        </jsp:body>
    </t:navbar>
    <t:footer/>
</t:head>

<script defer src="${pageContext.request.contextPath}/js/lockForms.js"></script>