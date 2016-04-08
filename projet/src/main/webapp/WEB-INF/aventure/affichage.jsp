<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="title"> - Détails de l'aventure</jsp:attribute>

    <jsp:attribute name="header">
        <h1>Détails de la partie</h1>
    </jsp:attribute>

    <jsp:body>

        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Informations</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>Nom</th>
                    <td><c:out value="${aventure.getTitre()}"/></td>
                </tr>
                <tr>
                    <th>Date</th>
                    <td><c:out value="${aventure.getDate()}"/></td>
                </tr>
                <tr>
                    <th>Lieu</th>
                    <td><c:out value="${aventure.getLieu()}"/></td>
                </tr>
                <tr>
                    <th>Univers</th>
                    <td><c:out value="${aventure.getUnivers().getNom()}"/></td>
                </tr>
                <tr>
                    <th>Situation</th>
                    <td><c:out value="${aventure.getSituation()}"/></td>
                </tr>
                <tr>
                    <th>Meneur</th>
                    <td>
                        <c:out value="${aventure.getMj().getPseudo()}"/>
                    </td>
                </tr>
                <tr>
                    <th>Accès rapide</th>
                    <td>
                    <a href="character?action=gameList&id=${aventure.getId()}" 
                     class="btn btn-primary">Participants</a>
                    </td>
                </tr>
                <tr>
                    <th>Ajouter un participant</th>
                    <td>
                    <c:choose>
                    <c:when test='${can_add}'>
                        <form class="form-inline" action="game?action=addMember" method="post">
                            <div class="input-group">
                                <input type="hidden" name="idAventure" value="${aventure.getId()}">
                                <div class="form-group">
                                    <p>Personnage à ajouter</p>
                                </div>
                                <div class="form-group">
                                    <select name="idPersonnage" onchange='if(this.value != -1) { this.form.submit(); }'
                                            class="form-control col-sm-offset-2">
                                        <option value="-1"></option>
                                        <c:forEach var="perso" items="${listePersonnages}">
                                            <option value="${perso.getId()}"><c:out value="${perso.getPseudo()}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
                    </c:otherwise>
                    </c:choose>
                    </td>
                </tr>
            </tbody>
        </table>

    </jsp:body>
</t:wrapper>
