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
                            <form action="${pageContext.request.contextPath}/addToCart" method="POST">
                                <h2 class="fw-bolder">Byg carport med rejsning</h2>
                                <hr>
                                <h5 class="fw-light">Carport bredde</h5>
                                <div class="col-12 col-md-7 mb-3">
                                    <select class="form-select flex-column" name="carportWidth" required>
                                        <option value="" selected="true" disabled="disabled">Vælg bredde</option>
                                        <option value="240 cm">240 cm</option>
                                        <option value="270 cm">270 cm</option>
                                        <option value="300 cm">300 cm</option>
                                        <option value="330 cm">330 cm</option>
                                        <option value="360 cm">360 cm</option>
                                        <option value="390 cm">390 cm</option>
                                        <option value="420 cm">420 cm</option>
                                        <option value="450 cm">450 cm</option>
                                        <option value="480 cm">480 cm</option>
                                        <option value="510 cm">510 cm</option>
                                        <option value="540 cm">540 cm</option>
                                        <option value="570 cm">570 cm</option>
                                        <option value="600 cm">600 cm</option>
                                    </select>
                                </div>

                                <h5 class="fw-light">Carport længde</h5>
                                <div class="col-12 col-md-7 mb-3">
                                    <select class="form-select flex-column" name="carportLength" required>
                                        <option value="" selected="true" disabled="disabled">Vælg længde</option>
                                        <option value="240 cm">240 cm</option>
                                        <option value="270 cm">270 cm</option>
                                        <option value="300 cm">300 cm</option>
                                        <option value="330 cm">330 cm</option>
                                        <option value="360 cm">360 cm</option>
                                        <option value="390 cm">390 cm</option>
                                        <option value="420 cm">420 cm</option>
                                        <option value="450 cm">450 cm</option>
                                        <option value="480 cm">480 cm</option>
                                        <option value="510 cm">510 cm</option>
                                        <option value="540 cm">540 cm</option>
                                        <option value="570 cm">570 cm</option>
                                        <option value="600 cm">600 cm</option>
                                        <option value="630 cm">630 cm</option>
                                        <option value="660 cm">660 cm</option>
                                        <option value="690 cm">690 cm</option>
                                        <option value="720 cm">720 cm</option>
                                        <option value="750 cm">750 cm</option>
                                        <option value="780 cm">780 cm</option>
                                    </select>
                                </div>

                                <h5 class="fw-light">Tag</h5>
                                <div class="col-12 col-md-7 mb-3">
                                    <select class="form-select flex-column" name="roof" required>
                                        <option value="" selected="true" disabled="disabled">Vælg tag/farve</option>
                                        <option value="Betontagsten - Rød">Betontagsten - Rød</option>
                                        <option value="Betontagsten - Teglrød">Betontagsten - Teglrød</option>
                                        <option value="Betontagsten - Brun">Betontagsten - Brun</option>
                                        <option value="Betontagsten - Sort">Betontagsten - Sort</option>
                                        <option value="Eternittag B6 - Grå">Eternittag B6 - Grå</option>
                                        <option value="Eternittag B6 - Sort">Eternittag B6 - Sort</option>
                                        <option value="Eternittag B6 - Mokka (brun)">Eternittag B6 - Mokka (brun)</option>
                                        <option value="Eternittag B6 - Rødbrun">Eternittag B6 - Rødbrun</option>
                                        <option value="Eternittag B6 - Teglrød">Eternittag B6 - Teglrød</option>
                                        <option value="Eternittag B7 - Grå">Eternittag B7 - Grå</option>
                                        <option value="Eternittag B7 - Sort">Eternittag B7 - Sort</option>
                                        <option value="Eternittag B7 - Mokka (brun)">Eternittag B7 - Mokka (brun)</option>
                                        <option value="Eternittag B7 - Rødbrun">Eternittag B7 - Rødbrun</option>
                                        <option value="Eternittag B7 - Teglrød">Eternittag B7 - Teglrød</option>
                                        <option value="Eternittag B7 - Rødflammet">Eternittag B7 - Rødflammet</option>
                                    </select>
                                </div>

                                <h5 class="fw-light">Taghældning</h5>
                                <div class="col-12 col-md-7 mb-3">
                                    <select class="form-select flex-column" name="roof" required>
                                        <option value="15 grader">15 grader</option>
                                        <option value="20 grader">20 grader</option>
                                        <option selected="selected" value="25 grader">25 grader</option>
                                        <option value="30 grader">30 grader</option>
                                        <option value="35 grader">35 grader</option>
                                        <option value="40 grader">40 grader</option>
                                        <option value="45 grader">45 grader</option>
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
                                    <select class="form-select flex-column" name="toolroomWidth" required>
                                        <option value="" selected="true" disabled="disabled">Ønsker ikke redskabsrum</option>
                                        <option value="210 cm">210 cm</option>
                                        <option value="240 cm">240 cm</option>
                                        <option value="270 cm">270 cm</option>
                                        <option value="300 cm">300 cm</option>
                                        <option value="330 cm">330 cm</option>
                                        <option value="360 cm">360 cm</option>
                                        <option value="390 cm">390 cm</option>
                                        <option value="420 cm">420 cm</option>
                                        <option value="450 cm">450 cm</option>
                                        <option value="480 cm">480 cm</option>
                                        <option value="510 cm">510 cm</option>
                                        <option value="540 cm">540 cm</option>
                                        <option value="570 cm">570 cm</option>
                                        <option value="600 cm">600 cm</option>
                                        <option value="630 cm">630 cm</option>
                                        <option value="660 cm">660 cm</option>
                                        <option value="690 cm">690 cm</option>
                                        <option value="720 cm">720 cm</option>
                                    </select>
                                </div>

                                <h5 class="fw-light">Redskabsrum længde</h5>
                                <div class="col-12 col-md-7 mb-3">
                                    <select class="form-select flex-column" name="toolroomLength" id="choose-menu" required>
                                        <option value="" selected="true" disabled="disabled">Ønsker ikke redskabsrum</option>
                                        <option value="150 cm">150 cm</option>
                                        <option value="180 cm">180 cm</option>
                                        <option value="210 cm">210 cm</option>
                                        <option value="240 cm">240 cm</option>
                                        <option value="270 cm">270 cm</option>
                                        <option value="300 cm">300 cm</option>
                                        <option value="330 cm">330 cm</option>
                                        <option value="360 cm">360 cm</option>
                                        <option value="390 cm">390 cm</option>
                                        <option value="420 cm">420 cm</option>
                                        <option value="450 cm">450 cm</option>
                                        <option value="480 cm">480 cm</option>
                                        <option value="510 cm">510 cm</option>
                                        <option value="540 cm">540 cm</option>
                                        <option value="570 cm">570 cm</option>
                                        <option value="600 cm">600 cm</option>
                                        <option value="630 cm">630 cm</option>
                                        <option value="660 cm">660 cm</option>
                                        <option value="690 cm">690 cm</option>
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
<t:footer />