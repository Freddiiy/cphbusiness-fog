<%--
  Created by IntelliJ IDEA.
  User: Frederik
  Date: 10/11/2021
  Time: 13.47
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:head>
    <jsp:body>
        <div class="d-flex align-items-center min-vh-100 bg-light nopadding">
            <div class="container-fluid nopadding">
                <div class="row nopadding">
                    <div class="col-0 col-md-4 col-lg-6 col-xl-8 d-none d-md-block nopadding login-img">
                    </div>
                    <div class="col-12 col-md-8 col-lg-6 col-xl-4 align-items-center my-auto">
                        <div class="p-5">

                            <form action="${pageContext.request.contextPath}/login" method="post" id="login-form" class="needs-validation text-center">
                                <a href="${pageContext.request.contextPath}/">
                                    <img class="mb-4 center rounded-3" src="${pageContext.request.contextPath}/resources/img/favicon.png" alt width="92" height="92">
                                </a>
                                <c:if test="${param.error==1}">
                                    <div class="alert alert-danger" role="alert">
                                        Din email eller adgangskode er forkert.
                                    </div>
                                </c:if>
                                <h4 class="pb-3">Log ind på Olsker Cupcakes.</h4>
                                <div class="form-floating mb-3">
                                    <input name="email" type="email" class="form-control" id="floatingInput" placeholder="name@example.com" required>
                                    <label for="floatingInput">Email adresse</label>
                                    <div class="invalid-feedback">Venligst angiv en gyldig email.</div>
                                </div>
                                <div class="form-floating mb-3">
                                    <input name="password" type="password" class="form-control" id="floatingPassword" placeholder="Password" required>
                                    <label for="floatingPassword">Indtast kodeord</label>
                                    <div class="invalid-feedback">Venligst angiv et gyldigt kodeord</div>
                                </div>
                                <hr class="my-4">
                                <button class="w-100 btn btn-lg btn-cupcakes-secondary" type="submit" id="sendButton">Log ind</button>
                                <div class="py-2">
                                    <small class="text-muted"><a class="text-cupcakes text-decoration-none" href="${pageContext.request.contextPath}/register">Registrer</a> | <a class="text-cupcakes text-decoration-none" href="${pageContext.request.contextPath}/forgotPassword">Glemt kodeord</a></small>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script defer src="${pageContext.request.contextPath}/js/loginValidation.js"></script>
    </jsp:body>
</t:head>
