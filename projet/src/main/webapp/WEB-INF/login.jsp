<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="header">
    	<h1>Connexion</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
    <div>
        <form method="post" action="main">
            <ul>
                <li>Login : <input type="text" name="login"/></li>
                <li>Mot de passe : <input type="password" name="password"/></li>
            </ul>
            <input type="submit" name="Login"/>
        </form>
    </div>
    </jsp:body>

</t:wrapper>
