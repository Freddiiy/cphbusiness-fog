<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head>
    <t:navbar>
        <jsp:body>
            <c:choose>
                <c:when test="${requestScope.userList != null}">
                    <div class="container" style="margin-top: 20px">
                        <div class="bg-light rounded-3 px-0 pt-2 p-md-2 mb-0">
                            <h2 class="ps-4">Vores kunder</h2>
                            <div class="row">
                                <a class="col-2 btn btn-fog-primary m-3" href="${pageContext.request.contextPath}/admin/users">Kundeoversigt</a>
                                <a class="col-2 btn btn-fog-primary m-3" href="${pageContext.request.contextPath}/admin">Ordreoversigt</a>
                            </div>

                            <table class="table table-striped table-borderless flex-column table-hover text-center">
                                <thead>
                                <th class="col">Kunde ID</th>
                                <th class="col">Navn</th>
                                <th class="col">Email</th>
                                <th class="col">Telefon</th>
                                <th class="col">Addresse</th>
                                <th class="col">Post nr.</th>
                                <th class="col">By</th>
                                <th class="col">Rolle</th>
                                </thead>

                                <c:forEach var="item" items="${requestScope.userList}">
                                    <tr>
                                        <td>${item.id}</td>
                                        <td>${item.fname} ${item.lname}</td>
                                        <td>${item.email}</td>
                                        <td>${item.phone}</td>
                                        <td>${item.address}</td>
                                        <td>${item.zipcode}</td>
                                        <td>${item.city}</td>
                                        <td>${item.role}</td>
                                        <td class="p-0 m-0"><a type="button" href="${pageContext.request.contextPath}/admin/users/user?userId=${item.id}" class="btn btn-fog-primary">Se kundeoplysninger</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
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
