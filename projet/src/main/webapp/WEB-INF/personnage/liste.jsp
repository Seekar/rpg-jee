<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Liste des personnages possédés</h1>
    </jsp:attribute>

    <jsp:body>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Nom</th>
                </tr>
            </thead>

            <tbody>
            <c:forEach var="perso" items="${persos}">
                <tr onclick="document.location = 'character?show=${perso.getId()}';">
                    <td>${perso.getNom()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:wrapper>
