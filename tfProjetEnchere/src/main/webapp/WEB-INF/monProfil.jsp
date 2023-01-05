<%@ page import="fr.eni.tfProjetEnchere.bo.Utilisateur" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- Google Search Meta -->
    <meta name="title" content="ENI - ENCHERES - ED2WM-2210A" />
    <meta name="description" content="Sites Enchères en ligne ENI" />
    <meta name="author" content="Virginie, Kenza, Thomas" />

    <!-- FavIcons Settings -->
    <link rel="icon" href="img/favicon.png" />
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"/>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"rel="stylesheet"/>
    <!-- MDB -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/4.2.0/mdb.min.css" rel="stylesheet"/>
    <!-- BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <!-- Normalize CSS and Steelsheets -->
    <link href="css/normalize.css" rel="stylesheet" />
    <link href="css/style.css" rel="stylesheet" />
    <title>Mon Profil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

</head>
<body>
<body>
<% Utilisateur user = (Utilisateur) session.getAttribute("user"); %>
<header>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-transparent shadow-0">
        <!-- Container wrapper -->
        <div class="container">
            <!-- Navbar brand -->
            <a class="navbar-brand me-2" href="<%=request.getContextPath()%>">
                <img src="img/logo-navbar.png" height="35" alt="ENI LOGO" loading="lazy" style="margin-top: -1px;"/>
            </a>
            <!-- Toggle button -->
            <button class="navbar-toggler" type="button" data-mdb-toggle="collapse" data-mdb-target="#navbarButtonsExample" aria-controls="navbarButtonsExample" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fas fa-bars"></i>
            </button>
            <!-- Collapsible wrapper -->
            <div class="collapse navbar-collapse" id="navbarButtonsExample">
                <!-- Left links -->
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>">PROJET ENCHERES</a>
                    </li>
                </ul>
                <!-- Left links -->
                <div class="d-flex align-items-center">
                    <ul class="navbar-nav me-3 mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>">Enchères</a>
                        </li>
                        <c:if test="${isAllowed}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/VenteArticleServlet">Vendre un Article</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/ProfilUtilisateurServlet">Mon Profil</a>
                        </li>
                    </ul>
                </div>
                <div class="d-flex">
                    </c:if>
                    <c:if test="${!isAllowed}">
                        <a href="${pageContext.request.contextPath}/connexion"><button type="button" class="btn btn-secondary me-3 mb-2 shadow-2">CONNEXION</button></a>
                        <a href="${pageContext.request.contextPath}/connexion"><button type="button" class="btn btn-primary me-3 shadow-2">CREER UN COMPTE</button></a>
                    </c:if>
                    <c:if test="${isAllowed}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/ProfilUtilisateurServlet"><button type="button" class="btn btn-outline-success me-3 shadow-2">${userPseudo.pseudo}, ${userPseudo.credit} Crédits</button></a>
                        <a href="${pageContext.request.contextPath}/connexion?action=deconnexion"><button type="button" class="btn btn-warning me-3 shadow-2">DECONNEXION</button></a>
                    </c:if>
                </div>
            </div>
            <!-- Collapsible wrapper -->
        </div>
        <!-- Container wrapper -->
    </nav>
    <!-- Navbar -->
