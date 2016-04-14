Tool UML : https://genmymodel.com

Code / design suffisant, faudrait arrêter de coder et finir la suite (sauf si trouvaille d'un bug important)


### TODO

Partie 1 : Analyse
- acteurs, diagramme de cas d'utilisations et description de ces cas d'utilisations, illustrées par des diagrammes de séquence système pertinents : Jules

Partie 2 : Conception
- doc conception : Simon

Partie 3
- manuel utilisateur : Léo / Salim

Partie 4
- rédaction bilan : J'ai écrit 2-3 trucs


- finir le script SQL pour préremplir la bdd : DONE
- rédaction readme.txt : DONE

- commentaires pertinents (parties non explicites) + Javadoc : (reste un peu de DAO peut-être)


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



[Appli en ligne (sans accès bdd)](http://rpg-723.rhcloud.com)
