<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Biographie de <c:out value="${perso.nom}"/></h1>
    </jsp:attribute>

    <jsp:body>

        <div class="text-left">
        <p>${perso.getBiographie().showTexte()}</p>

        <br/>
        <c:if test="${owner}">
        <a href="biographie?action=edition&persoID=${perso.getId()}&biographie=${perso.getBiographie().getID()}" 
           class="btn btn-default">Editer les épisodes</a>
        </c:if>

        <c:forEach items="${perso.getBiographie().getEpisodes()}" var="episode">
        <c:if test="${episode.getValide()}">
        <br/>
        <h3>Episode daté à <c:out value="${episode.getDate()}"/> UT<c:if test="${episode.getAventure() != null}">, 
        pendant l'aventure <a href="game?action=show&id=${episode.getAventure().getId()}"
         target="_blank"><c:out value="${episode.getAventure().getTitre()}"/></a></c:if></h3>

        <c:forEach items="${episode.getParagraphes()}" var="parag">
            <p<c:if test="${parag.isSecret()}"> class="text-info"</c:if>><c:out value="${parag.getTexte()}"/></p>
            <c:if test="${owner && parag.isSecret()}">
<div class="modal fade" id="modalReveal${parag.getID()}" tabindex="-1" role="dialog" aria-labelledby="modalRevealLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalRevealLabel">Révéler un paragraphe</h4>
      </div>
      <div class="modal-body">
      <p class="text-info"><c:out value="${parag.getTexte()}"/></p>
      <p>Voulez vous vraiment révéler ce paragraphe ?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
        <button type="button" class="btn btn-primary" onclick="
        $.post('paragraphe',
        { res : 'oui',
        persoID : ${perso.getId()},
        pID : ${parag.getID()},
        action : 'reveler' },
        function (data) {
        /*if (data == 'done') { alert('success'); };*/
        location.reload(); }); ">Révéler</button>
      </div>
    </div>
  </div>
</div>
            <button type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalReveal${parag.getID()}">
            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> Révéler ce paragraphe</button>
            <br/><br/>
            </c:if>
        </c:forEach>
        </c:if>

        </c:forEach>
        </div>
    </jsp:body>
</t:wrapper>
