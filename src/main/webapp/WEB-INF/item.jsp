<%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 15/11/2021
  Time: 22.30
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!--
    <br><br><br><br><br>
    <p>Name: <c:out value="${requestScope.cupcakeData.getName()}"/></p>
    <p>Desc: <c:out value="${requestScope.cupcakeData.getDesc()}"/></p>
    <p>Image: <c:out value="${requestScope.cupcakeData.getImageURL()}"/></p>
    <p>Bottom price: <c:out value="${requestScope.cupcakeData.getBottomPrice()}"/> kr.</p>
    <p>Topping price: <c:out value="${requestScope.cupcakeData.getToppingPrice()}"/> kr.</p>
-->
<t:head>
    <t:navbar>
        <jsp:body>
            <!-- Product section-->
            <section class="py-5">
                <div class="container px-4 px-lg-5 my-5 bg-white p-5">
                    <div class="row gx-4 gx-lg-5 align-items-center">
                        <div class="col-md-6"><img class="card-img-top mb-8 mb-md-0" src="/resources/img/cupcakesShop/${requestScope.cupcakeData.getImageURL()}" alt="Cupcake Image" style="height: 600px!important;"></div>
                        <div class="col-md-6">
                            <h1 class="display-5 fw-bolder">${requestScope.cupcakeData.getName()}</h1>
                            <div class="fs-5 mb-5">
                                <span>${requestScope.cupcakeData.getBottomPrice() + requestScope.cupcakeData.getToppingPrice()} kr.</span>
                            </div>
                            <p class="lead">${requestScope.cupcakeData.getDesc()}</p>
                            <div class="d-flex">
                                <form action="${pageContext.request.contextPath}/addToCart" method="POST">
                                    <div>
                                        <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1" style="max-width: 3rem; float: left" name="amount">
                                        <input class="btn btn-outline-dark flex-shrink-0" type="submit" value="Tilføj til kurven">
                                    </div>
                                    <input name="topping" type="hidden" value="${requestScope.cupcakeData.getTopping()}">
                                    <input name="bottom" type="hidden" value="${requestScope.cupcakeData.getBottom()}">
                                    <input name="sessionID" type="hidden" value="${requestScope.sessionID}">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </jsp:body>
    </t:navbar>
</t:head>