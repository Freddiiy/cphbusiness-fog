<%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 12/11/2021
  Time: 12.48
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:head>
    <t:navbar>
        <jsp:body>
            <section class="py-5">
                <div class="container-md px-5 px-lg-5 mt-5">
                    <div class="row gx-4 gx-lg-5 row-cols-1 row-cols-md-2 row-cols-xl-4 justify-content-center">
                        <c:forEach var="item" items="${requestScope.cupcakeData}">
                            <div class="col mb-5">
                                <div class="card w-100">
                                    <img class="card-img-top" src="resources/img/cupcakesShop/${item.getImageURL()}" alt="Cupcake Image" />
                                    <div class="card-body p-4">
                                        <div class="text-center">
                                            <h5 class="fw-bolder">${item.getName()}</h5>
                                            ${item.getBottomPrice() + item.getToppingPrice()} kr.
                                        </div>
                                    </div>
                                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                        <c:choose>
                                            <c:when test="${item.getId() == 1}">
                                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="shop/custom">Lav din her</a></div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="shop/item?id=${item.getId()}">Læs mere</a></div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
        </jsp:body>
    </t:navbar>
</t:head>