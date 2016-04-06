<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Biographie de <c:out value="${perso.nom}"/></h1>
    </jsp:attribute>

    <jsp:body>
        <p><c:out value="${perso.getBiographie().getTexte()}"/></p>
        <c:forEach items="${perso.getBiographie().getEpisodes()}" var="episode">
        <c:if test="${episode.getValide()}">
            <c:forEach items="${episode.getParagraphes()}" var="parag">
                <c:choose>
                <c:when test="${parag.getSecret()}">
                    <c:if test="${owner}">
                        <p><c:out value="${parag.getTexte()}"/> (secret)</p>
                        <form action="biographie" method="POST">
                            <button type="submit" class="btn btn-primary">Révéler</button>
                            <input type="hidden" name="paragID" value="${parag.getID()}"/>
                            <input type="hidden" name="action" value="reveler"/>
                        </form>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <p><c:out value="${parag.getTexte()}"/></p>
                </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:if>
        <c:if test="${episode.getAventure() != null}">
            <a href="aventure?action=show&id=${episode.getAventure().getId()}" 
            class="btn btn-primary">Aventure liée</a> <!-- a valider avec celui qui fait aventure-->
        </c:if>
        </c:forEach>
        <c:if test="${owner}">
            <a href="biographie?action=edition&persoID=${perso.getId()}&biographie=${perso.getBiographie().getID()}" 
            class="btn btn-primary">Editer</a>
        </c:if>      
    </jsp:body>
</t:wrapper>