</header>
<main>

    <div class="container img-article mt-3 d-flex justify-content-center">
        <img src="${pageContext.request.contextPath}/img/logo-ENI-full.png" width="200"/>
    </div>
        <p class="h4 my-3 text-center">MON PROFIL</p>
    <c:if test="${errorPasswordProfil}">
        <div class="alert alert-danger container d-flex align-items-center" type="alert">
                <p autofocus>Merci de rentrer le bon mot de passe</p>
        </div>
    </c:if>
    <div class="container col-12 col-sm-10 col-md-8 col-lg-7 col-xl-4">
        <section class="d-flex justify-content-center border rounded-2 p-4 bg-white shadow-1">
            <div class="col-12 col-lg-10">
                <form class="d-flex flex-column align-items-center" method="post" action="${pageContext.request.contextPath}/ProfilUtilisateurServlet">
                    <input type="hidden" name="noUtilisateur" value="${userPseudo.id}"/>
                    <!-- Username input -->
                    <div class="d-flex col-12 col-sm-11 col-md-11 justify-content-between gap-2">
                        <div class="form-outline mb-4">
                            <input type="text" name="profilPseudo" id="registerUsername" class="form-control" value="${userPseudo.pseudo}"/>
                            <label class="form-label" for="registerUsername">Pseudo</label>
                        </div>
                        <!-- Name input -->
                        <div class="form-outline mb-4 justify-content-between">
                            <input type="text" name="profilNom" id="registerName" class="form-control"value="${userPseudo.nom}"/>
                            <label class="form-label" for="registerName">Nom</label>
                        </div>
                    </div>
                    <!-- Surname input-->
                    <div class="col-12 col-sm-11 col-md-11">
                        <div class="form-outline mb-4">
                            <input type="text" name="profilPrenom" id="registerSurname" class="form-control" value="${userPseudo.prenom}"/>
                            <label class="form-label" for="registerSurname">Prénom</label>
                        </div>
                        <!-- E-Mail input -->
                        <div class="form-outline mb-4">
                            <input type="email" name="profilEmail" id="registerEmail" class="form-control" value="${userPseudo.email}"/>
                            <label class="form-label" for="registerEmail">E-mail</label>
                        </div>
                    </div>
                    <!-- Phone input -->
                    <div class="col-12 col-sm-11 col-md-11">
                        <div class="form-outline mb-4">
                            <input type="text" name="profilTelephone" id="registerTel" class="form-control" value="${userPseudo.telephone}"/>
                            <label class="form-label" for="registerTel">Téléphone</label>
                        </div>
                        <!-- Rue input -->
                        <div class="form-outline mb-4">
                            <input type="text" name="profilRue" id="registerRue" class="form-control" value="${userPseudo.rue}"/>
                            <label class="form-label" for="registerRue">Rue</label>
                        </div>
                    </div>
                    <!-- Code Postal input -->
                    <div class="d-flex justify-content-between col-12 col-sm-11 col-md-11 gap-2">
                        <div class="form-outline mb-4">
                            <input type="text" name="profilCodePostal" id="registerPostalCode" class="form-control" value="${userPseudo.codePostal}"/>
                            <label class="form-label" for="registerPostalCode">Code Postal</label>
                        </div>
                        <!-- Ville input -->
                        <div class="form-outline mb-4">
                            <input type="text" name="profilVille" id="registerVille" class="form-control" value="${userPseudo.ville}"/>
                            <label class="form-label" for="registerVille">Ville</label>
                        </div>
                    </div>
                    <!-- Password input -->
                        <div class="form-outline mb-4 col-12 col-sm-11 col-md-11">
                            <input type="password" name="profilPassword" id="registerPassword" class="form-control"/>
                            <label class="form-label" for="registerPassword">Mot de Passe</label>
                        </div>
                    <!-- New Password input -->
                    <div class="col-12 col-sm-11 col-md-11">
                        <div class="form-outline mb-4">
                            <input type="password" name="profilNewPassword" id="registerNewPassword" class="form-control"/>
                            <label class="form-label" for="registerNewPassword">Nouveau Mot de Passe</label>
                        </div>
                        <!-- Check new password input -->
                        <div class="form-outline mb-4">
                            <input type="password" name="profilPasswordConf" id="registerPasswordConf" class="form-control"/>
                            <label class="form-label" for="registerPasswordConf">Confirmation</label>
                        </div>
                    </div>
                        <div class="text-center mb-2">
                            <button type="button" class="btn btn-light btn mb-3 shadow-1">Crédits : ${userPseudo.credit}</button>
                            <input type="hidden" name="profilCredit" value="${userPseudo.credit}"/>
                        </div>
                    <!-- Bouton Input -->
                    <div class="d-flex flex-column align-items-center justify-content-center col-12 gap-4 text-center">
                        <div class="col-12 col-sm-8 col-lg-9 text-center">
                            <div class="form-outline">
                                <button type="submit" class="btn btn-primary btn-block shadow-1" name="modifier" >ENREGISTRER</button>
                            </div>
                        </div>
                        <div class="col-12 col-sm-8 col-lg-9 text-center">
                            <div class="form-outline">
                                <a href="${pageContext.request.contextPath}/ProfilUtilisateurServlet?action=supprimer&noUtilisateur=${userPseudo.id}"><button type="button" class="btn btn-outline-danger btn-block shadow-1">SUPPRIMER</button></a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>
</main>
<!-- FOOTER -->
<section class="container">
    <!-- Footer -->
    <footer class="text-center text-white rounded-2 mb-2" style="background-color: #A1A0A0FF;">
        <!-- Grid container -->
        <div class="container p-4 pb-0 mt-5">
            <!-- Section: CTA -->
            <c:if test="${!isAllowed}">
                <section class="">
                    <p class="d-flex justify-content-center align-items-center">
                        <span class="me-3">Enregistrez-vous Gratuitement</span>
                        <a class="h2 text-primary mx-3" href="${pageContext.request.contextPath}/connexion"><button type="button" class="btn btn-outline-light btn-rounded">
                            CREER MON COMPTE
                        </button></a>
                    </p>
                </section>
            </c:if>
            <!-- Section: CTA -->
        </div>
        <!-- Grid container -->
        <!-- Copyright -->
        <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
            © 2022 Copyright : Virginie MERLINO / Kenza BELHADI / Thomas FANOUILLERE</a>
        </div>
        <!-- Copyright -->
    </footer>
    <!-- Footer -->
</section>
<!-- FIN FOOTER -->
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<!-- MDB -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/4.2.0/mdb.min.js"></script>
</body>
</html>