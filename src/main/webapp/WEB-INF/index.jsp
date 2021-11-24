<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head />
<t:navbar>
    <jsp:body>
        <div class="landing-header">

            <div class="video-overlay"></div>

            <div class="container h-100">
                <div class="d-flex h-100 text-center align-items-center">
                    <div class="landing-container text-white w-100">
                        <h1 class="display-3 text-shadow">Olsker Cupcakes</h1>
                        <p class="lead mb-0 text-shadow">Cupcakes til dine lår</p>
                    </div>
                </div>
            </div>
            <video class="landing-video" playsinline autoplay muted loop>
                <source src="${pageContext.request.contextPath}/resources/vid/hero.mp4" type="video/mp4">
            </video>
        </div>

        <!-- Cards container-->
        <div class="container py-3 py-md-5">
            <div class="row row-cols-1 row-cols-sm-1 row-cols-md-1 row-cols-lg-2 g-4">

                <!-- Cards -->
                <div class="col">
                    <div class="card mb-3">
                        <div class="row g-0">
                            <div class="col-12 col-md-12 col-lg-5 p-0 pt-lg overflow-hidden">
                                <img class="card-img-container" src="${pageContext.request.contextPath}/resources/img/cardImg/card-img.png" alt="...">
                            </div>
                            <div class="rounded-3 col-12 col-md-12 col-lg-7 my-3 my-md-3">
                                <div class="card-body">
                                    <h5 class="card-title">Olsker Cupcakes er mere end bare cupcakes</h5>
                                    <p class="card-text">Det er løftet om en ekstraordinær smagsoplevelse. Hos Olsker Cupcakes er kvalitet og indtryk vigtige fokuspunkter som adskiller os fra andre.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card mb-3">
                        <div class="row g-0">
                            <div class="col-12 col-md-12 col-lg-5 p-0 pt-lg overflow-hidden">
                                <img class="card-img-container" src="${pageContext.request.contextPath}/resources/img/cardImg/card-img.png" alt="...">
                            </div>
                            <div class="rounded-3 col-12 col-md-12 col-lg-7 my-3 my-md-3">
                                <div class="card-body">
                                    <h5 class="card-title">Bagt med kærlighed og højeste kvalitet</h5>
                                    <p class="card-text">Olsker Cupcakes garanterer den bedste kvalitets cupcakes med brug af eksotiske og høj-kvalitets ingredienser. Cupcakes er ikke bare en lækker snack, det er en kunstform.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card mb-3">
                        <div class="row g-0">
                            <div class="col-12 col-md-12 col-lg-5 p-0 pt-lg overflow-hidden">
                                <img class="card-img-container" src="${pageContext.request.contextPath}/resources/img/cardImg/card-img.png" alt="...">
                            </div>
                            <div class="rounded-3 col-12 col-md-12 col-lg-7 my-3 my-md-3">
                                <div class="card-body">
                                    <h5 class="card-title">Håndlavet fra bunden</h5>
                                    <p class="card-text">Alle Olsker Cupcakes er håndlavet for at sikre den bedste kvalitet. Vores mål er at skabe en oplevelse som gør dig glad i både mund og sind.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card mb-3">
                        <div class="row g-0">
                            <div class="col-12 col-md-12 col-lg-5 p-0 pt-lg overflow-hidden">
                                <img class="card-img-container" src="${pageContext.request.contextPath}/resources/img/cardImg/card-img.png" alt="...">
                            </div>
                            <div class="rounded-3 col-12 col-md-12 col-lg-7 my-3 my-md-3">
                                <div class="card-body">
                                    <h5 class="card-title">Om os</h5>
                                    <p class="card-text">Olsker Cupcakes blev stiftet i 2019 med målet at lave cupcakes af den højeste kvalitet. Holdet bag Olsker Cupcakes har længe haft lidenskab til kunsten bag cupcakes og fører det ud i virkeligheden med over 30 forskellige varianter af cupcakes.</p>
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
