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
                        <div class="col-md-6"><img class="card-img-top mb-8 mb-md-0"
                                                   src="${pageContext.request.contextPath}/resources/img/cupcakesShop/custom.jpg"
                                                   alt="Cupcake Image" style="height: 600px!important;"></div>
                        <div class="col-md-6">
                            <form action="${pageContext.request.contextPath}/addToCart" method="POST">
                                <h2 class="fw-bolder">Byg din egen cupcake</h2>
                                <hr>
                                <h4 class="fw-light">Vælg bund</h4>
                                <div class="col-12 col-md-7 mb-3">
                                    <select class="form-select flex-column" name="bottom" id="choose-menu" required>
                                        <option value="" selected="true" disabled="disabled">Vælg bund</option>
                                        <option value="Chocolate">Chocolate</option>
                                        <option value="Blueberry">Blueberry</option>
                                        <option value="Raspberry">Raspberry</option>
                                        <option value="Crispy">Crispy</option>
                                        <option value="Strawberry">Strawberry</option>
                                        <option value="Rum & raisin">Rum & raisin</option>
                                        <option value="Orange">Orange</option>
                                        <option value="Lemon">Lemon</option>
                                        <option value="Blue cheese">Blue cheese</option>
                                    </select>
                                </div>
                                <h4 class="fw-light">Vælg topping</h4>
                                <div class="col-12 col-md-7 mb-5">
                                    <select class="form-select flex-column" name="topping" id="choose-menu" required>
                                        <option value="" selected="true" disabled="disabled">Vælg topping</option>
                                        <option value="Chocolate">Chocolate</option>
                                        <option value="Vanilla">Vanilla</option>
                                        <option value="Nutmeg">Nutmeg</option>
                                        <option value="Pistachio">Pistachio</option>
                                        <option value="Wallnut">Wallnut</option>
                                    </select>
                                </div>
                                <div class="d-flex">
                                    <div>
                                        <input class="form-control text-center me-3" id="amount" type="number" value="1"
                                               style="max-width: 3rem; float: left" name="amount">
                                        <input class="btn btn-outline-dark flex-shrink-0" type="submit"
                                               value="Tilføj til kurven">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </jsp:body>
    </t:navbar>
</t:head>