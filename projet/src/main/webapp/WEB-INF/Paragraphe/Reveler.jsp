<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Voulez-vous vraiment révéler ce paragraphe ?</h1>
    </jsp:attribute>

    <jsp:body>
        <p><c:out value="${parag.getTexte()}"/></p>
        <br/>
        <form action="paragraphe" method="post">
           <input type="hidden" name="res" value='oui' class="btn btn-default"/>
           <a href="biographie?action=afficher&id=${persoID}" class="btn btn-default">Annuler</a>
           <button class="btn btn-default" type="submit">Révéler</button>
           <input type="hidden" name="pID" value="${parag.getID()}"/>
           <input type="hidden" name="action" value="reveler"/>
           <input type="hidden" name="persoID" value="${persoID}"/>
        </form>
    </jsp:body>
</t:wrapper>
