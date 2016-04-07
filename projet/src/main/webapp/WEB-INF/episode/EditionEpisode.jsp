<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Episode du ${episode.getDate()}</h1>
    </jsp:attribute>

    <jsp:body>
        <a class="btn btn-primary" href="paragraphe?eID=${episode.getId()}&action=new&persoID=${persoID}" > Nouveau Paragraphe</a> <br/>
        <c:forEach items="${episode.getParagraphes()}" var="parag">
                <c:choose>
                    <c:when test="${parag.getSecret()}">
                        (secret) ${parag.getTexte()}  
                          <form action="biographie" method="POST">
                                <button class="btn btn-default btn-sm" type="submit">  reveler </button>
                                <input type="hidden" name="paragID" value="${parag.getID()}"/>
                                <input type="hidden" name="action" value="reveler"/>
                                <input type="hidden" name="persoID" value="${persoID}"/>
                          </form>                 
                </c:when>
                <c:otherwise>
                    ${parag.texte} 
                </c:otherwise>
                        
                            
            </c:choose>
                        <br/>
                        <a class="btn btn-default btn-sm" href="paragraphe?ID=${parag.getID()}&action=edit&persoID=${persoID}"> Editer </a>
                        <br/>
        </c:forEach>
                  
    </jsp:body>
</t:wrapper>

