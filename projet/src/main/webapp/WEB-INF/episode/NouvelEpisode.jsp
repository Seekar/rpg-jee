<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="title"> - Créer un épisode</jsp:attribute>

    <jsp:attribute name="header">
        <h1>Créer un épisode</h1>
    </jsp:attribute>

    <jsp:body>
        <form class="form-horizontal col-sm-offset-3" action="episode" method="post">
            <div class="form-group">
            <label for="date" class="col-sm-3 control-label">Date en UT</label>
            <div class="col-sm-4">
            <input id="date" class="form-control" type="number" name="date" value="${param.date}" required min="-999999999" max="999999999"/>
            </div>
            </div>
            <div class="form-group">
            <label for="aventure" class="col-sm-3 control-label">Aventure </label>
            <div class="col-sm-4">
            <select name="aventure" class="form-control">
                <option value="__NONE__" selected="selected">Aucune</option>
                <c:forEach var="aventure" items="${aventures}">
                <option value="${aventure.getId()}"><c:out value="${aventure.getTitre()}"/></option>
                </c:forEach>
            </select>
            </div>
            </div>
            <input type="hidden" name="action" value="new"/>
            <input type="hidden" name="IDbio" value="${bioID}"/>
            <input type="hidden" name="persoID" value="${persoID}"/>
            
            <div class="form-group">
              <div class="col-sm-offset-3 col-sm-1">
                <button type="submit" class="btn btn-primary">Valider la création</button>
              </div>
            </div>
        </form>
    </jsp:body>
</t:wrapper>
