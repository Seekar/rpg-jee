<%@tag description="Main template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html>
    <head>
        <title>RPG</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-theme.min.css" rel="stylesheet">
        <link href="css/theme.css" rel="stylesheet">

    </head>
    <body role="document">

    <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">RPG</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li <c:if test="${section == 'accueil'}">class="active"</c:if>><a href=".">Accueil</a></li>
            <li <c:if test="${section == 'login'}">class="active"</c:if>><a href="?login">Login</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Personnages<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Créer</a></li>
                <li><a href="#">Liste</a></li>
                <li role="separator" class="divider"></li>
                <li class="dropdown-header">Sous-titre</li>
                <li><a href="#">Fonction3</a></li>
                <li><a href="#">Fonction4</a></li>
              </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container theme-showcase" role="main">
    <div class="text-center">

        <div class="page-header">
            <jsp:invoke fragment="header"/>
        </div>

        <jsp:doBody/>

        <div class="page-footer">
            <jsp:invoke fragment="footer"/>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </div>
    </div>
    </body>
</html>