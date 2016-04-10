<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Edition de l'épisode daté à ${episode.getDate()} UT</h1>
    </jsp:attribute>

    <jsp:body>
        <div class="text-left">
        <a class="btn btn-primary btn-sm" href="paragraphe?eID=${episode.getId()}&action=new&persoID=${persoID}" >
        Nouveau paragraphe</a> <br/>
        
        <c:forEach items="${episode.getParagraphes()}" var="parag">
            <br/>
            <p<c:if test="${parag.isSecret()}"> class="text-info"</c:if>><c:out value="${parag.getTexte()}"/></p>
            <c:if test="${owner && parag.isSecret()}">
            <form action="biographie" method="POST">
                <button type="submit" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-eye-open" 
                aria-hidden="true"></span> Révéler ce paragraphe</button>
                <input type="hidden" name="persoID" value="${perso.getId()}"/>
                <input type="hidden" name="paragID" value="${parag.getID()}"/>
                <input type="hidden" name="action" value="reveler"/>
            </form>
            <br/>
            </c:if>
            <a class="btn btn-default btn-xs" href="paragraphe?ID=${parag.getID()}&action=edit&persoID=${persoID}">
            Modifier</a>
            <br/>
        </c:forEach>
        </div>
    </jsp:body>
</t:wrapper>

