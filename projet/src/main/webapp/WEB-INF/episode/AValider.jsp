<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Episodes à valider</h1>
    </jsp:attribute>

    <jsp:body>
        <c:forEach items="${episodes}" var="episode">
        <div class="text-left">
        <h3>Episode daté à <c:out value="${episode.getDate()}"/> UT<c:if test="${episode.getAventure() != null}">, 
        pendant l'aventure <a href="game?action=show&id=${episode.getAventure().getId()}"
        target="_blank"><c:out value="${episode.getAventure().getTitre()}"/></a></c:if></h3>

        <c:forEach items="${episode.getParagraphes()}" var="parag">
        <p<c:if test="${parag.isSecret()}"> class="text-info"</c:if>><c:out value="${parag.getTexte()}"/></p>
        </c:forEach>
           <form action="episode" method="post">
           <button class="btn btn-primary btn-sm" type="submit">Valider l'épisode</button>
           <input type="hidden" name="eID" value="${episode.getId()}"/>
           <input type="hidden" name="action" value="validerParMJ"/>
           <input type="hidden" name="mjID" value="${episode.getMJ().getId()}"/>
           </form>
           <br/>
        </div>
        </c:forEach>
    </jsp:body>
</t:wrapper>