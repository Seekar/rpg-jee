<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Episode du ${episode.getDate()}</h1>
    </jsp:attribute>

    <jsp:body>
        <a href="paragraphe?eID=${episode.getId()}&action=new" > Nouveau Paragraphe</a> <br/>
        <c:forEach items="${episode.getParagraphes()}" var="parag">
                <c:choose>
                    <c:when test="${parag.getSecret()}">
                        (secret) ${parag.getTexte()}  
                          <form action="biographie" method="POST">
                                <button type="submit"> reveler </button>
                                <input type="hidden" name="paragID" value="${parag.getID()}"/>
                                <input type="hidden" name="action" value="reveler"/>
                          </form>                 
                </c:when>
                <c:otherwise>
                    ${parag.texte} 
                </c:otherwise>
                        
                            
            </c:choose>
                        <br/>
                        <a href="paragraphe?ID=${parag.getID()}&action=edit"> Editer </a>
                        <br/>
        </c:forEach>
                  
    </jsp:body>
</t:wrapper>

