<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head>
    <t:navbar>
        <jsp:body>
            <c:choose>
                <c:when test="${requestScope.userList != null}">
                    <div class="container" style="margin-top: 80px">
                        <div class="bg-light rounded-3 px-0 pt-4 p-md-5 mb-0">
                            <h1 class="ps-4">Vores kunder</h1>

                            <c:forEach var="item" items="${requestScope.userList}">
                                <div class="px-0 py-5 mb-5 bg-light border rounded shadow mx-3">
                                    <div class="row text-start px-5">
                                        <h4 class="col-12 col-lg-6 text-truncate">Email: ${item.getEmail()}</h4>
                                        <hr class="d-lg-none mb-3 mb-lg-5">
                                        <h4 class="col-12 col-lg-6">ID: ${item.getId()}</h4>
                                        <hr class="mb-3 mb-md-5">


                                        <h4 class="col-12 col-lg-6">Rolle: ${item.getRole()}</h4>
                                        <hr class="d-lg-none mb-3 mb-lg-5">
                                        <h4 class="col-12 col-lg-6">Balance: ${item.getBalance()} kr.</h4>
                                        <hr class="mb-3 mb-md-5">

                                    </div>

                                    <div class="row align-content-center align-content-lg-end px-5">
                                        <div class="col-12 col-lg-4 mb-1 mb-lg-0">
                                            <button type="button" class="col-12 btn btn-fog-secondary"
                                                    data-bs-toggle="modal"
                                                    data-bs-target="#modalAddBalance-${item.getId()}">
                                                Giv kredit
                                            </button>
                                        </div>
                                        <form class="col-12 col-lg-4 mb-1 mb-lg-0" method="get"
                                              action="${pageContext.request.contextPath}/admin/orders?userId=${item.getId()}">
                                            <input type="hidden" name="userId" value="${item.getId()}">
                                            <input class="col-12 btn btn-fog-secondary" type="submit"
                                                   value="Se ordre">
                                        </form>
                                        <div class="col-12 col-lg-4 mb-1 mb-lg-0">
                                            <button type="button" class="col-12 btn btn-fog-secondary"
                                                    data-bs-toggle="modal"
                                                    data-bs-target="#modalVerify-${item.getId()}">
                                                Slet bruger
                                            </button>
                                        </div>
                                    </div>

                                    <!-- Modal delete user -->
                                    <div class="modal fade" id="modalVerify-${item.getId()}" data-bs-keyboard="false"
                                         tabindex="-1" aria-labelledby="modal" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="modalVerifyTitle">Modal title</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <h4>Er du sikker på at du vil slette ${item.getEmail()}</h4>
                                                </div>
                                                <div class="modal-footer">
                                                    <form class="" method="post"
                                                          action="${pageContext.request.contextPath}/admin/remove-user">
                                                        <input type="hidden" name="userId" value="${item.getId()}">
                                                        <input class="btn btn-outline-danger" type="submit"
                                                               value="Slet ${item.getEmail()}">
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Modal add blance to user -->
                                    <div class="modal fade" id="modalAddBalance-${item.getId()}"
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
                                                    <h4>Hvor meget vil du give ${item.getEmail()}?</h4>
                                                </div>
                                                <div class="modal-footer">
                                                    <form class="row justify-content-end" method="post"
                                                          action="${pageContext.request.contextPath}/admin/add-balance">
                                                        <input type="hidden" name="userId" value="${item.getId()}">
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
