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

<t:head>
    <t:navbar>
        <jsp:body>
            <!-- Product section-->
            <section class="pb-5">
                <div class="container px-4 px-lg-5 mb-5 p-5">
                    <div class="row gx-4 gx-lg-5">
                        <div class="col-12 col-md-12 col-lg-3 text-fog text-center mb-3">
                            <ul class="nav nav-pills flex-column justify-content-center">
                                <li>
                                    <a class="text-decoration-none text-center text-fog h5" href="${pageContext.request.contextPath}/carport/flat-roof">Carport med fladt tag</a>
                                    <hr class="w-100">
                                </li>
                                <li>
                                    <a class="text-decoration-none text-center text-fog h5" href="${pageContext.request.contextPath}/carport/raised-roof">Carport med rejsning</a>
                                    <hr class="w-100">
                                </li>
                                <li>
                                    <a class="text-decoration-none text-center text-fog h5" href="${pageContext.request.contextPath}/carport">Standard carport</a>
                                    <hr class="w-100">
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-9">
                            <form action="${pageContext.request.contextPath}/orders/add" method="POST">
                                <h2 class="fw-bolder">Byg carport med fladt tag</h2>
                                <hr>
                                <h5 class="fw-light">Carport bredde</h5>
                                <div class="col-12 col-md-7 mb-3">
                                    <select class="form-select flex-column" name="carportWidth" required>
                                        <option value="" selected="true" disabled="disabled">Vælg bredde</option>
                                        <option value="240">240 cm</option>
                                        <option value="270">270 cm</option>
                                        <option value="300">300 cm</option>
                                        <option value="330">330 cm</option>
                                        <option value="360">360 cm</option>
                                        <option value="390">390 cm</option>
                                        <option value="420">420 cm</option>
                                        <option value="450">450 cm</option>
                                        <option value="480">480 cm</option>
                                        <option value="510">510 cm</option>
                                        <option value="540">540 cm</option>
                                        <option value="570">570 cm</option>
                                        <option value="600">600 cm</option>
                                    </select>
                                </div>

                                <h5 class="fw-light">Carport længde</h5>
                                <div class="col-12 col-md-7 mb-3">
                                    <select class="form-select flex-column" name="carportLength" required>
                                        <option value="" selected="true" disabled="disabled">Vælg længde</option>
                                        <option value="240">240 cm</option>
                                        <option value="270">270 cm</option>
                                        <option value="300">300 cm</option>
                                        <option value="330">330 cm</option>
                                        <option value="360">360 cm</option>
                                        <option value="390">390 cm</option>
                                        <option value="420">420 cm</option>
                                        <option value="450">450 cm</option>
                                        <option value="480">480 cm</option>
                                        <option value="510">510 cm</option>
                                        <option value="540">540 cm</option>
                                        <option value="570">570 cm</option>
                                        <option value="600">600 cm</option>
                                        <option value="630">630 cm</option>
                                        <option value="660">660 cm</option>
                                        <option value="690">690 cm</option>
                                        <option value="720">720 cm</option>
                                        <option value="750">750 cm</option>
                                        <option value="780">780 cm</option>
                                    </select>
                                </div>

                                <h5 class="fw-light">Tag</h5>
                                <div class="col-12 col-md-7 mb-3">
                                    <select class="form-select flex-column" name="roof" required>
                                        <option selected="selected" value="28">Plastmo Ecolite Tagplade</option>
                                    </select>
                                </div>

                                <div class="col-12 col-md-7 mb-5">
                                    <span>
                                        <br>
                                        <b>Redskabsrum:</b>
                                        <br>NB! Der skal beregnes 15 cm tagudhæng på hver side af redskabsrummet*
                                    </span>
                                </div>

                                <h5 class="fw-light">Redskabsrum bredde</h5>
                                <div class="col-12 col-md-7 mb-3">
                                    <select class="form-select flex-column" name="shedWidth" required>
                                        <option value="0" selected="true">Ønsker ikke redskabsrum</option>
                                        <option value="210">210 cm</option>
                                        <option value="240">240 cm</option>
                                        <option value="270">270 cm</option>
                                        <option value="300">300 cm</option>
                                        <option value="330">330 cm</option>
                                        <option value="360">360 cm</option>
                                        <option value="390">390 cm</option>
                                        <option value="420">420 cm</option>
                                        <option value="450">450 cm</option>
                                        <option value="480">480 cm</option>
                                        <option value="510">510 cm</option>
                                        <option value="540">540 cm</option>
                                        <option value="570">570 cm</option>
                                        <option value="600">600 cm</option>
                                        <option value="630">630 cm</option>
                                        <option value="660">660 cm</option>
                                        <option value="690">690 cm</option>
                                        <option value="720">720 cm</option>
                                    </select>
                                </div>

                                <h5 class="fw-light">Redskabsrum længde</h5>
                                <div class="col-12 col-md-7 mb-3">
                                    <select class="form-select flex-column" name="shedLength" id="choose-menu" required>
                                        <option value="0" selected="true">Ønsker ikke redskabsrum</option>
                                        <option value="150">150 cm</option>
                                        <option value="180">180 cm</option>
                                        <option value="210">210 cm</option>
                                        <option value="240">240 cm</option>
                                        <option value="270">270 cm</option>
                                        <option value="300">300 cm</option>
                                        <option value="330">330 cm</option>
                                        <option value="360">360 cm</option>
                                        <option value="390">390 cm</option>
                                        <option value="420">420 cm</option>
                                        <option value="450">450 cm</option>
                                        <option value="480">480 cm</option>
                                        <option value="510">510 cm</option>
                                        <option value="540">540 cm</option>
                                        <option value="570">570 cm</option>
                                        <option value="600">600 cm</option>
                                        <option value="630">630 cm</option>
                                        <option value="660">660 cm</option>
                                        <option value="690">690 cm</option>
                                    </select>
                                </div>
                                <c:choose>
                                    <c:when test="${sessionScope.user != null}">
                                        <div class="d-flex">
                                            <div>
                                                <input class="btn btn-outline-dark flex-shrink-0" type="submit"
                                                       value="Send en forespørgsel">
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="${pageContext.request.contextPath}/login" method="POST">
                                            <div class="d-flex">
                                                <div>
                                                    <input class="btn btn-outline-dark flex-shrink-0" type="submit" disabled="disabled"
                                                           value="Log ind for at sende en forespørgsel">
                                                </div>
                                            </div>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </jsp:body>
    </t:navbar>
</t:head>
<t:footer />