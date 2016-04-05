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
                        <form action="aventure" method="POST">
                                <button type="submit"> voir l'aventure </button>
                                <input type="hidden" name="nom" value="${episode.getAventure().getTitre()}"/>
                                <input type="hidden" name="action" value="show"/> <!-- a valider avec celui qui fait aventure-->
                            </form>
                    </c:if>
    </c:forEach>
    <c:if test="${owner}">
        <form action="biographie" method="POST">
            <button type="submit" > Editer </button>
            <input type="hidden" name="biographie" value="${perso.getBiographie().getID()}"/>
            <input type="hidden" name="persoID" value="${perso.getId()}"/>
            <input type="hidden" name="action" value="edition"/>
        </form>
    </c:if>      
</jsp:body>
</t:wrapper>
