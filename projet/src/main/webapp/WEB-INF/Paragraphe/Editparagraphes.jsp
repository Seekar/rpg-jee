<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Edition d'un paragraphe</h1>
    </jsp:attribute>

    <jsp:body>
        <form action="paragraphe" method="post">
            <textarea name="texte" >
                ${parag.getTexte()}
            </textarea><br/>
            
            <button type="submit"> Ok </button>
            <input type="hidden" value="${parag.getID()}" name="id"/>
            <input type="hidden" value="edit" name="action"/>
        </form>
    </jsp:body>
</t:wrapper>
