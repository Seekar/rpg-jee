<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="title"> - Supprimer un épisode</jsp:attribute>
    <jsp:attribute name="header">
        <h1>Voulez-vous vraiment supprimer cet épisode ?</h1>
    </jsp:attribute>

    <jsp:body>
        <h4>Date : <c:out value="${episode.getDate()}"/> UT</h4>
        <br/>

        <div class="text-left">
        <c:forEach items="${episode.getParagraphes()}" var="parag">
        <p<c:if test="${parag.isSecret()}"> class="text-info"</c:if>><c:out value="${parag.getTexte()}"/></p>
        </c:forEach>
        <br/>
        </div>
        <form action="episode" method="post">
           <input type="hidden" name="res" value='oui' class="btn btn-default"/>
           <a href="biographie?action=afficher&id=${persoID}" class="btn btn-default">Annuler</a>
           <button class="btn btn-danger" type="submit">Supprimer</button>
           <input type="hidden" name="pID" value="${episode.getId()}"/>
           <input type="hidden" name="action" value="validesuppr"/>
           <input type="hidden" name="persoID" value="${persoID}"/>
        </form>
    </jsp:body>
</t:wrapper>
