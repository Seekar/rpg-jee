<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Biographie de <c:out value="${perso.nom}"/></h1>
    </jsp:attribute>

    <jsp:body>
        <div class="text-left">
        <p>${perso.getBiographie().showTexte()}</p>

        <br>
        <c:if test="${owner}">
        <a href="biographie?action=edition&persoID=${perso.getId()}&biographie=${perso.getBiographie().getID()}" 
           class="btn btn-default">Accéder à l'éditeur</a>
        </c:if>

        <c:forEach items="${perso.getBiographie().getEpisodes()}" var="episode">
        <c:if test="${episode.getValide()}">
        <br>
        <h3>Episode daté à <c:out value="${episode.getDate()}"/> UT<c:if test="${episode.getAventure() != null}">, 
        pendant l'aventure <a href="game?action=show&id=${episode.getAventure().getId()}"
         target="_blank"><c:out value="${episode.getAventure().getTitre()}"/></a></c:if></h3>

        <c:forEach items="${episode.getParagraphes()}" var="parag">
            <p<c:if test="${parag.isSecret()}"> class="text-info"</c:if>><c:out value="${parag.getTexte()}"/></p>
            <c:if test="${owner && parag.isSecret()}">
            <form action="biographie" method="POST">
                <button type="submit" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-eye-open" 
                aria-hidden="true"></span> Révéler ce paragraphe</button>
                <input type="hidden" name="persoID" value="${perso.getId()}"/>
                <input type="hidden" name="paragID" value="${parag.getID()}"/>
                <input type="hidden" name="action" value="reveler"/>
            </form>
            <br>
            </c:if>
        </c:forEach>
        </c:if>

        </c:forEach>
        </div>
    </jsp:body>
</t:wrapper>
