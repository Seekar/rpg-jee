Tutos JSTL (pour les vues JSP) : http://www.tutorialspoint.com/jsp/jsp_standard_tag_library.htm

Design CSS : [Bootstrap Twitter [documentation]](http://getbootstrap.com/)

Tool UML : https://genmymodel.com


### Critères de notation appli Web : [détail chamilo](http://chamilo2.grenet.fr/inp/courses/ENSIMAG4MMCAWEB/)
- **Gestion des droits d’accès** des utilisateurs (impossibilité de faire des actions réservées à l’utilisateur U sans être identifié comme U, **même en contournant l’interface**, par exemple en entrant des URL à la main, ou en utilisant une version en cache d’un formulaire sur un navigateur où U s’est déloggué)
- **Qualité** / présentation du code : structuration, **nommage**, commentaires ...
- **Ergonomie** / utilisabilité de l’interface
- **Gestion des erreurs**
- Correction du code HTML généré
- Respect du cahier des charges
- Respect de l’architecture MVC



### Protection contre les [failles d'injection XSS](https://fr.wikipedia.org/wiki/Cross-site_scripting)

Pour empêcher l'affichage de champs donnés par l'utilisateur de modifier la page HTML, il suffit d'afficher ce genre de champs avec JSTL, exemple : ```<c:out value="${monChamp}"/>``` au lieu de ```${monChamp}```


### Identifiants Oracle

Dans le dossier src/main/webapp/META-INF, copier le template context.xml.template vers context.xml et remplacer XXXX par les identifiants Oracle

### Répartition

[Description des fonctionnalités](https://github.com/leogouttefarde/rpg/blob/master/todo.txt)

- Personnages / Joueurs : Terminé
- Parties : Salim
- Biographie / Episodes / Paragraphes : Jules-Eugène / Simon


### Vues JSP

- Utilisation de template(s) 'tag' JSP pour factoriser les vues
- Utilisation de JSTL pour conditionner les vues etc


### BDD

[Graphique SVG](https://github.com/leogouttefarde/rpg/blob/master/bdd.svg)


### Infos

Dans ma gestion des persos, le seul moyen d'être le meneur d'un de ses propres persos est qu'un autre joueur nous le cède alors qu'on était déjà meneur

De la même façon, on peut se retrouver à posséder plusieurs persos participant à une même partie etc car l'énoncé est ambigu et que sinon c'est encore plus complexe.


### Bugs

- Si on accède à une servlet et que Main.java n'a pas été créée, le Datasource n'est pas dispo (pas important selon le prof). Fix possible : http://jersey.576304.n2.nabble.com/Initialization-at-startup-as-opposed-to-first-request-td5164440.html


### ACVL

- acteurs, diagramme de cas d'utilisations et description de ces cas d'utilisations, illustrées par des diagrammes de séquence système pertinents (voir l'ensemble des fonctionnalités décrites dans todo.txt)


### Autre

- doc conception : à rédiger après implém java pour éviter les changements

- rédaction readme.txt
- rédaction manuel utilisateur
- rédaction bilan


[Appli en ligne](http://rpg-723.rhcloud.com)
