<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Modifier un paragraphe</h1>
    </jsp:attribute>

    <jsp:body>
        <form class="form-horizontal col-sm-offset-2" action="paragraphe" method="post">
            <div class="form-group">
              <div class="col-sm-10">
                <textarea class="form-control" rows="10" id="texte" name="texte" required
                 placeholder="Contenu du paragraphe"><c:out value="${parag.getTexte()}"/></textarea>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">Valider les modifications</button>
              </div>
            </div>
            <input type="hidden" name="idBio" value="${param.idBio}"/>
            <input type="hidden" name="id" value="${parag.getID()}"/>
            <input type="hidden" name="action" value="edit"/>
            <input type="hidden" name="persoID" value="${persoID}"/>
            <input type="hidden" name="idEpi" value="${param.idEpi}"/>
        </form>
    </jsp:body>
</t:wrapper>
