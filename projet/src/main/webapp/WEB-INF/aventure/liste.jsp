<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Liste des parties</h1>
    </jsp:attribute>

    <jsp:body>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Titre</th>
                </tr>
            </thead>

            <tbody style="cursor:pointer">
            <c:forEach var="partie" items="${parties}">
                <tr onclick="document.location = 'game?action=show&id=${partie.getId()}';">
                    <td><c:out value="${partie.getTitre()}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:wrapper>
