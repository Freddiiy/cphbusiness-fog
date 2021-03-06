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
                                    <tr>
                                        <td>Telefon:</td>
                                        <td>${requestScope.order.user.phone}</td>
                                    </tr>
                                    <tr>
                                        <td>Adresse:</td>
                                        <td>${requestScope.order.user.address}</td>
                                    </tr>
                                    <tr>
                                        <td>Post nr.:</td>
                                        <td>${requestScope.order.user.zipcode}</td>
                                    </tr>
                                    <tr>
                                        <td>By:</td>
                                        <td>${requestScope.order.user.city}</td>
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
                                        <td>Carport l??ngde:</td>
                                        <td>${requestScope.order.carport.length} cm</td>
                                    </tr>
                                    <tr>
                                        <td>Carport tag:</td>
                                        <td>${requestScope.order.carport.roof}</td>
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
                                                <td>Redskabsrum l??ngde:</td>
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
                                <div class="row row-cols-auto p-2 pb-4 pe-0">
                                    <c:if test="${requestScope.order.status == 'ACCEPTED'}">
                                        <div class="col">
                                            <form action="${pageContext.request.contextPath}/orders/pay"
                                                  method="post">
                                                <input type="hidden" name="orderId" value="${requestScope.order.id}">
                                                <input type="submit" id="payButton" class="btn btn-success btn-sm"
                                                       value="Betal">
                                            </form>
                                        </div>
                                    </c:if>

                                </div>
                                <div class="row row-cols-auto p-2 pb-4 pe-0">
                                    <c:if test="${requestScope.order.status == 'RECIEVED'}">
                                        <div class="col">
                                            <form action="${pageContext.request.contextPath}/orders/remove"
                                                  method="post">
                                                <input type="hidden" name="orderId" value="${requestScope.order.id}">
                                                <input type="submit" id="acceptButton" class="btn btn-danger btn-sm"
                                                       value="Annull??r ordre">
                                            </form>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <c:if test="${requestScope.order.status == 'PAID'}">
                                <div class="row row-cols-auto p-2 pb-4 pe-0">
                                    <div class="col">
                                        <a class="btn btn-fog-primary btn-sm" data-bs-toggle="collapse"
                                           href="#collapseSVG" role="button" aria-expanded="false"
                                           aria-controls="collapseSVG">
                                            Se tegning
                                        </a>
                                    </div>
                                </div>
                                <div class="collapse" id="collapseSVG">
                                    <div class="text-start py-2">
                                            ${requestScope.svg}
                                    </div>
                                </div>
                                <div class="col-12">
                                    <h4>Stykliste</h4>
                                    <table class="table table-striped table-bordered flex-column table-hover">
                                        <thead class="table-active">
                                        <th class="col">Id</th>
                                        <th class="col">Navn</th>
                                        <th class="col">Antal</th>
                                        <th class="col">Pris</th>
                                        </thead>
                                        <c:forEach var="item" items="${requestScope.billOfMaterials}">
                                            <tr>
                                                <td>${item.key.id}</td>
                                                <td>${item.key.name}</td>
                                                <td>${item.value}</td>
                                                <td>${item.key.price}</td>
                                            </tr>
                                        </c:forEach>
                                        <c:forEach var="item" items="${requestScope.woodBillOfMaterials}">
                                            <tr>
                                                <td>${item.key.id}</td>
                                                <td>${item.key.name}</td>
                                                <td>${item.key.length} cm</td>
                                                <td>${item.value}</td>
                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td>Total</td>
                                            <td>${requestScope.totalPrice}</td>
                                        </tr>
                                    </table>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="container" style="margin-top: 80px">
                        <h1>Kunne ikke finde en ordre.</h1>
                    </div>
                </c:otherwise>
            </c:choose>
        </jsp:body>
    </t:navbar>
    <t:footer/>
</t:head>

