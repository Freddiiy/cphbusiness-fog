<%--
  Created by IntelliJ IDEA.
  User: Frederik
  Date: 08/11/2021
  Time: 13.40
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark position-fixed" style="width: 280px;">
    <a href="/" class="d-flex align-content-center text-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
        <img draggable="false" class="user-select-none rounded-3" style="height: 2em; width: auto;"
             src="${pageContext.request.contextPath}/resources/img/favicon.png">
        <span class="fs-4 ps-2">Olsker</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="/" class="nav-link text-white" aria-current="page">
                Hjem
            </a>
        </li>
        <li>
            <a href="/profile" class="nav-link text-white">
                Min profil
            </a>
        </li>
        <li>
            <a href="/orders" class="nav-link text-white">
                Mine ordrer
            </a>
        </li>
        <li>
            <a href="/shop" class="nav-link text-white">
                Shop
            </a>
        </li>
    </ul>
    <hr>
    <div class="dropup">
        <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false"> ${sessionScope.user.email}</a>
        <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile">Min
                profil</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/orders">Mine
                ordrer</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Log ud</a>
            </li>
        </ul>
    </div>
</div>

<div id="body">
    <jsp:doBody/>
</div>
