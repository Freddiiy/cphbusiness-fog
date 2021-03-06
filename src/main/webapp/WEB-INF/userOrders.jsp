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
                            <h2 class="ps-4">Mine ordre</h2>

                            <table class="table table-striped table-borderless flex-column table-hover text-center">
                                <thead>
                                <th class="col">Ref. Nr.</th>
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
                                        <td>${item.carport.width}</td>
                                        <td>${item.carport.length}</td>
                                        <td>${item.carport.roof}</td>
                                        <td>${item.carport.hasShedString()}</td>
                                        <td>${item.timestamp}</td>
                                        <td>${item.status}</td>
                                        <td class="p-0 m-0"><a type="button"
                                                               href="${pageContext.request.contextPath}/orders/orderId?orderId=${item.id}"
                                                               class="btn btn-fog-primary">Se ordre</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <div>
                            <c:if test="${param.success==1}">
                                <div class="alert alert-success" role="alert">
                                    Din ordrer er modtaget.
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="container" style="margin-top: 80px">
                        <h1>Dine ordrer kunne ikke findes.</h1>
                    </div>
                </c:otherwise>
            </c:choose>
        </jsp:body>
    </t:navbar>
    <t:footer/>
</t:head>
