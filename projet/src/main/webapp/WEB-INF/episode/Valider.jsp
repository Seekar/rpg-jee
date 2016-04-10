<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="title"> - Valider un épisode</jsp:attribute>
    <jsp:attribute name="header">
        <h1>Voulez-vous vraiment valider cet épisode ?</h1>
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
           <a href="biographie?action=edition&persoID=${persoID}&biographie=${param.idBio}"
              class="btn btn-default">Annuler</a>
           <button class="btn btn-primary" type="submit">Valider</button>
           <input type="hidden" name="pID" value="${episode.getId()}"/>
           <input type="hidden" name="action" value="validevalid"/>
           <input type="hidden" name="persoID" value="${persoID}"/>
           <input type="hidden" name="idBio" value="${param.idBio}"/>
        </form>
    </jsp:body>
</t:wrapper>
