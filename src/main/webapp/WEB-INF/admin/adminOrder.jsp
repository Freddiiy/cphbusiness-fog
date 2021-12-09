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

                            <div class="row row-cols-auto">
                                <a class="col btn btn-fog-primary btn-sm ms-3 mb-2"
                                   href="${pageContext.request.contextPath}/admin">Tilbage</a>
                            </div>

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
                            <form action="${pageContext.request.contextPath}/admin/order/update" method="POST">
                                <div class="col-6">
                                    <table class="table table-striped table-bordered flex-column table-hover">
                                        <thead class="table-active">
                                        <th>Carport</th>
                                        <th>
                                            <div>
                                                <input type="checkbox" id="carportLock" name="carportLock"
                                                       onclick="lockCarport()" checked>
                                                <label for="carportLock">Lås carport</label>
                                            </div>
                                        </th>
                                        </thead>

                                        <tr>
                                            <td>Carport bredde:</td>
                                            <td>
                                                <select class="form-select flex-column" id="carportWidth"
                                                        name="carportWidth" disabled required>
                                                    <option value="${requestScope.order.carport.width}"
                                                            selected="selected">${requestScope.order.carport.width} cm
                                                    </option>
                                                    <c:forEach var="item"
                                                               items="${requestScope.measurements.widthList}">
                                                        <option value="${item}">${item} cm</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>Carport længde:</td>
                                            <td>
                                                <select class="form-select flex-column" id="carportLength"
                                                        name="carportLength" disabled required>
                                                    <option value="${requestScope.order.carport.length}"
                                                            selected="selected">${requestScope.order.carport.length} cm
                                                    </option>
                                                    <c:forEach var="item"
                                                               items="${requestScope.measurements.lengthList}">
                                                        <option value="${item}">${item} cm</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
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
                                                    <td>
                                                        <select class="form-select flex-column" id="carportShedWidth"
                                                                name="carportShedWidth" disabled required>
                                                            <option value="${requestScope.order.carport.shedWidth}"
                                                                    selected="selected">${requestScope.order.carport.shedWidth}
                                                                cm
                                                            </option>
                                                            <c:forEach var="item"
                                                                       items="${requestScope.measurements.shedWidthList}">
                                                                <option value="${item}">${item} cm</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Redskabsrum længde:</td>
                                                    <td>
                                                        <select class="form-select flex-column" id="carportShedLength"
                                                                name="carportShedLength" disabled required>
                                                            <option value="${requestScope.order.carport.shedLength}"
                                                                    selected="selected">${requestScope.order.carport.shedLength}
                                                                cm
                                                            </option>
                                                            <c:forEach var="item"
                                                                       items="${requestScope.measurements.shedLengthList}">
                                                                <option value="${item}">${item} cm</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
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
                                        <div class="col">
                                            <input type="hidden" name="orderId" value="${requestScope.order.id}">
                                            <input type="submit" id="updateButton" class="btn btn-fog-primary btn-sm"
                                                   disabled value="Opdatér værdier">
                                        </div>
                                        <a class="btn btn-fog-primary btn-sm" data-bs-toggle="collapse"
                                           href="#collapseSVG" role="button" aria-expanded="false"
                                           aria-controls="collapseSVG">
                                            Se tegning
                                        </a>
                                    </div>
                                    <div class="collapse" id="collapseSVG">
                                        <div class="text-start py-2">
                                                ${requestScope.svg}
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="row row-cols-auto" role="group" aria-label="Accept and decline">
                                <c:if test="${requestScope.order.status == 'RECIEVED'}">
                                    <div class="col">
                                        <form action="${pageContext.request.contextPath}/admin/order/accept"
                                              method="post">
                                            <input type="hidden" name="orderId" value="${requestScope.order.id}">
                                            <button type="submit" id="acceptButton" class="col btn btn-success">Acceptér
                                                ordre
                                            </button>
                                        </form>
                                    </div>
                                    <div class="col">
                                        <form action="${pageContext.request.contextPath}/admin/order/reject"
                                              method="post">
                                            <input type="hidden" name="orderId" value="${requestScope.order.id}">
                                            <button type="submit" id="removeButton" class="btn btn-danger">Afvis ordre
                                            </button>
                                        </form>
                                    </div>
                                </c:if>
                                <c:if test="${requestScope.order.status == 'ACCEPTED'}">
                                    <div class="col">
                                        <form action="${pageContext.request.contextPath}/admin/order/reject"
                                              method="post">
                                            <input type="hidden" name="orderId" value="${requestScope.order.id}">
                                            <button type="submit" id="removeButton" class="btn btn-danger">Afvis ordre
                                            </button>
                                        </form>
                                    </div>
                                </c:if>
                                <c:if test="${requestScope.order.status == 'REJECTED'}">
                                    <div class="col">
                                        <form action="${pageContext.request.contextPath}/admin/order/accept"
                                              method="post">
                                            <input type="hidden" name="orderId" value="${requestScope.order.id}">
                                            <button type="submit" id="acceptButton" class="btn btn-success">Acceptér
                                                ordre
                                            </button>
                                        </form>
                                    </div>
                                </c:if>
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

<script defer src="${pageContext.request.contextPath}/js/lockForms.js"></script>