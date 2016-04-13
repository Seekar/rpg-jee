<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="title"> - <c:out value="${titre}"/></jsp:attribute>

    <jsp:attribute name="header">
        <h1><c:out value="${titre}"/></h1>
    </jsp:attribute>

    <jsp:body>
<%-- Modal --%>
<c:if test="${!aventure.isFinie()}">
<div class="modal fade" id="modalFin" tabindex="-1" role="dialog" aria-labelledby="modalFinLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalFinLabel">Fin de la partie</h4>
      </div>
      <div class="modal-body">
      <form action="game" method="POST">
        <div class="form-group">
          <label for="message-text" class="control-label">Veuillez saisir un résumé des différents événements</label>
          <textarea class="form-control" id="eventsVal" rows="6"
                    placeholder="Résumé des événements" name="events"
                    required="required" data-rule-required="true"></textarea>
          <input type="hidden" name="action" value="finish"/>
          <input type="hidden" name="idAventure" value="${aventure.getId()}"/>
          <button id="finishForm" type="submit" class="hide"></button>
        </div>
      </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
        <button type="button" class="btn btn-warning"
         onclick="$('#finishForm').trigger('click');"
         >Valider</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="modalSuppr" tabindex="-1" role="dialog" aria-labelledby="modalSupprLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalSupprLabel">Suppression</h4>
      </div>
      <div class="modal-body">
      <p>Voulez vous vraiment supprimer la partie <c:out value="${aventure.getTitre()}"/> ?</p>
      <form id="deleteForm" action="game" method="POST">
          <input type="hidden" name="action" value="delete"/>
          <input type="hidden" name="idAventure" value="${aventure.getId()}"/>
      </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
        <button type="button" class="btn btn-danger" onclick="$('#deleteForm').trigger('submit');">Supprimer</button>
      </div>
    </div>
  </div>
</div>
</c:if>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th class="col-sm-2">Informations</th>
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
                    <th>Situation initiale</th>
                    <td class="text-left"><c:out value="${aventure.getSituation()}"/></td>
                </tr>
                <c:if test="${aventure.isFinie()}">
                <tr>
                    <th>Résumé des événements</th>
                    <td class="text-left">${aventure.showEvents()}</td>
                </tr>
                </c:if>
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
                    <c:if test="${!aventure.isFinie() && aventure.getMj().getId() == sessionScope.user.getId()}">
                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modalFin">
                    Finir la partie</button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalSuppr">
                    Effacer la partie</button></c:if>
                    </td>
                </tr>
                <tr>
                    <th>Ajouter un participant</th>
                    <td>
                    <c:choose>
                    <c:when test='${canAdd}'>
                        <form class="form-inline" action="game?action=addMember" method="post">
                            <div class="input-group">
                                <input type="hidden" name="idAventure" value="${aventure.getId()}">
                                <div class="form-group">
                                <p>Personnage à ajouter </p>
                                </div>
                                <div class="form-group">
                                <select name="idPersonnage" onchange='if(this.value != -1) { this.form.submit(); }'
                                        class="form-control col-sm-offset-2">
                                    <option value="-1"></option>
                                    <c:forEach var="perso" items="${listePersonnages}">
                                        <option value="${perso.getId()}"><c:out value="${perso.getNom()}"/></option>
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
