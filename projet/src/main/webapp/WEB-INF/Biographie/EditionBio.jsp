<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Edition de la biographie de ${perso.nom}</h1>
    </jsp:attribute>

    <jsp:body>
        <h2> Episodes en cours d'édition</h2>
        
        <a href="episode?action=new&bioID=${perso.getBiographie().getID()}" > Nouveau</a> <br/>
         <c:forEach items="${perso.getBiographie().getEpisodes()}" var="episode">
             
            <c:if test="${!episode.getValide()}" >
            <c:forEach items="${episode.getParagraphes()}" var="parag">
                <c:choose>
                    <c:when test="${parag.getSecret()}">
                        (secret) ${parag.getTexte()}  <br/>
                          <form action="biographie" method="POST">
                                <button type="submit"> reveler </button>
                                <input type="hidden" name="paragID" value="${parag.getID()}"/>
                                <input type="hidden" name="action" value="reveler"/>
                          </form>                 
                </c:when>
                <c:otherwise>
                    ${parag.getTexte()} 
                </c:otherwise>
                        
                            
            </c:choose>
              <br/>
        </c:forEach>
        <a href="episode?id=${episode.getId()}&action=edit&persoID=${perso.getId()}" > Editer l'épisode</a>
        <a href="episode?id=${episode.getId()}&action=suppr&persoID=${perso.getId()}" > Supprimer l'épisode</a>
        <a href="episode?id=${episode.getId()}&action=valider&persoID=${perso.getId()}" > Valider l'épisode</a>
         </c:if><br/>
    </c:forEach>
              
    </jsp:body>
</t:wrapper>
