<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head>
    <t:navbar>
        <jsp:body>
            <c:choose>
                <c:when test="${sessionScope.cartList != null}">
                    <div class="container" style="margin-top: 80px">
                        <div class="bg-light rounded-3 pt-3 p-md-5 mb-2">
                            <h1 class="ps-4">Indkøbsvogn</h1>

                            <c:forEach var="item" items="${sessionScope.cartList}">
                                <div class="px-5 py-5 mb-5 bg-light border rounded shadow mx-3">
                                    <form method="post" action="${pageContext.request.contextPath}/removeFromCart">
                                        <div class="row text-start">
                                            <h4 class="col-6">Bund: ${item.getCartItems().getBottom()}</h4>
                                            <h4 class="col-6">${item.getCartItems().getBottomPrice()} kr.</h4>
                                            <hr class="mb-5">

                                            <h4 class="col-6">Topping: ${item.getCartItems().getTopping()}</>
                                            <h4 class="col-6">${item.getCartItems().getToppingPrice()} kr.</h4> <br>
                                            <hr class="mb-5">

                                            <h4 class="col-6">Antal: ${item.getCartItems().getAmount()}</h4>
                                            <h4 class="col-6">i alt: ${item.getCartItems().getTotalPrice()} kr.</h4>
                                            <hr class="mb-3">
                                        </div>
                                        <input type="hidden" name="cartId" value="${item.getId()}">
                                        <input class="col-12 col-md-4 btn btn-cupcakes-secondary float-end"
                                               type="submit"
                                               value="Fjern fra kurv">
                                    </form>
                                </div>
                            </c:forEach>
                            <div class="text-end me-3">
                                <h1>${sessionScope.totalPrice} kr.</h1>
                            </div>
                            <c:if test="${sessionScope.cartList != null}">
                                <form class="" method="post" action="${pageContext.request.contextPath}/addOrder">
                                    <c:if test="${param.error==1}">
                                        <div class="row">
                                            <div class="col-9">
                                            </div>
                                            <div class="col-3 alert alert-danger text-center" role="alert">
                                                Du har ikke nok penge.
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="bg-light p-5">
                                        <input class="col-12 col-md-4 btn btn-cupcakes-secondary float-end" type="submit" value="Køb">
                                    </div>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="container" style="margin-top: 80px">
                        <h1>Din indkøbskurv er tom.</h1>
                    </div>
                </c:otherwise>
            </c:choose>
        </jsp:body>
    </t:navbar>
    <t:footer/>
</t:head>
