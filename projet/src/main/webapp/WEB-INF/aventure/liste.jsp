<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1><c:out value="${titre}"/></h1>
    </jsp:attribute>

    <jsp:body>
        <table class="table table-hover text-left">
            <thead>
                <tr>
                    <th>Titre</th>
                    <th>Statut</th>
                    <c:if test="${hasPerso}"><th>Participant</th></c:if>
                </tr>
            </thead>

            <tbody style="cursor:pointer">
            <c:forEach var="partie" items="${parties}">
                <tr onclick="document.location = 'game?action=show&id=${partie.getId()}';"
                    <c:if test="${partie.isFinie()}"> class="active"</c:if>>
                    <td><c:out value="${partie.getTitre()}"/></td>
                    <td>
                    <c:choose>
                    <c:when test="${partie.isFinie()}">Terminée</c:when>
                    <c:otherwise>En cours</c:otherwise>
                    </c:choose>
                    </td>
                    <c:set var="perso" value="${partie.getPersonnage()}"/>
                    <c:if test="${hasPerso}">
                    <td>
                    <a href="character?action=show&id=${perso.getId()}" class="btn btn-info">
                    <c:out value="${perso.getNom()}"/>
                    </a></td></c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:wrapper>
