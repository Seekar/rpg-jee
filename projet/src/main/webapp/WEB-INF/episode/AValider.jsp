<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Episodes à valider</h1>
    </jsp:attribute>

    <jsp:body>
        <c:forEach items="${episodes}" var="episode">
        <c:forEach items="${episode.getParagraphes()}" var="parag">
                <c:choose>
                    <c:when test="${parag.isSecret()}">
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
           <input type="hidden" name="eID" value="${episode.getId()}"/>
           <input type="hidden" name="action" value="validerParMJ"/>
           <input type="hidden" name="mjID" value="${episode.getMJ().getId()}"/><br/>
        </c:forEach>
    </jsp:body>
</t:wrapper>