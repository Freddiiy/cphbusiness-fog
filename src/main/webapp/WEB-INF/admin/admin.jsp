<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head>
    <t:navbar>
        <jsp:body>
            <c:choose>
                <c:when test="${requestScope.orderList != null}">
                    <div class="container" style="margin-top: 20px">
                        <div class="bg-light rounded-3 px-0 pt-2 p-md-2 mb-0">
                            <h2 class="ps-4">Vores ordrer</h2>
                            <div class="row">
                                <a class="col-2 btn btn-fog-primary m-3" href="${pageContext.request.contextPath}/admin/users">Kundeoversigt</a>
                                <a class="col-2 btn btn-fog-primary m-3" href="${pageContext.request.contextPath}/admin">Ordreoversigt</a>
                            </div>

                            <table class="table table-striped table-borderless flex-column table-hover text-center">
                                <thead>
                                    <th class="col">Ref. Nr.</th>
                                    <th class="col">Navn</th>
                                    <th class="col">Carport bredde.</th>
                                    <th class="col">Carport længde</th>
                                    <th class="col">Tag</th>
                                    <th class="col">Redskabsrum</th>
                                    <th class="col">Bestilt</th>
                                    <th class="col">Status</th>
                                </thead>

                            <c:forEach var="item" items="${requestScope.orderList}">
                                <tr>
                                    <td>${item.id}</td>
                                    <td class="text-truncate">${item.user.fname} ${item.user.lname}</td>
                                    <td>${item.carport.width}</td>
                                    <td>${item.carport.length}</td>
                                    <td>${item.carport.idRoof}</td>
                                    <td>${item.carport.hasShedString()}</td>
                                    <td>${item.timestamp}</td>
                                    <td>${item.status}</td>
                                    <td class="p-0 m-0"><a type="button" href="${pageContext.request.contextPath}/admin/order?orderId=${item.id}" class="btn btn-fog-primary">Se ordre</a></td>
                                </tr>
                            </c:forEach>
                            </table>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="container" style="margin-top: 80px">
                        <h1>Der kunne ikke findes nogle kunder.</h1>
                    </div>
                </c:otherwise>
            </c:choose>
        </jsp:body>
    </t:navbar>
    <t:footer/>
</t:head>
