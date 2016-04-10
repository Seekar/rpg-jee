<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="title"> - Créer un paragraphe</jsp:attribute>
    
    <jsp:attribute name="header">
        <h1>Créer un paragraphe</h1>
    </jsp:attribute>

    <jsp:body>
        <form class="form-horizontal col-sm-offset-2" action="paragraphe" method="post">
            
            <div class="form-group">
              <div class="col-sm-10">
                <textarea class="form-control" rows="10" id="texte" name="texte" placeholder="Contenu du paragraphe" 
                 value="${param.texte}" required></textarea>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-10">
                <div class="checkbox">
                  <label>
                    <input type="checkbox" name="secret" value="false">Paragraphe secret
                  </label>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">Valider la création</button>
              </div>
            </div>
            <input type="hidden" name="episodeID" value="${eID}"/>
            <input type="hidden" name="action" value="new"/>
            <input type="hidden" name="persoID" value="${persoID}"/>
        </form>
    </jsp:body>
</t:wrapper>
