<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head />
<t:navbar>
    <jsp:body>
        <div class="landing-header">

            <div class="video-overlay"></div>

            <div class="container w-100">
                <div class="d-flex w-100 text-center align-items-center">
                    <div class="landing-container text-white l-100">
                        <h1 class="display-3 text-shadow">Johannes Fog Carportbygger</h1>
                        <p class="lead mb-0 text-shadow">Byg din drømmecarport i dag!</p>
                    </div>
                </div>
            </div>
            <video class="landing-video" playsinline autoplay muted loop>
                <source src="${pageContext.request.contextPath}/resources/vid/hero.mp4" type="video/mp4">
            </video>
        </div>

        <!-- Cards container-->
        <div class="container py-3 py-md-5">
            <div>

                <div class="col">
                    <div class="card mb-3">
                        <div class="row g-0">
                            <div class="col-12 col-md-12 col-lg-5 p-0 pt-lg overflow-hidden">
                                <img class="card-img-container" src="${pageContext.request.contextPath}/resources/img/cardImg/carport1.jpg" alt="...">
                            </div>
                            <div class="rounded-3 col-12 col-md-12 col-lg-7 my-3 my-md-3">
                                <div class="card-body">
                                    <h5 class="card-title">Byg din egen carport nemt</h5>
                                    <p class="card-text">Med Johannes Fogs smarte carportbygger kan du nemt bygge din egen carport. Blot angiv mål, tagtype og redskabsrumsspecifikationskrav og send en forspørgelse. Din forspørgelse vil være synlig i Johannes Fogs brugerpanelet hvor det er muligt at se en plantegning af din nye carport. Hvis din forspørgelse er accepteret vil vi kontakte dem via telefon.</p>
                                    <a href="/carport"><button class="btn btn-outline-dark flex-shrink-0 position-absolute" style="bottom: 10px;">Gå til carportbygger</button></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card mb-3">
                        <div class="row g-0">
                            <div class="col-12 col-md-12 col-lg-5 p-0 pt-lg overflow-hidden">
                                <img class="card-img-container" src="${pageContext.request.contextPath}/resources/img/cardImg/carport2.jpg" alt="...">
                            </div>
                            <div class="rounded-3 col-12 col-md-12 col-lg-7 my-3 my-md-3">
                                <div class="card-body">
                                    <h5 class="card-title">Om os</h5>
                                    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                                    <a href="/kontakt"><button class="btn btn-outline-dark flex-shrink-0 position-absolute" style="bottom: 10px;">Kontakt os</button></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:navbar>
<t:footer />
