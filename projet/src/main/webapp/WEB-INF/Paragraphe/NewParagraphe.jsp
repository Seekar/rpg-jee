<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Nouveau Paragraphe</h1>
    </jsp:attribute>

    <jsp:body>
        <form action="paragraphe" method="post">
            <textarea name="texte" >
                
            </textarea><br/>
            <input type="checkbox" value="false" name="secret"/><label> secret </label><br/>
            <button type="submit"> Ok </button>
            <input type="hidden" name="episodeID" value="${eID}"/>
            
        </form>
    </jsp:body>
</t:wrapper>
