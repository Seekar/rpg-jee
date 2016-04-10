<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Episodes de <c:out value="${perso.nom}"/> en édition</h1>
    </jsp:attribute>

    <jsp:body>
        <div class="text-left">
        <a href="episode?action=new&bioID=${perso.getBiographie().getID()}&pid=${perso.getId()}"
              class="btn btn-primary">Nouvel épisode</a>
        
        <c:forEach items="${perso.getBiographie().getEpisodes()}" var="episode">
        <c:if test="${!episode.getValide()}">
        <br/>
        <h3>Episode daté à <c:out value="${episode.getDate()}"/> UT</h3>

        <c:forEach items="${episode.getParagraphes()}" var="parag">
            <p<c:if test="${parag.isSecret()}"> class="text-info"</c:if>><c:out value="${parag.getTexte()}"/></p>
            <c:if test="${parag.isSecret()}">
            <form action="biographie" method="POST">
                <button type="submit" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-eye-open" 
                aria-hidden="true"></span> Révéler ce paragraphe</button>
                <input type="hidden" name="persoID" value="${perso.getId()}"/>
                <input type="hidden" name="paragID" value="${parag.getID()}"/>
                <input type="hidden" name="action" value="reveler"/>
            </form>
            <br/>
            </c:if>
        </c:forEach>
        </c:if>

        <a class="btn btn-default btn-sm" href="episode?id=${episode.getId()}&action=edit&persoID=${perso.getId()}" > Modifier l'épisode</a>
        <a class="btn btn-danger btn-sm" href="episode?id=${episode.getId()}&action=suppr&persoID=${perso.getId()}" > Supprimer l'épisode</a>
        <a class="btn btn-success btn-sm" href="episode?id=${episode.getId()}&action=valider&persoID=${perso.getId()}" > Valider l'épisode</a>
        
        </c:forEach>
        </div>

    </jsp:body>
</t:wrapper>
