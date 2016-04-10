<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Episode daté à ${episode.getDate()} UT</h1>
    </jsp:attribute>

    <jsp:body>
        <a class="btn btn-primary btn-sm" href="paragraphe?eID=${episode.getId()}&action=new&persoID=${persoID}" >
        Nouveau Paragraphe</a> <br/>
        <c:forEach items="${episode.getParagraphes()}" var="parag">
                <c:choose>
                    <c:when test="${parag.getSecret()}">
                        (secret) ${parag.getTexte()}  
                          <form action="biographie" method="POST">
                                <button type="submit" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-eye-open" 
                aria-hidden="true"></span> Révéler</button>
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
            <a class="btn btn-default btn-xs" href="paragraphe?ID=${parag.getID()}&action=edit&persoID=${persoID}">
            Modifier</a>
            <br/>
        </c:forEach>
                  
    </jsp:body>
</t:wrapper>

