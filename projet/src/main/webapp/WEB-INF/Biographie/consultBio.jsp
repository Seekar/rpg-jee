<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Biographie de ${perso.nom}</h1>
    </jsp:attribute>

    <jsp:body>
        ${perso.getBiographie().getTexte()} <br/>
        <c:forEach items="${perso.getBiographie().getEpisodes()}" var="episode">
            <c:if test="${episode.getValide()}" >
                
            <c:forEach items="${episode.getParagraphes()}" var="parag">
                <c:choose>
                    <c:when test="${parag.getSecret()}">
                        <c:if test="${owner}">
                            
                            (secret)    ${parag.getTexte()}
                            <form action="biographie" method="POST">
                                <button type="submit"> reveler </button>
                                <input type="hidden" name="paragID" value="${parag.getID()}"/>
                                <input type="hidden" name="action" value="reveler"/>
                            </form>
                        
                    </c:if>
                </c:when>
                <c:otherwise>
                    ${parag.getTexte()} 
                </c:otherwise>
            </c:choose>
        </c:forEach>
         </c:if>
    <c:if test="${episode.getAventure() != null}">
        <a href="aventure?action=show&nom=${episode.getAventure().getTitre()}" 
        class="btn btn-primary">Aventure liée</a> <!-- a valider avec celui qui fait aventure-->
    </c:if>
    </c:forEach>
    <c:if test="${owner}">
        <a href="biographie?action=edition&persoID=${perso.getId()}&biographie=${perso.getBiographie().getID()}" 
        class="btn btn-primary">Editer</a>
    </c:if>      
</jsp:body>
</t:wrapper>
