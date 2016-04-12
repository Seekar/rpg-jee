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
                    <th>Profession</th>
                    <c:if test="${persoKiller}"><th>Action(s)</th></c:if>
                    </tr>
                </thead>

                <tbody>
                <c:set var="idPartie" value="${param.id}"/>
                <c:forEach var="perso" items="${persos}">
                    <tr onclick="document.location = 'character?action=show&id=${perso.getId()}';" style="cursor:pointer" >
                        <td><c:out value="${perso.getNom()}"/></td>
                        <td><c:out value="${perso.getProfession()}"/></td>
                        <c:if test="${persoKiller}">
                            <td>                    
                                <form action="game" method="POST">
                                    <button class="btn btn-primary" type="submit" class="btn btn-primary">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 
                                         Retirer
                                    </button>
                                    <input type="hidden" name="idPartie" value="${idPartie}">
                                    <input type="hidden" name="idPerso" value="${perso.getId()}">
                                    <input type="hidden" name="action" value="removeMember">
                                </form>
                            </td>
                        </c:if>
                    </tr>

                </c:forEach>
            </tbody>
        </table>
        <c:if test="${persoKiller}"><br/><a href="game?action=show&id=${idPartie}" class="btn btn-default">
                Retour à la partie</a></c:if>
    </jsp:body>
</t:wrapper>
