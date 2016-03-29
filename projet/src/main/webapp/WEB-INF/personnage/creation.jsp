<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Créer un personnage</h1>
    </jsp:attribute>

    <jsp:body>
        
<form class="form-horizontal col-sm-offset-3" method="post" action="character?create">

  <div class="form-group">
    <label for="univers" class="col-sm-3 control-label">Univers</label>
    <div class="col-sm-4">
        <%-- TODO : afficher univers depuis une requete bdd --%>
        <select class="form-control" id="univers" name="univers" required>
            <option>1</option>
            <option>2</option>
        </select>
    </div>
  </div>
  <div class="form-group">
    <label for="nom" class="col-sm-3 control-label">Nom</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom" value="${param.nom}"/>
    </div>
  </div>
  <div class="form-group">
    <label for="naissance" class="col-sm-3 control-label">Naissance</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="naissance" name="naissance" placeholder="Naissance" value="${param.naissance}"/>
    </div>
  </div>
  <div class="form-group">
    <label for="portrait" class="col-sm-3 control-label">URL du portrait</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="portrait" name="portrait" placeholder="URL du portrait" value="${param.portrait}"/>
    </div>
  </div>
  <div class="form-group">
    <label for="profession" class="col-sm-3 control-label">Profession</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="profession" name="profession" placeholder="Profession" value="${param.profession}"/>
    </div>
  </div>
  <div class="form-group">
    <label for="biographie" class="col-sm-3 control-label">Biographie initiale</label>
    <div class="col-sm-4">
      <textarea class="form-control" rows="3" id="biographie" name="biographie" placeholder="Biographie initiale" value="${param.biographie}"></textarea>
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-1">
      <button type="submit" class="btn btn-default">Valider la création</button>
    </div>
  </div>
</form>
      
    </jsp:body>
</t:wrapper>
