Appli en ligne à l'adresse : http://rpg-723.rhcloud.com

Design CSS : [Bootstrap Twitter [documentation]](http://getbootstrap.com/)

Tool UML : https://genmymodel.com


### Protection contre les [failles d'injection XSS](https://fr.wikipedia.org/wiki/Cross-site_scripting)

Pour empêcher l'affichage de champs donnés par l'utilisateur de modifier la page HTML, il suffit d'afficher ce genre de champs avec JSTL, exemple : ```<c:out value="${monChamp}"/>``` au lieu de ```${monChamp}```


### Problème BD
Il faut garder l'historique des aventures jouées pas un personnage, que fait-on ?
On peut utiliser la relation joue mais dans ce cas il faut faire attention aux parties finies
ou on peut créeer une autre relation

=> En effet c'est plus compliqué avec un truc ternaire comme Joue et tous les autres groupes font avec une table entre persos / parties.

Du coup j'ai supprimé Joue et créé Participe

### Identifiants Oracle

Dans le dossier src/main/webapp/META-INF, copier le template context.xml.template vers context.xml et remplacer XXXX par les identifiants Oracle

### Répartition

[Description des fonctionnalités](https://github.com/leogouttefarde/rpg/blob/master/todo.txt)

- Personnages / Joueurs : Léo
- Parties : Salim
- Biographie / Episodes / Paragraphes : Jules-Eugène / Simon


### Vues JSP

- Utilisation de template(s) 'tag' JSP pour factoriser les vues
- Utilisation de JSTL pour conditionner les vues etc


### TODO

Forcer le login pour empêcher l'accès aux autres pages par des inconnus et aussi les erreurs liées à l'accès sans login


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
