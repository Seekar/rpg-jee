Tool UML : https://genmymodel.com


### Identifiants Oracle

Dans le dossier src/main/webapp/META-INF, copier le template context.xml.template vers context.xml et remplacer XXXX par les identifiants Oracle


### Bugs

- Si on accède à une servlet et que Main.java n'a pas été créée, le Datasource n'est pas dispo (pas important selon le prof). Fix possible : http://jersey.576304.n2.nabble.com/Initialization-at-startup-as-opposed-to-first-request-td5164440.html


### Critères de notation appli Web : [détail chamilo](http://chamilo2.grenet.fr/inp/courses/ENSIMAG4MMCAWEB/)
- **Gestion des droits d’accès** des utilisateurs (impossibilité de faire des actions réservées à l’utilisateur U sans être identifié comme U, **même en contournant l’interface**, par exemple en entrant des URL à la main, ou en utilisant une version en cache d’un formulaire sur un navigateur où U s’est déloggué)
- **Qualité** / présentation du code : structuration, **nommage**, commentaires ...
- **Ergonomie** / utilisabilité de l’interface
- **Gestion des erreurs**
- Correction du code HTML généré
- Respect du cahier des charges
- Respect de l’architecture MVC


Tutos JSTL (pour les vues JSP) : http://www.tutorialspoint.com/jsp/jsp_standard_tag_library.htm

Design CSS : [Bootstrap Twitter [documentation]](http://getbootstrap.com/)


