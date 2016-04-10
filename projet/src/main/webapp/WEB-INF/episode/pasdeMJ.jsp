<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Erreur : pas de Meneur de Jeu</h1>
    </jsp:attribute>

    <jsp:body>

        <div class="alert alert-danger alert-dismissible" role="alert">
            <p>Pour pouvoir valider un épisode le personage doit posséder un meneur de jeu.</p>
            <form method="get" action="biographie">
                <button class="btn btn-default" type="submit" > Ok </button>
                <input type="hidden" name="action" value="edition"/>
                <input type="hidden" name="persoID" value="${persoID}"/>
                <input type="hidden" name="biographie" value="${biographie}"/>
            </form>
        </div>
    </jsp:body>
</t:wrapper>
