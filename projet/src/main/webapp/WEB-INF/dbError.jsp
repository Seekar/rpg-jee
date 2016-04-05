<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper>
    <jsp:attribute name="header">
    	<h1>Erreur d'accès à la base de données</h1>
    </jsp:attribute>

    <jsp:body>
        <p>Une erreur d'accès à la base de données s'est produite.</p>
        <c:if test="error != null">
            <p>Détails : ${error}</p>
        </c:if>
    </jsp:body>

</t:wrapper>
