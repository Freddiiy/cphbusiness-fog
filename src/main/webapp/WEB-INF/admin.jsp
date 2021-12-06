<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head>
    <t:navbar>
        <jsp:body>
            <c:choose>
                <c:when test="${requestScope.orderList != null}">
                    <div class="container" style="margin-top: 20px">
                        <div class="bg-light rounded-3 px-0 pt-2 p-md-2 mb-0">
                            <h2 class="ps-4">Vores ordrer</h2>

                            <div class="row text-start px-5">
                                <h6 class="col-2 fw-bold">Ref nr.</h6>
                                <h6 class="col-1 fw-bold">Carport bredde</h6>
                                <h6 class="col-1 fw-bold">Carport længde</h6>
                                <h6 class="col-1 fw-bold">Tag</h6>
                                <h6 class="col-1 fw-bold">Bestilt</h6>
                                <h6 class="col-1 fw-bold">Redskabsrum</h6>
                                <h6 class="col-1 fw-bold">Status</h6>
                                <hr class="fw-bold">
                            </div>

                            <c:forEach var="item" items="${requestScope.orderList}">
                                <div class="px-0 py-5 mb-5 bg-light border rounded shadow mx-3">
                                    <div class="row text-start px-5">
                                        <h6 class="col-2 text-truncate">Userid: ${item.getUserId()}</h6>
                                        <h6 class="col-2 text-truncate">Email: ${item.getEmail()}</h6>
                                        <h6 class="col-2 text-truncate">Carport ID: ${item.getCarport().getId()}</h6>
                                        <h6 class="col-2 text-truncate">Carport længde: ${item.getCarport().getLength()}</h6>
                                        <h6 class="col-2 text-truncate">Carport bredde: ${item.getCarport().getWidth()}</h6>
                                        <h6 class="col-2 text-truncate">Carport tag id: ${item.getCarport().getIdRoof()}</h6>
                                        <h6 class="col-2 text-truncate">Har skur: ${item.getCarport().hasShed()}</h6>
                                        <h6 class="col-2 text-truncate">Skur længde ${item.getCarport().getShedLength()}</h6>
                                        <h6 class="col-2 text-truncate">Skur bredde ${item.getCarport().getShedWidth()}</h6>
                                        <h6 class="col-2 text-truncate">Timestamp: ${item.getTimestamp()}</h6>
                                        <hr class="d-lg-none mb-3 mb-lg-5">

                                    </div>

                                    <div class="row align-content-center align-content-lg-end px-5">
                                        <div class="col-12 col-lg-4 mb-1 mb-lg-0">
                                            <button type="button" class="col-12 btn btn-fog-secondary"
                                                    data-bs-toggle="modal"
                                                    data-bs-target="#modalAddBalance-${item.id}">
                                                Giv kredit
                                            </button>
                                        </div>
                                        <form class="col-12 col-lg-4 mb-1 mb-lg-0" method="get"
                                              action="${pageContext.request.contextPath}/admin/orders?userId=${item.id}">
                                            <input type="hidden" name="userId" value="${item.id}">
                                            <input class="col-12 btn btn-fog-secondary" type="submit"
                                                   value="Se ordre">
                                        </form>
                                        <div class="col-12 col-lg-4 mb-1 mb-lg-0">
                                            <button type="button" class="col-12 btn btn-fog-secondary"
                                                    data-bs-toggle="modal"
                                                    data-bs-target="#modalVerify-${item.id}">
                                                Slet bruger
                                            </button>
                                        </div>
                                    </div>

                                    <!-- Modal delete user -->
                                    <div class="modal fade" id="modalVerify-${item.id}" data-bs-keyboard="false"
                                         tabindex="-1" aria-labelledby="modal" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="modalVerifyTitle">Modal title</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <h4>Er du sikker på at du vil slette ${item.email}</h4>
                                                </div>
                                                <div class="modal-footer">
                                                    <form class="" method="post"
                                                          action="${pageContext.request.contextPath}/admin/remove-user">
                                                        <input type="hidden" name="userId" value="${item.id}">
                                                        <input class="btn btn-outline-danger" type="submit"
                                                               value="Slet ${item.email}">
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Modal add blance to user -->
                                    <div class="modal fade" id="modalAddBalance-${item.id}"
                                         data-bs-keyboard="false"
                                         tabindex="-1" aria-labelledby="modal" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="modalBalanceTitle">Modal title</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <h4>Hvor meget vil du give ${item.email}?</h4>
                                                </div>
                                                <div class="modal-footer">
                                                    <form class="row justify-content-end" method="post"
                                                          action="${pageContext.request.contextPath}/admin/add-balance">
                                                        <input type="hidden" name="userId" value="${item.id}">
                                                        <div class="col-4">
                                                            <input class="form-control" type="number"
                                                                   name="addToBalance">
                                                        </div>
                                                        <input class="col-4 btn btn-fog-secondary" type="submit"
                                                               value="Indsæt beløb">
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="container" style="margin-top: 80px">
                        <h1>Der kunne ikke findes nogle kunder.</h1>
                    </div>
                </c:otherwise>
            </c:choose>
        </jsp:body>
    </t:navbar>
    <t:footer/>
</t:head>
