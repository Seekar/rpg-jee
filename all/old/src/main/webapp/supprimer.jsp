<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"/><title>Supprimer une référence</title></head>
    <body>
        <h2> Suppression d’une entrée </h2>

            Etes vous sûr de vouloir supprimer « ${ouvrage.titre} » de ${ouvrage.auteur} ?

        <form action="controleur" method="post">
            <input type="hidden" name="action" value="supprimer" />
            <a href="controleur">Non</a>
            <input type="submit" value="Oui" />
            <input type="hidden" name="id" value="${ouvrage.id}" />
        </form>
    </body>
</html>

