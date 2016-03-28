<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="header">
    	<h1>Se connecter</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>


<form class="form-horizontal col-sm-offset-3" method="post" action=".">
  <div class="form-group">
    <label for="nickname" class="col-sm-3 control-label">Login</label>
    <div class="col-sm-4<c:if test="${error != null}"> has-error</c:if>">
      <input type="text" class="form-control" id="nickname" name="nickname" placeholder="Login">
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-3 control-label">Mot de passe</label>
    <div class="col-sm-4<c:if test="${error != null}"> has-error</c:if>">
      <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe">
    </div>
  </div>
  <!--
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> Remember me
        </label>
      </div>
    </div>
  </div>
  -->
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-1">
      <button type="submit" class="btn btn-default">Connexion</button>
    </div>
  </div>
</form>

    </jsp:body>

</t:wrapper>
