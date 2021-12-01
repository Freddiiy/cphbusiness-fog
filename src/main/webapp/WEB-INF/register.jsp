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

<t:head>
  <jsp:body>
    <div class="d-flex align-items-center min-vh-100 bg-light nopadding">
      <div class="container-fluid nopadding">
        <div class="row nopadding">
          <div class="col-0 col-md-4 col-lg-6 col-xl-8 d-none d-md-block nopadding register-img">
          </div>
          <div class="col-12 col-md-8 col-lg-6 col-xl-4 align-items-center my-auto">
            <div class="p-5">

              <form action="${pageContext.request.contextPath}/register" method="post" id="login-form" class="needs-validation text-center" oninput='password2.setCustomValidity(password2.value !== password1.value ? "was-validated" : "")'>
                <a href="${pageContext.request.contextPath}/">
                  <img class="mb-4 center rounded-3" src="${pageContext.request.contextPath}/resources/img/favicon.png" alt width="92" height="92">
                </a>
                <c:if test="${param.error==1}">
                  <div class="alert alert-danger" role="alert">
                    Konto findes allerede!
                  </div>
                </c:if>
                <h4 class="pb-3">Registrer dig på Fog.</h4>
                <div class="form-floating mb-3">
                  <input name="email" type="email" class="form-control" id="floatingInput" placeholder="name@example.com" required>
                  <label for="floatingInput">Email adresse</label>
                  <div class="invalid-feedback">Venligst angiv en gyldig email.</div>
                </div>
                <div class="row">
                  <div class="form-floating mb-3 col-12 col-sm-6">
                    <input name="fname" type="text" class="form-control" id="floatingFname" placeholder="fornavn" required>
                    <label for="floatingFname" class="ms-2">Fornavn</label>
                    <div class="invalid-feedback">Angiv venligst et fornavn.</div>
                  </div>
                  <div class="form-floating mb-3 col-12 col-sm-6">
                    <input name="lname" type="text" class="form-control" id="floatingLname" placeholder="efternavn" required>
                    <label for="floatingLname" class="ms-2">Efternavn</label>
                    <div class="invalid-feedback">Angiv venligst et efternavn.</div>
                  </div>
                </div>
                <div class="form-floating mb-3">
                  <input name="password1" type="password" class="form-control" id="floatingPassword" placeholder="Password"required>
                  <label for="floatingPassword">Kodeord</label>
                  <div class="invalid-feedback">Venligst angiv et gyldigt kodeord.</div>
                </div>
                <div class="form-floating mb-3">
                  <input name="password2" type="password" class="form-control" id="floatingPasswordRepeat" placeholder="Password"required>
                  <label for="floatingPasswordRepeat">Kodeord</label>
                  <div class="invalid-feedback">Kodeorderne matcher ikke.</div>
                </div>
                <hr class="my-4">
                <button class="w-100 btn btn-lg btn-fog-primary" type="submit" id="sendButton">Tilmeld</button>
                <div class="py-2">
                  <small class="text-muted">Har du allerede en bruger? <a class="text-fog text-decoration-none" href="${pageContext.request.contextPath}/login">Log ind</a></small>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script defer src="${pageContext.request.contextPath}/js/registerValidation.js"></script>
  </jsp:body>
</t:head>
