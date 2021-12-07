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
                            <form action="${pageContext.request.contextPath}/admin/order/update" method="POST">
                                <div class="col-6">
                                    <table class="table table-striped table-bordered flex-column table-hover">
                                        <thead class="table-active">
                                        <th>Carport</th>
                                        <th>
                                            <div>
                                                <input type="checkbox" id="carportLock" name="carportLock" checked>
                                                <label for="carportLock">Lås carport</label>
                                            </div>
                                        </th>
                                        </thead>

                                        <tr>
                                            <td>Carport bredde:</td>
                                            <td>
                                                <select class="form-select flex-column" id="carportWidth" name="carportWidth" required>
                                                    <option value="${requestScope.order.carport.width}" selected="selected">${requestScope.order.carport.width} cm</option>
                                                    <c:forEach var="item" items="${requestScope.measurements.widthList}">
                                                        <option value="${item}">${item} cm</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>Carport længde:</td>
                                            <td>
                                                <select class="form-select flex-column" id="carportLength" name="carportLength" required>
                                                    <option value="${requestScope.order.carport.length}" selected="selected">${requestScope.order.carport.length} cm</option>
                                                    <c:forEach var="item" items="${requestScope.measurements.lengthList}">
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

                                                </tr>
                                                <tr>
                                                    <td>Redskabsrum bredde:</td>
                                                    <td>
                                                        <select class="form-select flex-column" id="carportShedWidth" name="carportShedWidth" required>
                                                            <option value="${requestScope.order.carport.shedWidth}" selected="selected">${requestScope.order.carport.shedWidth} cm</option>
                                                            <c:forEach var="item" items="${requestScope.measurements.shedWidthList}">
                                                                <option value="${item}">${item} cm</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Redskabsrum længde:</td>
                                                    <td>
                                                        <select class="form-select flex-column" id="carportShedLength" name="carportShedLength" required>
                                                            <option value="${requestScope.order.carport.shedLength}" selected="selected">${requestScope.order.carport.shedLength} cm</option>
                                                            <c:forEach var="item" items="${requestScope.measurements.shedLengthList}">
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
                                    <div>
                                        <input type="hidden" name="orderId" value="${requestScope.order.id}">
                                        <input type="submit" class="btn btn-fog-primary btn-sm" value="Opdatér værdier">
                                    </div>
                                </div>
                            </form>
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

