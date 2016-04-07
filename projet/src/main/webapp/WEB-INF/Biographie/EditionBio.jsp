<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Edition de la biographie de <c:out value="${perso.nom}"/></h1>
    </jsp:attribute>

    <jsp:body>
        <h2>Episodes en cours d'édition</h2>

        <p><a class="btn btn-primary" href="episode?action=new&bioID=${perso.getBiographie().getID()}&pid=${perso.getId()}" >Nouveau</a></p>
        
        <c:forEach items="${perso.getBiographie().getEpisodes()}" var="episode">
            <c:if test="${!episode.getValide()}">
                <c:choose >                   
                    <c:when test="${episode.getParagraphes().size() !=0}">
                        <c:forEach items="${episode.getParagraphes()}" var="parag">
                            <c:choose>
                                <c:when test="${parag.isSecret()}">  
                                    <p><c:out value="${parag.getTexte()}"/> (secret)</p>
                                    <form action="biographie" method="POST">
                                        <button class="btn btn-primary" type="submit" class="btn btn-primary">Révéler</button>
                                        <input type="hidden" name="paragID" value="${parag.getID()}"/>
                                        <input type="hidden" name="action" value="reveler"/>
                                        <input type="hidden" name="persoID" value="${perso.getId()}"/>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <p><c:out value="${parag.getTexte()}"/></p>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        (Episode vide)
                    </c:otherwise>
                </c:choose>  
                <a class="btn btn-default btn-sm" href="episode?id=${episode.getId()}&action=edit&persoID=${perso.getId()}" > Editer l'épisode</a>
                <a class="btn btn-default btn-sm" href="episode?id=${episode.getId()}&action=suppr&persoID=${perso.getId()}" > Supprimer l'épisode</a>
                <a class="btn btn-default btn-sm" href="episode?id=${episode.getId()}&action=valider&persoID=${perso.getId()}" > Valider l'épisode</a>
            </c:if><br/>
        </c:forEach>

    </jsp:body>
</t:wrapper>
