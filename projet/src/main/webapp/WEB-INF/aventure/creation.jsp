<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="title"> - Créer une aventure</jsp:attribute>
    <jsp:attribute name="header">
        <h1>Créer une aventure</h1>
    </jsp:attribute>

    <jsp:body>

<form class="form-horizontal col-sm-offset-3" method="post" action="game?action=create">

  <div class="form-group">
    <label for="univers" class="col-sm-3 control-label">Univers</label>
    <div class="col-sm-4">
        <select class="form-control" id="univers" name="univers" required>
            <c:forEach var="univers" items="${listeUnivers}">
            <option value="${univers.getId()}">${univers.getNom()}</option>
            </c:forEach>
        </select>
    </div>
  </div>

  <div class="form-group">
    <label for="titre" class="col-sm-3 control-label">Titre</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="titre" name="titre" placeholder="Titre" value="${param.titre}" required/>
    </div>
  </div>

  <div class="form-group">
    <label for="date" class="col-sm-3 control-label">Date</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="date" name="date" placeholder="Date" value="${param.date}" required/>
    </div>
  </div>

  <div class="form-group">
    <label for="lieu" class="col-sm-3 control-label">Lieu</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="lieu" name="lieu" placeholder="Lieu" value="${param.lieu}" required/>
    </div>
  </div>

  <div class="form-group">
    <label for="resume" class="col-sm-3 control-label">Résumé de la situation initiale</label>
    <div class="col-sm-7">
      <textarea class="form-control" rows="7" id="resume" name="resume" placeholder="Résumé de la situation initiale" value="${param.resume}" required></textarea>
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-1">
      <button type="submit" class="btn btn-primary">Valider la création de l'aventure</button>
    </div>
  </div>
</form>

    </jsp:body>
</t:wrapper>
