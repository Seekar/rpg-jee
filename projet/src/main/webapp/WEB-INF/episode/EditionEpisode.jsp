<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1>Edition de l'épisode daté à ${episode.getDate()} UT</h1>
    </jsp:attribute>

    <jsp:body>
        <a class="btn btn-primary btn-sm" href="paragraphe?eID=${episode.getId()}&action=new&persoID=${persoID}&idBio=${param.idBio}" >
        Nouveau paragraphe</a>
        <a href="biographie?action=edition&persoID=${persoID}&biographie=${param.idBio}"
         class="btn btn-default btn-sm">Retour</a>
        <br/>
        
        <div class="text-left">
        <c:forEach items="${episode.getParagraphes()}" var="parag">
            <br/>
            <p<c:if test="${parag.isSecret()}"> class="text-info"</c:if>><c:out value="${parag.getTexte()}"/></p>
            <c:if test="${parag.isSecret()}">
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
        { persoID : ${persoID},
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
            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> Révéler</button>
            </c:if>
            <a href="paragraphe?id=${parag.getID()}&action=edit&persoID=${persoID}&idEpi=${episode.getId()}&idBio=${param.idBio}"
             class="btn btn-default btn-xs">Modifier</a>
             <br/>
        </c:forEach>
        </div>
    </jsp:body>
</t:wrapper>

