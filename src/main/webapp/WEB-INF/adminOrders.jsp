<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head>
    <t:navbar>
        <jsp:body>
            <c:choose>
                <c:when test="${requestScope.orderList != null}">
                    <div class="container" style="margin-top: 80px">
                        <div class="bg-light rounded-3 p-0 p-md-5 mb-3">
                            <h1>Ordrer fra ${requestScope.orderList.get(0).getEmail()}</h1>

                            <c:forEach var="item" items="${requestScope.orderList}">
                                <form method="POST" action="${pageContext.request.contextPath}/adminRemoveOrder">

                                    <div class="px-0 py-5 mb-5 bg-light border rounded shadow mx-3">
                                        <div class="row text-start px-5">
                                            <h4 class="col-6">Bruger: ${item.getEmail()}</h4>
                                            <h4 class="col-6">Ordre ID: ${item.getId()}</h4>
                                            <hr class="mb-5">


                                            <h4 class="col-6">Bund: ${item.getOrderItems().getBottom()}</h4>
                                            <h4 class="col-6">${item.getOrderItems().getBottomPrice()} kr.</h4>
                                            <hr class="mb-5">

                                            <h4 class="col-6">Topping: ${item.getOrderItems().getTopping()}</>
                                            <h4 class="col-6">${item.getOrderItems().getToppingPrice()} kr.</h4> <br>
                                            <hr class="mb-5">

                                            <h4 class="col-6">Antal: ${item.getOrderItems().getAmount()}</h4>
                                            <h4 class="col-6">i alt: ${item.getOrderItems().getTotalPrice()} kr.</h4>
                                            <hr class="mb-3">

                                            <input type="hidden" name="orderId" value="${item.getId()}">
                                            <input type="hidden" name="userId" value="${item.getUserId()}">
                                            <div class="row justify-content-end">
                                                <input class="col-12 col-lg-3 btn btn-fog-secondary" type="submit" value="Fjern ordre">
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
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
