<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="title"> - <c:out value="${titre}"/></jsp:attribute>
    <jsp:attribute name="header">
        <h1><c:out value="${titre}"/></h1>
    </jsp:attribute>

    <jsp:body>
        <table class="table table-hover text-left">
            <thead>
                <tr>
                    <th>Nom</th>
                    <c:if test="${persoKiller}"><th>Action(s)</th></c:if>
                </tr>
            </thead>

            <tbody>
            <c:forEach var="perso" items="${persos}">
                <tr onclick="document.location = 'character?action=show&id=${perso.getId()}';" style="cursor:pointer" >
                    <td><c:out value="${perso.getNom()}"/></td>
                <c:if test="${persoKiller}">
                <td><a href="#" class="btn btn-danger"><%-- Lien retirer perso à mettre ICI --%>
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 
                Retirer
                </a></td></c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:wrapper>
