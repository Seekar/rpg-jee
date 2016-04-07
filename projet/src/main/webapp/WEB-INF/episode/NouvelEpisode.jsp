<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
        <h1> Nouvel Episode</h1>
    </jsp:attribute>

    <jsp:body>
        <form action="episode" method="post">
            <label> Date : </label> <input type="number" name="date" value="0"/> <br/>
            <label> Aventure (optionnel): </label> <br/>
            aucune aventure <input type="radio" name="aventure" value="__NONE__" checked/> <br/>
            <c:forEach var="aventure" items="${aventures}">
               ${aventure.getTitre()} <input type="radio" name="aventure" value="${aventure.getId()}"/> <br/>
            </c:forEach>
               <button class="btn btn-primary" type="submit"> créer</button>
               <input type="hidden" name="action" value="new"/>
               <input type="hidden" name="IDbio" value="${bioID}"/>
               <input type="hidden" name="persoID" value="${persoID}"/>
        </form>
    </jsp:body>
</t:wrapper>
