<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"/><title>Modifier une référence</title></head>
    <body>
        <h2> Modifier une référence </h2>

        <form action="controleur" method="post" accept-charset="UTF-8">
            Auteur : 
            <input type="text" name="auteur" value="${ouvrage.auteur}"/>   
            <br />
            Titre : 
            <input type="text" name="titre" value="${ouvrage.titre}"/> 
            <br />
            <input type="hidden" name="action" value="modifier" />
            <a href="controleur">Annuler</a>
            <input type="submit" value="Valider" />
            <input type="hidden" name="id" value="${ouvrage.id}" />
        </form>
    </body>
</html>


