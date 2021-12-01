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

<nav class="top navbar navbar-expand-lg navbar-fog navbar-text-white px-3 align-content-lg-center">
    <div class="container">
        <div class="d-block">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                <img draggable="false" class="user-select-none rounded-3" style="height: 2em; width: auto;"
                     src="${pageContext.request.contextPath}/resources/img/favicon.png" alt="">
            </a>
        </div>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                aria-label="navbarNavDropdown">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav justify-content-start">
                <li class="nav-item">
                    <a class="nav-link text-white" href="${pageContext.request.contextPath}/shop">Carporte</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="">Om os</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="">Kontakt</a>
                </li>
            </ul>
        </div>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNavDropdown">
            <c:choose>
                <c:when test="${sessionScope.user.sessionID != null}">
                    <ul class="navbar-nav list-group list-group-horizontal">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">${sessionScope.user.email}</a>
                            <ul class="dropdown-menu dropdown-menu-sm-start dropdown-menu-lg-end opacity-75 rounded-0 rounded-bottom"
                                aria-labelledby="navbarDarkDropdownMenuLink" style="margin-top: 8px;">
                                <c:choose>
                                    <c:when test="${sessionScope.user.role == 'Admin'}">
                                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin">Admin</a></li>
                                    </c:when>
                                </c:choose>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile">Min profil</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/orders">Mine ordrer</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Log ud</a></li>
                            </ul>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link">${sessionScope.user.balance} kr.</a>
                        </li>
                        <li class="nav-item shopping-cart-opacity-50">
                            <a class="nav-link" href="${pageContext.request.contextPath}/cart"><img style="height: 2em; width: auto;" src="${pageContext.request.contextPath}/resources/img/shopping-cart.png" alt="Shopping cart"></a>
                        </li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="navbar-nav list-group list-group-horizontal">
                        <li class="nav-item row-cols-1 row-cols-lg-1 me-2">
                            <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/login"
                               value="Log ind">Log Ind</a>
                        </li>
                        <li class="nav-item row-cols-1 row-cols-lg-1 me-2">
                            <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/register"
                               value="Registrer">Registrer</a>
                        </li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

<div id="body">
    <jsp:doBody/>
</div>
