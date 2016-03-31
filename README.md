Appli en ligne à l'adresse : http://rpg-723.rhcloud.com

Design CSS : [Bootstrap Twitter [documentation]](http://getbootstrap.com/)

Tool UML : https://genmymodel.com


### Identifiants Oracle

Dans le dossier src/main/webapp/META-INF, copier le template context.xml.template vers context.xml et remplacer XXXX par les identifiants Oracle

### Répartition

[Description des fonctionnalités](https://github.com/leogouttefarde/rpg/blob/master/todo.txt)

- Personnages : Léo
- Parties : Salim
- Biographie / Episodes / Paragraphes : Jules-Eugène / Simon


### Vues JSP

- Utilisation de template(s) 'tag' JSP pour factoriser les vues
- Utilisation de JSTL pour conditionner les vues etc


### BDD

"Un épisode peut être lié à une aventure. Lors de la création d’un épisode, l’application propose la liste des aventures auxquelles le personnage a participé pour permettre ce lien."

=> Donc il faut conserver l'historique des aventure de chaque personnage


### Bugs

- Si on accède à une servlet et que Main.java n'a pas été créée, le Datasource n'est pas dispo : voir http://jersey.576304.n2.nabble.com/Initialization-at-startup-as-opposed-to-first-request-td5164440.html


### ACVL

- acteurs, diagramme de cas d'utilisations et description de ces cas d'utilisations, illustrées par des diagrammes de séquence système pertinents (voir l'ensemble des fonctionnalités décrites dans todo.txt)


### Autre

- doc conception : à rédiger après implém java pour éviter les changements

- rédaction readme.txt
- rédaction manuel utilisateur
- rédaction bilan
