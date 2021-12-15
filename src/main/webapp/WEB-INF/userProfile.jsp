<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head>
    <t:navbar>
        <jsp:body>
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <div class="container" style="margin-top: 20px">
                        <div class="bg-light rounded-3 px-0 pt-2 p-md-2 mb-0">
                            <h2 class="ps-4">Min profil</h2>
                            <div class="p-5">
                                <form action="${pageContext.request.contextPath}/profile/update" method="post" id="profileUpdate" class="needs-validation">
                                    <div class="form-floating mb-3">
                                        <input name="fname" type="text" class="form-control" id="floatingFname" value="${sessionScope.user.fname}" required>
                                        <label for="floatingFname">Fornavn</label>
                                        <div class="invalid-feedback">Venligst angiv et fornavn.</div>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input name="lname" type="text" class="form-control" id="floatingLname" value="${sessionScope.user.lname}" required>
                                        <label for="floatingLname">Efternavn</label>
                                        <div class="invalid-feedback">Venligst angiv et efternavn.</div>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input name="phone" type="text" class="form-control" id="floatingPhone" value="${sessionScope.user.phone}" required>
                                        <label for="floatingPhone">Telefonnumer</label>
                                        <div class="invalid-feedback">Venligst angiv et telefonnummer.</div>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input name="address" type="text" class="form-control" id="floatingAddress" value="${sessionScope.user.address}" required>
                                        <label for="floatingAddress">Adresse</label>
                                        <div class="invalid-feedback">Venligst angiv en adresse.</div>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input name="zipcode" type="text" class="form-control" id="floatingZipcode" value="${sessionScope.user.zipcode}" required>
                                        <label for="floatingZipcode">Postnummer</label>
                                        <div class="invalid-feedback">Venligst angiv et postnummer.</div>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input name="city" type="text" class="form-control" id="floatingCity" value="${sessionScope.user.city}" required>
                                        <label for="floatingCity">By</label>
                                        <div class="invalid-feedback">Venligst angiv en by.</div>
                                    </div>

                                    <input type="hidden" name="userId" value="${sessionScope.user.id}">
                                    <button class="w-100 btn btn-lg btn-fog-primary" type="submit" id="sendButton">Opdatér oplysninger</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="container" style="margin-top: 80px">
                        <h1>Denne bruger findes ikke.</h1>
                    </div>
                </c:otherwise>
            </c:choose>
        </jsp:body>
    </t:navbar>
    <t:footer/>
</t:head>
