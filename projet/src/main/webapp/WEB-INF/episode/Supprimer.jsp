<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Confimer la suppression de l'épisode?</h1>
    </jsp:attribute>

    <jsp:body>
        <c:forEach items="${episode.getParagraphes()}" var="parag">
                <c:choose>
                    <c:when test="${parag.getSecret()}">
                        (secret) ${parag.getTexte()}  
           
                </c:when>
                <c:otherwise>
                    ${parag.texte} 
                </c:otherwise>
                        
                            
            </c:choose><br/>
        </c:forEach>
        <form action="episode" method="post">
           oui <input type="radio" name="res" value='oui'/><br/>
           non <input type="radio" name="res" value="non" checked/><br/>
           <button class="btn btn-primary" type="submit"> Ok</button>
           <input type="hidden" name="pID" value="${episode.getId()}"/>
           <input type="hidden" name="action" value="validesuppr"/>
           <input type="hidden" name="persoID" value="${persoID}"/>
        </form>
    </jsp:body>
</t:wrapper>
