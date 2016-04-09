<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Biographie de <c:out value="${perso.nom}"/></h1>
    </jsp:attribute>

    <jsp:body>
        <p>${perso.getBiographie().showTexte()}</p>
        <c:forEach items="${perso.getBiographie().getEpisodes()}" var="episode">
        <c:if test="${episode.getValide()}">
            <table class="table table-striped">
                
            <thead>
                <tr>
                    <th>Paragraphes</th>
                    <th>Action(s)</th>
                </tr>
            </thead>

            <tbody>
            <c:forEach items="${episode.getParagraphes()}" var="parag">
                <tr><c:choose><c:when test="${parag.isSecret() && owner}">
                <td><c:out value="${parag.getTexte()}"/> (secret)</td>
                <td><form action="biographie" method="POST">
                    <button type="submit" class="btn btn-primary">Révéler</button>
                    <input type="hidden" name="persoID" value="${perso.getId()}"/>
                    <input type="hidden" name="paragID" value="${parag.getID()}"/>
                    <input type="hidden" name="action" value="reveler"/>
                    </form></td>
                </c:when>
                <c:otherwise><td><c:out value="${parag.getTexte()}"/></td><td></td></c:otherwise>
                </c:choose>
                </tr>
            </c:forEach>
            </tbody>
            </table>
            
            <c:if test="${episode.getAventure() != null}">
                <a href="game?action=show&id=${episode.getAventure().getId()}" 
                class="btn btn-primary">Aventure liée</a>
            </c:if>
        </c:if>
        </c:forEach>
        <c:if test="${owner}">
            <a href="biographie?action=edition&persoID=${perso.getId()}&biographie=${perso.getBiographie().getID()}" 
            class="btn btn-primary">Edition des épisodes</a>
        </c:if>      
    </jsp:body>
</t:wrapper>
