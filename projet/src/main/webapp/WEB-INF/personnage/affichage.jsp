<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="title"> - Détails du personnage</jsp:attribute>

    <jsp:attribute name="header">
        <h1>Détails du personnage</h1>
    </jsp:attribute>

    <jsp:body>
        <c:if test='${perso.getPortrait() != null}'>
            <img src="<c:out value="${perso.getPortrait()}"/>" alt="Portrait"/>
            <br/><br/>
        </c:if>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Informations</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>Nom</th>
                    <td><c:out value="${perso.getNom()}"/></td>
                </tr>
                <tr>
                    <th>Naissance</th>
                    <td><c:out value="${perso.getNaissance()}"/></td>
                </tr>
                <tr>
                    <th>Univers</th>
                    <td><c:out value="${perso.getUnivers().getNom()}"/></td>
                </tr>
                <tr>
                    <th>Biographie</th>
                    <td><a href="biographie?action=afficher&id=${perso.getId()}" class="btn btn-primary">Accès biographie</a></td>
                </tr>
                <tr>
                    <th>Meneur</th>
                    <td>
                        <c:choose>
                        <c:when test='${perso.getMj().getId() != 0}'>
                            <c:out value="${perso.getMj().getPseudo()}"/>
                        </c:when>
                        <c:otherwise>
                        <span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
                        </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>Profession</th>
                    <td>
                        <c:choose>
                        <c:when test='${canEdit}'>
                        <form action="character?action=edit" method="post" class="form-inline">
                        <div class="form-group">
                          <input name="newWork" type="text" class="form-control" placeholder="Profession" value="<c:out value="${perso.getProfession()}"/>">
                        </div>
                        <input type="hidden" name="idPerso" value="${perso.getId()}">
                        <button type="submit" class="btn btn-default">Modifier</button>
                      </form>
                        </c:when>
                        <c:otherwise><c:out value="${perso.getProfession()}"/></c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <c:set var="user_id" value="${sessionScope.user.getId()}"/>
                    <c:set var="valid" value="${perso.isValide()}"/>
                    <th>Validation</th>
                    <td>
                    <c:choose>
                    <c:when test='${valid}'>
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                        <c:when test='${perso.getValidateur().getId() == 0}'>
                            <!--<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>-->
                            <form class="form-inline" action="character?action=editMJ" method="post">
                                <div class="input-group">
                                <input type="hidden" name="idPerso" value="${perso.getId()}">
                                <div class="form-group">
                                <p>Faire valider par </p>
                                </div>
                                <div class="form-group">
                                <select name="idMJ" onchange='if(this.value != -1) { this.form.submit(); }'
                                        class="form-control col-sm-offset-2">
                                    <option value="-1"></option>
                                    <c:forEach var="mj" items="${listeMJ}">
                                    <option value="${mj.getId()}"><c:out value="${mj.getPseudo()}"/></option>
                                    </c:forEach>
                                </select>
                                </div>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                        <!--<span class="glyphicon glyphicon-send" aria-hidden="true"></span>-->
                            <c:choose>
                            <c:when test='${perso.getValidateur().getId() == user_id}'>
                            <a class="btn btn-default" href="character?action=editMJ&id=${perso.getId()}">Valider le personnage</button>
                            </c:when>
                            <c:otherwise>
                            En attente de validation
                            </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                    </c:choose>
                    </td>
                </tr>
                <tr>
                    <c:set var="mj_id" value="${perso.getMj().getId()}"/>
                    <c:set var="notLeader" value="${mj_id != user_id}"/>
                    <c:set var="noTransfer" value="${perso.getTransfert().getId() == 0}"/>
                    <th>Transfert</th>
                    <td>
                    <c:choose>
                    <c:when test='${valid}'>
                        <c:choose>
                        <c:when test='${noTransfer && notLeader}'>
                            <form class="form-inline" action="character?action=transfer" method="post">
                                <div class="input-group">
                                <input type="hidden" name="idPerso" value="${perso.getId()}">
                                <div class="form-group">
                                <p>Transférer à </p>
                                </div>
                                <div class="form-group">
                                <select name="idMJ" onchange='if(this.value != -1) { this.form.submit(); }'
                                        class="form-control col-sm-offset-2">
                                    <option value="-1"></option>
                                    <c:forEach var="mj" items="${listeTransfert}">
                                    <option value="${mj.getId()}"><c:out value="${mj.getPseudo()}"/></option>
                                    </c:forEach>
                                </select>
                                </div>
                                </div>
                            </form>
                        </c:when>
                        <c:when test='${noTransfer && !notLeader}'>
                        <p>Aucune demande de transfert</p>
                        </c:when>
                        <c:otherwise>
                        <!--<span class="glyphicon glyphicon-send" aria-hidden="true"></span>-->
                            <c:choose>
                            <c:when test='${perso.getTransfert().getId() == sessionScope.user.getId()}'>
                            <a class="btn btn-default" href="character?action=transfer&id=${perso.getId()}">Accepter le transfert</button>
                            </c:when>
                            <c:otherwise>
                            En attente de transfert
                            </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
                    </c:otherwise>
                    </c:choose>
                    </td>
            </tbody>
        </table>

    </jsp:body>
</t:wrapper>
